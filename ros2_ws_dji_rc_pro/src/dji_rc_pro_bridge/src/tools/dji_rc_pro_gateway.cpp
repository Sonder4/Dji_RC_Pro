#include <arpa/inet.h>
#include <errno.h>
#include <ifaddrs.h>
#include <net/if.h>
#include <netinet/in.h>
#include <poll.h>
#include <sys/socket.h>
#include <unistd.h>

#include <atomic>
#include <chrono>
#include <cmath>
#include <cstdint>
#include <cstring>
#include <fstream>
#include <iomanip>
#include <map>
#include <mutex>
#include <set>
#include <sstream>
#include <string>
#include <stdexcept>
#include <thread>
#include <utility>
#include <vector>

#include <openssl/evp.h>
#include <openssl/hmac.h>
#include <openssl/sha.h>

#include <geometry_msgs/msg/twist.hpp>
#include <rclcpp/rclcpp.hpp>
#include <std_msgs/msg/header.hpp>
#include <std_msgs/msg/string.hpp>
#include <std_msgs/msg/u_int8_multi_array.hpp>

#include "dji_rc_pro_bridge/msg/chassis_ctrl.hpp"
#include "protocol/protocol_parser.hpp"

namespace {

constexpr const char* kProtocolVersion = "RCBRIDGE_DISCOVERY/2";
constexpr const char* kDefaultDiscoveryGroup = "ff12::2026";
constexpr const char* kDefaultPairCode = "CHANGE_ME_PAIR_CODE";
constexpr int kDefaultControlPort = 1387;
constexpr int kDefaultDiscoveryPort = 1388;
constexpr int kDefaultAnnounceIntervalMs = 1000;
constexpr int kDefaultLeaseMs = 5000;
constexpr int kPollTimeoutMs = 500;

using FieldList = std::vector<std::pair<std::string, std::string>>;

enum class AddressFamily {
  kIpv4,
  kIpv6,
};

enum class TransportMode {
  kUdpOnly,
  kBleOnly,
  kBleUdp,
};

struct InterfaceInfo {
  unsigned int index = 0;
  std::string name;
};

struct HostAddresses {
  std::string ipv4;
  std::string ipv6;
};

int16_t ReadInt16Le(const std::vector<uint8_t>& data, std::size_t offset) {
  if (offset + 1 >= data.size()) {
    return 0;
  }
  const uint16_t value = static_cast<uint16_t>(data[offset]) |
                         (static_cast<uint16_t>(data[offset + 1]) << 8);
  return static_cast<int16_t>(value);
}

std::string Trim(const std::string& input) {
  const auto first = input.find_first_not_of(" \t\r\n");
  if (first == std::string::npos) {
    return "";
  }
  const auto last = input.find_last_not_of(" \t\r\n");
  return input.substr(first, last - first + 1);
}

std::map<std::string, std::string> ParseFields(const std::string& payload) {
  std::map<std::string, std::string> fields;
  std::istringstream iss(payload);
  std::string line;
  while (std::getline(iss, line)) {
    const auto cleaned = Trim(line);
    if (cleaned.empty()) {
      continue;
    }
    const auto separator = cleaned.find('=');
    if (separator == std::string::npos || separator == 0) {
      continue;
    }
    fields[cleaned.substr(0, separator)] = cleaned.substr(separator + 1);
  }
  return fields;
}

std::string BuildMessage(const FieldList& fields) {
  std::ostringstream oss;
  for (const auto& entry : fields) {
    oss << entry.first << '=' << entry.second << '\n';
  }
  oss << '\n';
  return oss.str();
}

std::string BytesToHex(const unsigned char* data, std::size_t size) {
  std::ostringstream oss;
  oss << std::hex << std::setfill('0');
  for (std::size_t index = 0; index < size; ++index) {
    oss << std::setw(2) << static_cast<int>(data[index]);
  }
  return oss.str();
}

std::string Sha256Hex(const std::string& input) {
  unsigned char digest[SHA256_DIGEST_LENGTH] = {0};
  SHA256(reinterpret_cast<const unsigned char*>(input.data()), input.size(), digest);
  return BytesToHex(digest, sizeof(digest));
}

std::string HmacSha256Hex(const std::string& key, const std::string& payload) {
  unsigned char digest[EVP_MAX_MD_SIZE] = {0};
  unsigned int digest_len = 0;
  HMAC(EVP_sha256(),
       key.data(), static_cast<int>(key.size()),
       reinterpret_cast<const unsigned char*>(payload.data()), payload.size(),
       digest, &digest_len);
  return BytesToHex(digest, digest_len);
}

std::string JoinSignature(const std::vector<std::string>& parts) {
  std::ostringstream oss;
  for (std::size_t index = 0; index < parts.size(); ++index) {
    if (index != 0) {
      oss << '|';
    }
    oss << parts[index];
  }
  return oss.str();
}

std::string PeerAddressToString(const sockaddr_storage& peer) {
  char host[INET6_ADDRSTRLEN] = {0};
  if (peer.ss_family == AF_INET6) {
    const auto* addr = reinterpret_cast<const sockaddr_in6*>(&peer);
    inet_ntop(AF_INET6, &addr->sin6_addr, host, sizeof(host));
    return std::string(host);
  }
  if (peer.ss_family == AF_INET) {
    const auto* addr = reinterpret_cast<const sockaddr_in*>(&peer);
    inet_ntop(AF_INET, &addr->sin_addr, host, sizeof(host));
    return std::string(host);
  }
  return "unknown";
}

std::string DefaultHostName() {
  return "ros2-gateway";
}

std::string ReadFirstLine(const char* path) {
  std::ifstream input(path);
  if (!input.is_open()) {
    return "";
  }
  std::string line;
  std::getline(input, line);
  return Trim(line);
}

std::string DeriveStableHostId(const std::string& host_name) {
  const std::string machine_id = ReadFirstLine("/etc/machine-id");
  if (!machine_id.empty()) {
    return "rcbridge-" + Sha256Hex(machine_id).substr(0, 16);
  }
  return "rcbridge-" + Sha256Hex(host_name).substr(0, 16);
}

std::vector<InterfaceInfo> EnumerateIpv6MulticastInterfaces() {
  std::vector<InterfaceInfo> interfaces;
  std::set<unsigned int> seen;

  ifaddrs* ifaddr = nullptr;
  if (::getifaddrs(&ifaddr) != 0 || ifaddr == nullptr) {
    return interfaces;
  }

  for (ifaddrs* ifa = ifaddr; ifa != nullptr; ifa = ifa->ifa_next) {
    if (ifa->ifa_addr == nullptr || ifa->ifa_addr->sa_family != AF_INET6) {
      continue;
    }
    if ((ifa->ifa_flags & IFF_UP) == 0 || (ifa->ifa_flags & IFF_MULTICAST) == 0 ||
        (ifa->ifa_flags & IFF_LOOPBACK) != 0) {
      continue;
    }

    const auto* addr = reinterpret_cast<const sockaddr_in6*>(ifa->ifa_addr);
    if (IN6_IS_ADDR_LOOPBACK(&addr->sin6_addr) || IN6_IS_ADDR_LINKLOCAL(&addr->sin6_addr)) {
      continue;
    }

    const unsigned int if_index = if_nametoindex(ifa->ifa_name);
    if (if_index == 0 || seen.count(if_index) != 0) {
      continue;
    }

    seen.insert(if_index);
    interfaces.push_back(InterfaceInfo{if_index, ifa->ifa_name});
  }

  ::freeifaddrs(ifaddr);
  return interfaces;
}

HostAddresses CollectHostAddresses() {
  HostAddresses addresses;
  ifaddrs* ifaddr = nullptr;
  if (::getifaddrs(&ifaddr) != 0 || ifaddr == nullptr) {
    return addresses;
  }

  for (ifaddrs* ifa = ifaddr; ifa != nullptr; ifa = ifa->ifa_next) {
    if (ifa->ifa_addr == nullptr || (ifa->ifa_flags & IFF_UP) == 0 ||
        (ifa->ifa_flags & IFF_LOOPBACK) != 0) {
      continue;
    }

    char host[INET6_ADDRSTRLEN] = {0};
    if (ifa->ifa_addr->sa_family == AF_INET && addresses.ipv4.empty()) {
      const auto* addr = reinterpret_cast<const sockaddr_in*>(ifa->ifa_addr);
      if (inet_ntop(AF_INET, &addr->sin_addr, host, sizeof(host)) != nullptr) {
        addresses.ipv4 = host;
      }
    }

    if (ifa->ifa_addr->sa_family == AF_INET6 && addresses.ipv6.empty()) {
      const auto* addr = reinterpret_cast<const sockaddr_in6*>(ifa->ifa_addr);
      if (!IN6_IS_ADDR_LOOPBACK(&addr->sin6_addr) && !IN6_IS_ADDR_LINKLOCAL(&addr->sin6_addr) &&
          inet_ntop(AF_INET6, &addr->sin6_addr, host, sizeof(host)) != nullptr) {
        addresses.ipv6 = host;
      }
    }

    if (!addresses.ipv4.empty() && !addresses.ipv6.empty()) {
      break;
    }
  }

  ::freeifaddrs(ifaddr);
  return addresses;
}

std::string AddressFamilyToWire(AddressFamily family) {
  return family == AddressFamily::kIpv6 ? "ipv6" : "ipv4";
}

int64_t MonotonicMillis() {
  return std::chrono::duration_cast<std::chrono::milliseconds>(
             std::chrono::steady_clock::now().time_since_epoch())
      .count();
}

TransportMode ParseTransportMode(const std::string& value) {
  if (value == "udp_only") {
    return TransportMode::kUdpOnly;
  }
  if (value == "ble_only") {
    return TransportMode::kBleOnly;
  }
  return TransportMode::kBleUdp;
}

const char* TransportModeToString(TransportMode mode) {
  switch (mode) {
    case TransportMode::kUdpOnly:
      return "udp_only";
    case TransportMode::kBleOnly:
      return "ble_only";
    case TransportMode::kBleUdp:
      return "ble_udp";
  }
  return "ble_udp";
}

}  // namespace

