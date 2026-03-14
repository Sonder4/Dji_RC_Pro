#pragma once

#include <string>

namespace dji_rc_pro_bridge {

enum class ClientAddressFamily {
  kIpv4,
  kIpv6,
};

struct ClientAddressSnapshot {
  std::string ipv4;
  std::string ipv6;
};

inline ClientAddressSnapshot MergeClientAddressSnapshot(
    const ClientAddressSnapshot& current,
    ClientAddressFamily family,
    const std::string& address) {
  ClientAddressSnapshot merged = current;
  if (family == ClientAddressFamily::kIpv4) {
    merged.ipv4 = address;
  } else {
    merged.ipv6 = address;
  }
  return merged;
}

inline ClientAddressSnapshot MergeNonEmptyClientAddresses(
    const ClientAddressSnapshot& current,
    const ClientAddressSnapshot& incoming) {
  ClientAddressSnapshot merged = current;
  if (!incoming.ipv4.empty()) {
    merged.ipv4 = incoming.ipv4;
  }
  if (!incoming.ipv6.empty()) {
    merged.ipv6 = incoming.ipv6;
  }
  return merged;
}

}  // namespace dji_rc_pro_bridge
