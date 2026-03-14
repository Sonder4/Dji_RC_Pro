#include <cstdlib>
#include <iostream>

#include "dji_rc_pro_bridge/client_address_snapshot.hpp"

int main() {
  using dji_rc_pro_bridge::ClientAddressFamily;
  using dji_rc_pro_bridge::ClientAddressSnapshot;
  using dji_rc_pro_bridge::MergeClientAddressSnapshot;
  using dji_rc_pro_bridge::MergeNonEmptyClientAddresses;

  {
    const ClientAddressSnapshot current{
        "10.202.200.141",
        "",
    };
    const ClientAddressSnapshot merged = MergeClientAddressSnapshot(
        current, ClientAddressFamily::kIpv6, "240c:c901:2:36d:b9db:45ad:a6fb:1af5");
    if (merged.ipv4 != "10.202.200.141") {
      std::cerr << "expected existing IPv4 to be preserved" << std::endl;
      return EXIT_FAILURE;
    }
    if (merged.ipv6 != "240c:c901:2:36d:b9db:45ad:a6fb:1af5") {
      std::cerr << "expected IPv6 to be updated" << std::endl;
      return EXIT_FAILURE;
    }
  }

  {
    const ClientAddressSnapshot current{
        "",
        "240c:c901:2:36d:b9db:45ad:a6fb:1af5",
    };
    const ClientAddressSnapshot merged = MergeClientAddressSnapshot(
        current, ClientAddressFamily::kIpv4, "10.202.200.141");
    if (merged.ipv4 != "10.202.200.141") {
      std::cerr << "expected IPv4 to be updated" << std::endl;
      return EXIT_FAILURE;
    }
    if (merged.ipv6 != "240c:c901:2:36d:b9db:45ad:a6fb:1af5") {
      std::cerr << "expected existing IPv6 to be preserved" << std::endl;
      return EXIT_FAILURE;
    }
  }

  {
    const ClientAddressSnapshot current{
        "",
        "240c:c901:2:36d:b9db:45ad:a6fb:1af5",
    };
    const ClientAddressSnapshot merged = MergeNonEmptyClientAddresses(
        current,
        ClientAddressSnapshot{
            "10.202.200.141",
            "",
        });
    if (merged.ipv4 != "10.202.200.141") {
      std::cerr << "expected non-empty incoming IPv4 to be merged" << std::endl;
      return EXIT_FAILURE;
    }
    if (merged.ipv6 != "240c:c901:2:36d:b9db:45ad:a6fb:1af5") {
      std::cerr << "expected existing IPv6 to be preserved during merge" << std::endl;
      return EXIT_FAILURE;
    }
  }

  return EXIT_SUCCESS;
}