class DjiRcProGatewayNode : public rclcpp::Node {
 public:
  DjiRcProGatewayNode()
      : Node("dji_rc_pro_gateway"),
        udp_parser_(),
        ble_parser_(),
        host_name_(DefaultHostName()) {
    host_name_ = this->declare_parameter<std::string>("host_name", host_name_);
    host_id_ = this->declare_parameter<std::string>("host_id", "");
    discovery_group_ = this->declare_parameter<std::string>("discovery_group", kDefaultDiscoveryGroup);
    pair_code_ = this->declare_parameter<std::string>("pair_code", kDefaultPairCode);
    transport_mode_ = ParseTransportMode(
        this->declare_parameter<std::string>("transport_mode", "ble_udp"));
    control_port_ = this->declare_parameter<int>("control_port", kDefaultControlPort);
    discovery_port_ = this->declare_parameter<int>("discovery_port", kDefaultDiscoveryPort);
    announce_interval_ms_ = this->declare_parameter<int>("announce_interval_ms", kDefaultAnnounceIntervalMs);
    lease_ms_ = this->declare_parameter<int>("lease_ms", kDefaultLeaseMs);
    require_ready_for_pairing_ = this->declare_parameter<bool>("require_ready_for_pairing", false);
    cmd_vel_topic_ = this->declare_parameter<std::string>("cmd_vel_topic", "/dji_rc_pro_bridge/cmd_vel");
    raw_topic_ = this->declare_parameter<std::string>("raw_topic", "/dji_rc_pro_bridge/chassis_ctrl_raw");
    status_topic_ = this->declare_parameter<std::string>("status_topic", "/mcu_comm_node/status");
    ble_frame_topic_ = this->declare_parameter<std::string>("ble_frame_topic", "/dji_rc_pro_bridge/ble/control_frame");
    transport_status_topic_ = this->declare_parameter<std::string>("transport_status_topic", "/dji_rc_pro_bridge/transport_status");
    ble_fallback_holdoff_ms_ = this->declare_parameter<int>("ble_fallback_holdoff_ms", 1500);

    if (host_id_.empty()) {
      host_id_ = DeriveStableHostId(host_name_);
    }
    if (pair_code_.empty()) {
      pair_code_ = kDefaultPairCode;
    }
    if (lease_ms_ <= 0) {
      lease_ms_ = kDefaultLeaseMs;
    }

    const auto now_count = std::chrono::steady_clock::now().time_since_epoch().count();
    host_nonce_ = Sha256Hex(host_id_ + "|" + std::to_string(now_count) + "|" + std::to_string(::getpid())).substr(0, 32);

    cmd_vel_publisher_ = this->create_publisher<geometry_msgs::msg::Twist>(cmd_vel_topic_, 10);
    raw_publisher_ = this->create_publisher<dji_rc_pro_bridge::msg::ChassisCtrl>(raw_topic_, 10);
    transport_status_publisher_ = this->create_publisher<std_msgs::msg::String>(transport_status_topic_, 10);
    ble_frame_subscription_ = this->create_subscription<std_msgs::msg::UInt8MultiArray>(
        ble_frame_topic_, 10,
        [this](const std_msgs::msg::UInt8MultiArray::SharedPtr msg) { this->HandleBleFrame(msg); });
    status_subscription_ = this->create_subscription<std_msgs::msg::Header>(
        status_topic_, 10,
        [this](const std_msgs::msg::Header::SharedPtr msg) {
          status_seen_.store(true);
          mcu_ready_.store(msg->frame_id == "connected");
        });

    running_.store(true);
    if (AllowsUdpTransport()) {
      discovery_ipv4_socket_ = OpenIpv4Socket(discovery_port_, true);
      discovery_ipv6_socket_ = OpenIpv6Socket(discovery_port_);
      control_ipv4_socket_ = OpenIpv4Socket(control_port_, false);
      control_ipv6_socket_ = OpenIpv6Socket(control_port_);

      if (discovery_ipv4_socket_ < 0 && discovery_ipv6_socket_ < 0) {
        throw std::runtime_error("Failed to open discovery sockets");
      }
      if (control_ipv4_socket_ < 0 && control_ipv6_socket_ < 0) {
        throw std::runtime_error("Failed to open control sockets");
      }

      if (discovery_ipv6_socket_ >= 0) {
        JoinDiscoveryGroup();
      }

      discovery_thread_ = std::thread(&DjiRcProGatewayNode::DiscoveryLoop, this);
      control_thread_ = std::thread(&DjiRcProGatewayNode::ControlLoop, this);
    } else {
      RCLCPP_INFO(this->get_logger(), "Transport mode ble_only: UDP discovery/control sockets disabled");
    }
    lease_timer_ = this->create_wall_timer(
        std::chrono::milliseconds(500),
        [this]() {
          this->CleanupLease();
          this->PublishTransportStatus();
        });

    const HostAddresses addresses = CollectHostAddresses();
    RCLCPP_INFO(this->get_logger(),
                "DJI RC Pro gateway ready host_id=%s host_name=%s transport_mode=%s control=%d discovery=%d ipv4=%s ipv6=%s",
                host_id_.c_str(), host_name_.c_str(), TransportModeToString(transport_mode_), control_port_, discovery_port_,
                addresses.ipv4.empty() ? "-" : addresses.ipv4.c_str(),
                addresses.ipv6.empty() ? "-" : addresses.ipv6.c_str());
    RCLCPP_INFO(this->get_logger(),
                "首次配对引导: Pair Code/短码=%s, Bootstrap IPv6=%s, Bootstrap IPv4=%s, cmd_vel=%s, ble_frame=%s",
                pair_code_.c_str(),
                addresses.ipv6.empty() ? "-" : addresses.ipv6.c_str(),
                addresses.ipv4.empty() ? "-" : addresses.ipv4.c_str(),
                cmd_vel_topic_.c_str(),
                ble_frame_topic_.c_str());
  }

