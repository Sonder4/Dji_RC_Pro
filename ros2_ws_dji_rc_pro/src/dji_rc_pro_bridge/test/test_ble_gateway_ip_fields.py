import importlib.util
from pathlib import Path


MODULE_PATH = Path(__file__).resolve().parents[1] / "src" / "tools" / "dji_rc_pro_ble_gateway.py"


def load_module():
    spec = importlib.util.spec_from_file_location("dji_rc_pro_ble_gateway", MODULE_PATH)
    module = importlib.util.module_from_spec(spec)
    assert spec.loader is not None
    spec.loader.exec_module(module)
    return module


def test_extract_client_ip_fields_accepts_compact_probe_4_and_6_keys():
    module = load_module()
    node = object.__new__(module.Ros2BleGatewayNode)

    client_ipv4, client_ipv6 = module.Ros2BleGatewayNode._extract_client_ip_fields(
        node,
        {
            "4": "10.10.0.2",
            "6": "240c::2",
        },
    )

    assert client_ipv4 == "10.10.0.2"
    assert client_ipv6 == "240c::2"


def test_compact_offer_includes_host_ipv4_and_ipv6_fields():
    module = load_module()

    class Logger:
        def info(self, *_args, **_kwargs):
            return None

        def warning(self, *_args, **_kwargs):
            return None

    node = object.__new__(module.Ros2BleGatewayNode)
    node.pair_code = "NCURC2026"
    node.transport_mode = module.TRANSPORT_MODE_BLE_UDP
    node.host_id = "rc26-host"
    node.host_nonce = "host-nonce"
    node.control_port = 1387
    node.lease_ms = 5000
    node.pending_offer = {}
    node.ipv4_address = "10.202.168.216"
    node.ipv6_address = "240c:c901:2:2a5c::230"
    node.get_logger = lambda: Logger()
    node._is_ready = lambda: True
    node._lease_active = lambda: False
    node._choose_endpoint = lambda support_ipv4, support_ipv6: ("ipv6", "240c:c901:2:2a5c::230", "ipv6")
    node._extract_client_ip_fields = lambda fields: module.Ros2BleGatewayNode._extract_client_ip_fields(node, fields)

    client_id = "73a521093ed98861"
    client_nonce = "client-nonce"
    support_mask = "3"
    client_ipv4 = "10.202.200.141"
    client_ipv6 = "240c:c901:2:36d::100"
    proof = module.compact_hmac_token(
        node.pair_code,
        ["p", module.BLE_COMPACT_VERSION, client_id, client_nonce, support_mask, client_ipv4, client_ipv6],
    )

    response = module.Ros2BleGatewayNode._handle_compact_probe(
        node,
        {
            "v": module.BLE_COMPACT_VERSION,
            "t": "p",
            "c": client_id,
            "n": client_nonce,
            "x": support_mask,
            "4": client_ipv4,
            "6": client_ipv6,
            "m": proof,
        },
    )

    assert response is not None
    fields = module.parse_fields(response.decode("utf-8"))
    assert fields["host_ipv4"] == "10.202.168.216"
    assert fields["host_ipv6"] == "240c:c901:2:2a5c::230"


def test_build_ble_session_status_message_includes_client_ipv4_and_ipv6():
    module = load_module()
    node = object.__new__(module.Ros2BleGatewayNode)
    node.host_id = "rc26-host"
    node.transport_mode = module.TRANSPORT_MODE_BLE_UDP
    node.udp_active = True
    node.mcu_ready = True
    node.ipv4_address = "10.202.168.216"
    node.ipv6_address = "240c:c901:2:2a5c::230"
    node._ble_state_value = lambda: "udp_primary"
    node._lease_active = lambda: True
    node.lease = module.LeaseState(
        client_id="client-1",
        client_ipv4="10.202.200.141",
        client_ipv6="240c:c901:2:36d::100",
        session_id="session-1",
        selected_family="ipv6",
        selected_address="240c:c901:2:2a5c::230",
    )

    payload = module.Ros2BleGatewayNode.build_ble_session_status_message(node)
    fields = module.parse_fields(payload)

    assert fields["type"] == "ble_session_status"
    assert fields["client_ipv4"] == "10.202.200.141"
    assert fields["client_ipv6"] == "240c:c901:2:36d::100"
    assert fields["host_ipv4"] == "10.202.168.216"
    assert fields["host_ipv6"] == "240c:c901:2:2a5c::230"