  ~DjiRcProGatewayNode() override {
    running_.store(false);
    CloseSocket(discovery_ipv4_socket_);
    CloseSocket(discovery_ipv6_socket_);
    CloseSocket(control_ipv4_socket_);
    CloseSocket(control_ipv6_socket_);

    if (discovery_thread_.joinable()) {
      discovery_thread_.join();
    }
    if (control_thread_.joinable()) {
      control_thread_.join();
    }
  }

 private:
  struct LeaseState {
    std::string client_id;
    std::string client_name;
    std::string client_nonce;
    std::string client_address;
    std::string session_id;
    AddressFamily family = AddressFamily::kIpv4;
    std::chrono::steady_clock::time_point expires_at = std::chrono::steady_clock::time_point::min();
  };

  int discovery_ipv4_socket_{-1};
  int discovery_ipv6_socket_{-1};
  int control_ipv4_socket_{-1};
  int control_ipv6_socket_{-1};
  std::atomic<bool> running_{false};
  std::atomic<bool> status_seen_{false};
  std::atomic<bool> mcu_ready_{true};
  std::atomic<int64_t> last_udp_frame_monotonic_ms_{0};
  std::atomic<int64_t> last_ble_frame_monotonic_ms_{0};
  std::thread discovery_thread_;
  std::thread control_thread_;
  std::mutex lease_mutex_;
  LeaseState lease_state_;
  dji_rc_pro_bridge::ProtocolParser udp_parser_;
  dji_rc_pro_bridge::ProtocolParser ble_parser_;
  std::vector<InterfaceInfo> discovery_interfaces_;

  std::string host_id_;
  std::string host_name_;
  std::string host_nonce_;
  std::string discovery_group_;
  std::string pair_code_;
  TransportMode transport_mode_{TransportMode::kBleUdp};
  std::string cmd_vel_topic_;
  std::string raw_topic_;
  std::string status_topic_;
  std::string ble_frame_topic_;
  std::string transport_status_topic_;
  int control_port_{kDefaultControlPort};
  int discovery_port_{kDefaultDiscoveryPort};
  int announce_interval_ms_{kDefaultAnnounceIntervalMs};
  int lease_ms_{kDefaultLeaseMs};
  int ble_fallback_holdoff_ms_{1500};
  bool require_ready_for_pairing_{false};
  uint64_t session_counter_{0};

  rclcpp::Publisher<geometry_msgs::msg::Twist>::SharedPtr cmd_vel_publisher_;
  rclcpp::Publisher<dji_rc_pro_bridge::msg::ChassisCtrl>::SharedPtr raw_publisher_;
  rclcpp::Publisher<std_msgs::msg::String>::SharedPtr transport_status_publisher_;
  rclcpp::Subscription<std_msgs::msg::UInt8MultiArray>::SharedPtr ble_frame_subscription_;
  rclcpp::Subscription<std_msgs::msg::Header>::SharedPtr status_subscription_;
  rclcpp::TimerBase::SharedPtr lease_timer_;

  bool AllowsUdpTransport() const {
    return transport_mode_ != TransportMode::kBleOnly;
  }

  bool AllowsBleTransport() const {
    return transport_mode_ != TransportMode::kUdpOnly;
  }

  int OpenIpv4Socket(int port, bool enable_broadcast) {
    const int sock = ::socket(AF_INET, SOCK_DGRAM, 0);
    if (sock < 0) {
      RCLCPP_WARN(this->get_logger(), "socket(AF_INET, %d) failed: %s", port, std::strerror(errno));
      return -1;
    }

    int reuse = 1;
    ::setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &reuse, sizeof(reuse));
    if (enable_broadcast) {
      int broadcast = 1;
      ::setsockopt(sock, SOL_SOCKET, SO_BROADCAST, &broadcast, sizeof(broadcast));
    }

    sockaddr_in addr{};
    addr.sin_family = AF_INET;
    addr.sin_addr.s_addr = htonl(INADDR_ANY);
    addr.sin_port = htons(static_cast<uint16_t>(port));
    if (::bind(sock, reinterpret_cast<sockaddr*>(&addr), sizeof(addr)) < 0) {
      RCLCPP_WARN(this->get_logger(), "bind(AF_INET, %d) failed: %s", port, std::strerror(errno));
      ::close(sock);
      return -1;
    }
    return sock;
  }

  int OpenIpv6Socket(int port) {
    const int sock = ::socket(AF_INET6, SOCK_DGRAM, 0);
    if (sock < 0) {
      RCLCPP_WARN(this->get_logger(), "socket(AF_INET6, %d) failed: %s", port, std::strerror(errno));
      return -1;
    }

    int reuse = 1;
    ::setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &reuse, sizeof(reuse));

    int v6_only = 1;
    ::setsockopt(sock, IPPROTO_IPV6, IPV6_V6ONLY, &v6_only, sizeof(v6_only));

    int loop = 0;
    ::setsockopt(sock, IPPROTO_IPV6, IPV6_MULTICAST_LOOP, &loop, sizeof(loop));

    sockaddr_in6 addr{};
    addr.sin6_family = AF_INET6;
    addr.sin6_addr = in6addr_any;
    addr.sin6_port = htons(static_cast<uint16_t>(port));
    if (::bind(sock, reinterpret_cast<sockaddr*>(&addr), sizeof(addr)) < 0) {
      RCLCPP_WARN(this->get_logger(), "bind(AF_INET6, %d) failed: %s", port, std::strerror(errno));
      ::close(sock);
      return -1;
    }
    return sock;
  }

  void JoinDiscoveryGroup() {
    discovery_interfaces_ = EnumerateIpv6MulticastInterfaces();
    if (discovery_interfaces_.empty()) {
      RCLCPP_WARN(this->get_logger(), "No IPv6 multicast interface available for discovery group");
      return;
    }

    in6_addr multicast_addr{};
    if (::inet_pton(AF_INET6, discovery_group_.c_str(), &multicast_addr) != 1) {
      RCLCPP_ERROR(this->get_logger(), "Invalid discovery group: %s", discovery_group_.c_str());
      return;
    }

    for (const auto& interface_info : discovery_interfaces_) {
      ipv6_mreq request{};
      request.ipv6mr_multiaddr = multicast_addr;
      request.ipv6mr_interface = interface_info.index;
      if (::setsockopt(discovery_ipv6_socket_, IPPROTO_IPV6, IPV6_JOIN_GROUP,
                       &request, sizeof(request)) < 0) {
        RCLCPP_WARN(this->get_logger(), "Failed to join %s on %s: %s",
                    discovery_group_.c_str(), interface_info.name.c_str(), std::strerror(errno));
      } else {
        RCLCPP_INFO(this->get_logger(), "Joined %s on %s",
                    discovery_group_.c_str(), interface_info.name.c_str());
      }
    }
  }

  void CloseSocket(int& sock) {
    if (sock >= 0) {
      ::close(sock);
      sock = -1;
    }
  }

  bool IsReady() const {
    return !status_seen_.load() || mcu_ready_.load();
  }

  bool IsLeaseActiveLocked(const std::chrono::steady_clock::time_point now) const {
    return !lease_state_.client_id.empty() && now < lease_state_.expires_at;
  }

  void ClearLeaseLocked() {
    lease_state_ = LeaseState{};
  }

  std::string GenerateSessionId() {
    std::ostringstream oss;
    oss << std::hex << std::uppercase
        << static_cast<unsigned long long>(
               std::chrono::steady_clock::now().time_since_epoch().count())
        << '-' << ++session_counter_;
    return oss.str();
  }

  void DiscoveryLoop() {
    std::vector<char> buffer(4096);
    while (running_.load() && rclcpp::ok()) {
      pollfd fds[2]{};
      nfds_t nfds = 0;
      if (discovery_ipv4_socket_ >= 0) {
        fds[nfds++] = pollfd{discovery_ipv4_socket_, POLLIN, 0};
      }
      if (discovery_ipv6_socket_ >= 0) {
        fds[nfds++] = pollfd{discovery_ipv6_socket_, POLLIN, 0};
      }
      if (nfds == 0) {
        return;
      }

      const int ready = ::poll(fds, nfds, kPollTimeoutMs);
      if (ready <= 0) {
        continue;
      }

      for (nfds_t index = 0; index < nfds; ++index) {
        if ((fds[index].revents & POLLIN) == 0) {
          continue;
        }

        sockaddr_storage peer{};
        socklen_t peer_len = sizeof(peer);
        const ssize_t received = ::recvfrom(fds[index].fd, buffer.data(), buffer.size(), 0,
                                            reinterpret_cast<sockaddr*>(&peer), &peer_len);
        if (received <= 0) {
          continue;
        }

        const std::string payload(buffer.data(), static_cast<std::size_t>(received));
        const auto fields = ParseFields(payload);
        const auto proto_it = fields.find("proto");
        if (proto_it == fields.end() || proto_it->second != kProtocolVersion) {
          continue;
        }

        const auto type_it = fields.find("type");
        if (type_it == fields.end()) {
          continue;
        }

        const AddressFamily family = peer.ss_family == AF_INET6 ? AddressFamily::kIpv6 : AddressFamily::kIpv4;
        if (type_it->second == "probe") {
          HandleProbe(fields, peer, peer_len, fds[index].fd, family);
        } else if (type_it->second == "pair_request") {
          HandlePairRequest(fields, peer, peer_len, fds[index].fd, family);
        } else if (type_it->second == "unpair") {
          HandleUnpair(fields, peer, family);
        }
      }
    }
  }

  void HandleProbe(const std::map<std::string, std::string>& fields,
                   const sockaddr_storage& peer,
                   socklen_t peer_len,
                   int socket_fd,
                   AddressFamily family) {
    const auto client_id_it = fields.find("client_id");
    const auto client_nonce_it = fields.find("client_nonce");
    const auto proof_it = fields.find("proof");
    if (client_id_it == fields.end() || client_nonce_it == fields.end() || proof_it == fields.end()) {
      return;
    }

    const std::string client_id = client_id_it->second;
    const std::string client_nonce = client_nonce_it->second;
    const std::string client_name = fields.count("client_name") ? fields.at("client_name") : "RCBridge_Controller";
    const std::string expected_proof = HmacSha256Hex(
        pair_code_,
        JoinSignature({"probe", kProtocolVersion, client_id, client_nonce, client_name}));
    if (proof_it->second != expected_proof) {
      return;
    }

    const auto now = std::chrono::steady_clock::now();
    bool busy = false;
    {
      std::lock_guard<std::mutex> lock(lease_mutex_);
      if (!IsLeaseActiveLocked(now)) {
        ClearLeaseLocked();
      }
      busy = !lease_state_.client_id.empty() && lease_state_.client_id != client_id;
    }

    const HostAddresses addresses = CollectHostAddresses();
    const std::string offer_proof = HmacSha256Hex(
        pair_code_,
        JoinSignature({
            "offer",
            kProtocolVersion,
            host_id_,
            client_id,
            client_nonce,
            host_nonce_,
            std::to_string(control_port_),
            std::to_string(lease_ms_),
        }));

    FieldList response{{"proto", kProtocolVersion},
                       {"type", "offer"},
                       {"host_id", host_id_},
                       {"host_name", host_name_},
                       {"client_id", client_id},
                       {"client_nonce", client_nonce},
                       {"host_nonce", host_nonce_},
                       {"control_port", std::to_string(control_port_)},
                       {"discovery_port", std::to_string(discovery_port_)},
                       {"lease_ms", std::to_string(lease_ms_)},
                       {"ready", IsReady() ? "1" : "0"},
                       {"busy", busy ? "1" : "0"}};
    if (!addresses.ipv4.empty()) {
      response.emplace_back("ipv4", addresses.ipv4);
    }
    if (!addresses.ipv6.empty()) {
      response.emplace_back("ipv6", addresses.ipv6);
    }
    response.emplace_back("proof", offer_proof);

    SendDiscoveryReply(socket_fd, BuildMessage(response), peer, peer_len);
    RCLCPP_INFO(this->get_logger(), "Offer sent to client=%s peer=%s family=%s",
                client_id.c_str(), PeerAddressToString(peer).c_str(), AddressFamilyToWire(family).c_str());
  }

  void HandlePairRequest(const std::map<std::string, std::string>& fields,
                         const sockaddr_storage& peer,
                         socklen_t peer_len,
                         int socket_fd,
                         AddressFamily family) {
    const auto host_id_it = fields.find("host_id");
    const auto client_id_it = fields.find("client_id");
    const auto client_nonce_it = fields.find("client_nonce");
    const auto host_nonce_it = fields.find("host_nonce");
    const auto proof_it = fields.find("proof");
    if (host_id_it == fields.end() || client_id_it == fields.end() || client_nonce_it == fields.end() ||
        host_nonce_it == fields.end() || proof_it == fields.end()) {
      return;
    }
    if (host_id_it->second != host_id_ || host_nonce_it->second != host_nonce_) {
      return;
    }

    const std::string client_id = client_id_it->second;
    const std::string client_nonce = client_nonce_it->second;
    const std::string client_name = fields.count("client_name") ? fields.at("client_name") : "RCBridge_Controller";
    const std::string client_address = PeerAddressToString(peer);
    const std::string expected_proof = HmacSha256Hex(
        pair_code_,
        JoinSignature({
            "pair",
            kProtocolVersion,
            host_id_,
            client_id,
            client_nonce,
            host_nonce_,
            std::to_string(control_port_),
            std::to_string(lease_ms_),
        }));
    if (proof_it->second != expected_proof) {
      return;
    }

    const auto now = std::chrono::steady_clock::now();
    std::string session_id;
    bool accept = false;
    bool not_ready = false;
    {
      std::lock_guard<std::mutex> lock(lease_mutex_);
      if (!IsLeaseActiveLocked(now)) {
        ClearLeaseLocked();
      }

      if (require_ready_for_pairing_ && !IsReady()) {
        not_ready = true;
      } else if (lease_state_.client_id.empty() || lease_state_.client_id == client_id) {
        if (lease_state_.session_id.empty()) {
          lease_state_.session_id = GenerateSessionId();
        }
        lease_state_.client_id = client_id;
        lease_state_.client_name = client_name;
        lease_state_.client_nonce = client_nonce;
        lease_state_.client_address = client_address;
        lease_state_.family = family;
        lease_state_.expires_at = now + std::chrono::milliseconds(lease_ms_);
        session_id = lease_state_.session_id;
        accept = true;
      }
    }

    if (accept) {
      const HostAddresses addresses = CollectHostAddresses();
      std::string selected_address = family == AddressFamily::kIpv6 ? addresses.ipv6 : addresses.ipv4;
      if (selected_address.empty()) {
        selected_address = family == AddressFamily::kIpv6 ? addresses.ipv4 : addresses.ipv6;
      }
      if (selected_address.empty()) {
        selected_address = family == AddressFamily::kIpv6 ? "::" : "0.0.0.0";
      }

      const std::string ack_proof = HmacSha256Hex(
          pair_code_,
          JoinSignature({
              "ack",
              kProtocolVersion,
              host_id_,
              client_id,
              client_nonce,
              host_nonce_,
              session_id,
              std::to_string(control_port_),
              std::to_string(lease_ms_),
              AddressFamilyToWire(family),
              selected_address,
          }));
      const std::string payload = BuildMessage({
          {"proto", kProtocolVersion},
          {"type", "pair_ack"},
          {"host_id", host_id_},
          {"session_id", session_id},
          {"lease_ms", std::to_string(lease_ms_)},
          {"control_port", std::to_string(control_port_)},
          {"selected_family", AddressFamilyToWire(family)},
          {"address", selected_address},
          {"proof", ack_proof},
      });
      SendDiscoveryReply(socket_fd, payload, peer, peer_len);
      RCLCPP_INFO(this->get_logger(), "Paired client=%s peer=%s family=%s control=%s:%d",
                  client_id.c_str(), client_address.c_str(), AddressFamilyToWire(family).c_str(),
                  selected_address.c_str(), control_port_);
      return;
    }

    const std::string reason = not_ready ? "not_ready" : "busy";
    const std::string busy_proof = HmacSha256Hex(
        pair_code_,
        JoinSignature({
            "busy",
            kProtocolVersion,
            host_id_,
            client_id,
            client_nonce,
            host_nonce_,
            reason,
            std::to_string(lease_ms_),
        }));
    const std::string payload = BuildMessage({
        {"proto", kProtocolVersion},
        {"type", "pair_busy"},
        {"host_id", host_id_},
        {"lease_ms", std::to_string(lease_ms_)},
        {"reason", reason},
        {"proof", busy_proof},
    });
    SendDiscoveryReply(socket_fd, payload, peer, peer_len);
    RCLCPP_WARN(this->get_logger(), "Rejecting pair request client=%s peer=%s reason=%s",
                client_id.c_str(), client_address.c_str(), reason.c_str());
  }

  void HandleUnpair(const std::map<std::string, std::string>& fields,
                    const sockaddr_storage& peer,
                    AddressFamily family) {
    const auto client_id_it = fields.find("client_id");
    if (client_id_it == fields.end()) {
      return;
    }

    const std::string client_id = client_id_it->second;
    const std::string session_id = fields.count("session_id") ? fields.at("session_id") : "";
    const std::string client_address = PeerAddressToString(peer);

    std::lock_guard<std::mutex> lock(lease_mutex_);
    if (lease_state_.client_id == client_id && lease_state_.client_address == client_address &&
        lease_state_.family == family && (session_id.empty() || lease_state_.session_id == session_id)) {
      RCLCPP_INFO(this->get_logger(), "Unpaired client=%s peer=%s", client_id.c_str(), client_address.c_str());
      ClearLeaseLocked();
    }
  }

  void SendDiscoveryReply(int socket_fd,
                          const std::string& payload,
                          const sockaddr_storage& peer,
                          socklen_t peer_len) {
    if (::sendto(socket_fd, payload.data(), payload.size(), 0,
                 reinterpret_cast<const sockaddr*>(&peer), peer_len) < 0) {
      RCLCPP_WARN(this->get_logger(), "Failed to send discovery reply: %s", std::strerror(errno));
    }
  }

  void ControlLoop() {
    std::vector<uint8_t> buffer(2048);
    while (running_.load() && rclcpp::ok()) {
      pollfd fds[2]{};
      nfds_t nfds = 0;
      if (control_ipv4_socket_ >= 0) {
        fds[nfds++] = pollfd{control_ipv4_socket_, POLLIN, 0};
      }
      if (control_ipv6_socket_ >= 0) {
        fds[nfds++] = pollfd{control_ipv6_socket_, POLLIN, 0};
      }
      if (nfds == 0) {
        return;
      }

      const int ready = ::poll(fds, nfds, kPollTimeoutMs);
      if (ready <= 0) {
        continue;
      }

      for (nfds_t index = 0; index < nfds; ++index) {
        if ((fds[index].revents & POLLIN) == 0) {
          continue;
        }

        sockaddr_storage peer{};
        socklen_t peer_len = sizeof(peer);
        const ssize_t received = ::recvfrom(fds[index].fd, buffer.data(), buffer.size(), 0,
                                            reinterpret_cast<sockaddr*>(&peer), &peer_len);
        if (received <= 0) {
          continue;
        }

        const AddressFamily family = peer.ss_family == AF_INET6 ? AddressFamily::kIpv6 : AddressFamily::kIpv4;
        const std::string client_address = PeerAddressToString(peer);
        const auto now = std::chrono::steady_clock::now();
        {
          std::lock_guard<std::mutex> lock(lease_mutex_);
          if (!IsLeaseActiveLocked(now)) {
            const int64_t now_ms = MonotonicMillis();
            const int64_t last_ble_ms = last_ble_frame_monotonic_ms_.load();
            const bool ble_recent = transport_mode_ == TransportMode::kBleUdp &&
                                    last_ble_ms > 0 && (now_ms - last_ble_ms) <= 2000;
            if (!ble_recent) {
              ClearLeaseLocked();
              continue;
            }
            if (lease_state_.session_id.empty()) {
              lease_state_.session_id = GenerateSessionId();
            }
            lease_state_.client_id = "ble_promoted_udp_peer";
            lease_state_.client_name = "BLEPromotedPeer";
            lease_state_.client_address = client_address;
            lease_state_.family = family;
            lease_state_.expires_at = now + std::chrono::milliseconds(lease_ms_);
            RCLCPP_INFO(this->get_logger(),
                        "Promoted BLE-authorized peer=%s family=%s to UDP lease session=%s",
                        client_address.c_str(),
                        family == AddressFamily::kIpv6 ? "ipv6" : "ipv4",
                        lease_state_.session_id.c_str());
          }
          if (lease_state_.client_address != client_address || lease_state_.family != family) {
            continue;
          }
          lease_state_.expires_at = now + std::chrono::milliseconds(lease_ms_);
        }

        last_udp_frame_monotonic_ms_.store(MonotonicMillis());
        const std::vector<uint8_t> datagram(buffer.begin(), buffer.begin() + received);
        HandleControlPayload(datagram, udp_parser_);
      }
    }
  }

  void HandleControlPayload(const std::vector<uint8_t>& payload,
                            dji_rc_pro_bridge::ProtocolParser& parser) {
    const auto frames = parser.parse(payload);
    for (const auto& frame : frames) {
      if (!frame.verifyCRC()) {
        continue;
      }
      if (frame.pid != static_cast<uint8_t>(dji_rc_pro_bridge::PacketID::CHASSIS_CTRL) || frame.data.size() < 8) {
        continue;
      }
      PublishChassisControl(frame.data);
    }
  }

  void HandleBleFrame(const std_msgs::msg::UInt8MultiArray::SharedPtr msg) {
    if (!AllowsBleTransport()) {
      return;
    }
    const int64_t now_ms = MonotonicMillis();
    const int64_t last_udp_ms = last_udp_frame_monotonic_ms_.load();
    if (last_udp_ms > 0 && (now_ms - last_udp_ms) <= ble_fallback_holdoff_ms_) {
      return;
    }
    last_ble_frame_monotonic_ms_.store(now_ms);
    const std::vector<uint8_t> payload(msg->data.begin(), msg->data.end());
    HandleControlPayload(payload, ble_parser_);
  }

  void PublishTransportStatus() {
    std_msgs::msg::String message;
    const int64_t now_ms = MonotonicMillis();
    const int64_t last_udp_ms = last_udp_frame_monotonic_ms_.load();
    const int64_t last_ble_ms = last_ble_frame_monotonic_ms_.load();
    const bool udp_active = last_udp_ms > 0 && (now_ms - last_udp_ms) <= 2000;
    const bool ble_active = last_ble_ms > 0 && (now_ms - last_ble_ms) <= 2000;
    message.data = BuildMessage({
        {"type", "transport_status"},
        {"host_id", host_id_},
        {"udp_active", udp_active ? "1" : "0"},
        {"ble_active", ble_active ? "1" : "0"},
        {"mcu_ready", mcu_ready_.load() ? "1" : "0"},
    });
    transport_status_publisher_->publish(message);
  }

  void PublishChassisControl(const std::vector<uint8_t>& data) {
    const int16_t v_raw = ReadInt16Le(data, 0);
    const int16_t d_raw = ReadInt16Le(data, 2);
    const int16_t w_raw = ReadInt16Le(data, 4);
    const int16_t theta_raw = ReadInt16Le(data, 6);

    dji_rc_pro_bridge::msg::ChassisCtrl raw_msg;
    raw_msg.v_mps = v_raw;
    raw_msg.d_rad = d_raw;
    raw_msg.w = w_raw;
    raw_msg.heading_angle = theta_raw;
    raw_publisher_->publish(raw_msg);

    const double v = static_cast<double>(v_raw) / 1000.0;
    const double d = static_cast<double>(d_raw) / 1000.0;
    const double w = static_cast<double>(w_raw) / 1000.0;
    const double theta = static_cast<double>(theta_raw) / 1000.0;

    RCLCPP_INFO_THROTTLE(this->get_logger(), *this->get_clock(), 1000,
                         "CHASSIS_CTRL decoded: v=%.3f d=%.3f w=%.3f theta=%.3f",
                         v, d, w, theta);

    geometry_msgs::msg::Twist cmd_vel;
    cmd_vel.linear.x = v * std::cos(d);
    cmd_vel.linear.y = v * std::sin(d);
    cmd_vel.angular.z = w;
    cmd_vel_publisher_->publish(cmd_vel);
  }

  void CleanupLease() {
    const auto now = std::chrono::steady_clock::now();
    std::lock_guard<std::mutex> lock(lease_mutex_);
    if (!IsLeaseActiveLocked(now)) {
      ClearLeaseLocked();
    }
  }
};

int main(int argc, char** argv) {
  rclcpp::init(argc, argv);
  auto node = std::make_shared<DjiRcProGatewayNode>();
  rclcpp::spin(node);
  rclcpp::shutdown();
  return 0;
}
