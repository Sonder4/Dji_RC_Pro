#!/usr/bin/env python3

import base64
import hashlib
import hmac
import json
import socket
import subprocess
import threading
import time
import uuid
from dataclasses import dataclass
from typing import Dict, Optional, Tuple

import dbus
import dbus.exceptions
import dbus.mainloop.glib
import dbus.service
from gi.repository import GLib
import rclpy
from rclpy.node import Node
from std_msgs.msg import Header, String, UInt8MultiArray

BLUEZ_SERVICE_NAME = 'org.bluez'
DBUS_OM_IFACE = 'org.freedesktop.DBus.ObjectManager'
DBUS_PROP_IFACE = 'org.freedesktop.DBus.Properties'
GATT_MANAGER_IFACE = 'org.bluez.GattManager1'
LE_ADVERTISING_MANAGER_IFACE = 'org.bluez.LEAdvertisingManager1'
GATT_SERVICE_IFACE = 'org.bluez.GattService1'
GATT_CHRC_IFACE = 'org.bluez.GattCharacteristic1'
LE_ADVERTISEMENT_IFACE = 'org.bluez.LEAdvertisement1'

PROTOCOL_VERSION = 'RC26_DISCOVERY/2'
DEFAULT_PAIR_CODE = 'NCURC2026'
DEFAULT_CONTROL_PORT = 1387
DEFAULT_LEASE_MS = 5000
DEVICE_NAME_PREFIX = 'RC26-ROS2'

SERVICE_UUID = '8f231100-6e52-4f7c-9b16-1b20c1a50001'
PAIR_CONTROL_UUID = '8f231100-6e52-4f7c-9b16-1b20c1a50002'
NETWORK_INFO_UUID = '8f231100-6e52-4f7c-9b16-1b20c1a50003'
CONTROL_STREAM_UUID = '8f231100-6e52-4f7c-9b16-1b20c1a50004'
STATUS_UUID = '8f231100-6e52-4f7c-9b16-1b20c1a50005'
BLE_COMPACT_VERSION = '1'
TRANSPORT_MODE_UDP_ONLY = 'udp_only'
TRANSPORT_MODE_BLE_ONLY = 'ble_only'
TRANSPORT_MODE_BLE_UDP = 'ble_udp'


class InvalidArgsException(dbus.exceptions.DBusException):
    _dbus_error_name = 'org.freedesktop.DBus.Error.InvalidArgs'


class NotSupportedException(dbus.exceptions.DBusException):
    _dbus_error_name = 'org.bluez.Error.NotSupported'


class FailedException(dbus.exceptions.DBusException):
    _dbus_error_name = 'org.bluez.Error.Failed'


class Advertisement(dbus.service.Object):
    PATH_BASE = '/org/bluez/dji_rc_pro/advertisement'

    def __init__(self, bus, index: int, local_name: str):
        self.path = f'{self.PATH_BASE}{index}'
        self.bus = bus
        self.local_name = local_name
        self.service_uuids = [SERVICE_UUID]
        super().__init__(bus, self.path)

    def get_properties(self):
        return {
            LE_ADVERTISEMENT_IFACE: {
                'Type': 'peripheral',
                'ServiceUUIDs': dbus.Array(self.service_uuids, signature='s'),
                'LocalName': dbus.String(self.local_name),
                'Includes': dbus.Array(['tx-power'], signature='s'),
            }
        }

    def get_path(self):
        return dbus.ObjectPath(self.path)

    @dbus.service.method(DBUS_PROP_IFACE, in_signature='s', out_signature='a{sv}')
    def GetAll(self, interface):
        if interface != LE_ADVERTISEMENT_IFACE:
            raise InvalidArgsException()
        return self.get_properties()[LE_ADVERTISEMENT_IFACE]

    @dbus.service.method(LE_ADVERTISEMENT_IFACE, in_signature='', out_signature='')
    def Release(self):
        pass


class Application(dbus.service.Object):
    def __init__(self, bus):
        self.path = '/'
        self.bus = bus
        self.services = []
        super().__init__(bus, self.path)

    def get_path(self):
        return dbus.ObjectPath(self.path)

    def add_service(self, service):
        self.services.append(service)

    @dbus.service.method(DBUS_OM_IFACE, out_signature='a{oa{sa{sv}}}')
    def GetManagedObjects(self):
        response = {}
        for service in self.services:
            response[service.get_path()] = service.get_properties()
            for characteristic in service.characteristics:
                response[characteristic.get_path()] = characteristic.get_properties()
        return response


class Service(dbus.service.Object):
    PATH_BASE = '/org/bluez/dji_rc_pro/service'

    def __init__(self, bus, index: int, uuid_str: str, primary: bool):
        self.path = f'{self.PATH_BASE}{index}'
        self.bus = bus
        self.uuid = uuid_str
        self.primary = primary
        self.characteristics = []
        super().__init__(bus, self.path)

    def get_properties(self):
        return {
            GATT_SERVICE_IFACE: {
                'UUID': self.uuid,
                'Primary': self.primary,
                'Characteristics': dbus.Array(
                    [characteristic.get_path() for characteristic in self.characteristics],
                    signature='o',
                ),
            }
        }

    def get_path(self):
        return dbus.ObjectPath(self.path)

    def add_characteristic(self, characteristic):
        self.characteristics.append(characteristic)

    @dbus.service.method(DBUS_PROP_IFACE, in_signature='s', out_signature='a{sv}')
    def GetAll(self, interface):
        if interface != GATT_SERVICE_IFACE:
            raise InvalidArgsException()
        return self.get_properties()[GATT_SERVICE_IFACE]


class Characteristic(dbus.service.Object):
    def __init__(self, bus, index: int, uuid_str: str, flags, service: Service):
        self.path = f'{service.path}/char{index}'
        self.bus = bus
        self.uuid = uuid_str
        self.flags = flags
        self.service = service
        self.notifying = False
        self._value = bytes()
        super().__init__(bus, self.path)

    def get_properties(self):
        return {
            GATT_CHRC_IFACE: {
                'Service': self.service.get_path(),
                'UUID': self.uuid,
                'Flags': dbus.Array(self.flags, signature='s'),
            }
        }

    def get_path(self):
        return dbus.ObjectPath(self.path)

    def _dbus_value(self):
        return dbus.Array([dbus.Byte(byte) for byte in self._value], signature='y')

    def set_value(self, value: bytes):
        self._value = value
        if self.notifying:
            self.PropertiesChanged(GATT_CHRC_IFACE, {'Value': self._dbus_value()}, [])

    @dbus.service.method(DBUS_PROP_IFACE, in_signature='s', out_signature='a{sv}')
    def GetAll(self, interface):
        if interface != GATT_CHRC_IFACE:
            raise InvalidArgsException()
        return self.get_properties()[GATT_CHRC_IFACE]

    @dbus.service.method(GATT_CHRC_IFACE, in_signature='a{sv}', out_signature='ay')
    def ReadValue(self, options):
        raise NotSupportedException()

    @dbus.service.method(GATT_CHRC_IFACE, in_signature='aya{sv}', out_signature='')
    def WriteValue(self, value, options):
        raise NotSupportedException()

    @dbus.service.method(GATT_CHRC_IFACE, in_signature='', out_signature='')
    def StartNotify(self):
        self.notifying = True
        if hasattr(self, 'gateway'):
            self.gateway.get_logger().info(f'BLE notify enabled uuid={self.uuid}')

    @dbus.service.method(GATT_CHRC_IFACE, in_signature='', out_signature='')
    def StopNotify(self):
        self.notifying = False
        if hasattr(self, 'gateway'):
            self.gateway.get_logger().info(f'BLE notify disabled uuid={self.uuid}')

    @dbus.service.signal(DBUS_PROP_IFACE, signature='sa{sv}as')
    def PropertiesChanged(self, interface, changed, invalidated):
        pass


@dataclass
class LeaseState:
    client_id: str = ''
    client_name: str = ''
    client_nonce: str = ''
    session_id: str = ''
    selected_family: str = ''
    selected_address: str = ''
    expires_at_monotonic: float = 0.0
    support_ipv4: bool = True
    support_ipv6: bool = True


class PairControlCharacteristic(Characteristic):
    def __init__(self, bus, index: int, service: Service, gateway: 'Ros2BleGatewayNode'):
        flags = ['write', 'write-without-response']
        if gateway.transport_mode != TRANSPORT_MODE_BLE_ONLY:
            flags = ['read', 'write', 'write-without-response', 'notify']
        super().__init__(bus, index, PAIR_CONTROL_UUID, flags, service)
        self.gateway = gateway

    @dbus.service.method(GATT_CHRC_IFACE, in_signature='a{sv}', out_signature='ay')
    def ReadValue(self, options):
        if self.gateway.transport_mode == TRANSPORT_MODE_BLE_ONLY:
            raise NotSupportedException()
        self.gateway.get_logger().info(f'BLE read pair_control bytes={len(self._value)}')
        return self._dbus_value()

    @dbus.service.method(GATT_CHRC_IFACE, in_signature='aya{sv}', out_signature='')
    def WriteValue(self, value, options):
        self.gateway.get_logger().info(f'BLE write pair_control bytes={len(value)}')
        response = self.gateway.handle_pair_control_write(bytes(value))
        if response is not None:
            self.gateway.get_logger().info(f'BLE notify pair_control bytes={len(response)}')
            self.set_value(response)


class NetworkInfoCharacteristic(Characteristic):
    def __init__(self, bus, index: int, service: Service, gateway: 'Ros2BleGatewayNode'):
        super().__init__(bus, index, NETWORK_INFO_UUID, ['read'], service)
        self.gateway = gateway

    @dbus.service.method(GATT_CHRC_IFACE, in_signature='a{sv}', out_signature='ay')
    def ReadValue(self, options):
        self.gateway.touch_lease()
        self._value = self.gateway.build_network_info_payload()
        self.gateway.get_logger().info(f'BLE read network_info bytes={len(self._value)}')
        return self._dbus_value()


class StatusCharacteristic(Characteristic):
    def __init__(self, bus, index: int, service: Service, gateway: 'Ros2BleGatewayNode'):
        super().__init__(bus, index, STATUS_UUID, ['read'], service)
        self.gateway = gateway

    @dbus.service.method(GATT_CHRC_IFACE, in_signature='a{sv}', out_signature='ay')
    def ReadValue(self, options):
        self.gateway.touch_lease()
        self._value = self.gateway.build_status_payload()
        self.gateway.get_logger().info(f'BLE read status bytes={len(self._value)}')
        return self._dbus_value()


class ControlStreamCharacteristic(Characteristic):
    def __init__(self, bus, index: int, service: Service, gateway: 'Ros2BleGatewayNode'):
        super().__init__(bus, index, CONTROL_STREAM_UUID, ['write', 'write-without-response'], service)
        self.gateway = gateway

    @dbus.service.method(GATT_CHRC_IFACE, in_signature='aya{sv}', out_signature='')
    def WriteValue(self, value, options):
        self.gateway.get_logger().info(f'BLE write control_stream bytes={len(value)}')
        self.gateway.handle_control_frame(bytes(value))


class Ros2GatewayService(Service):
    def __init__(self, bus, index: int, gateway: 'Ros2BleGatewayNode'):
        super().__init__(bus, index, SERVICE_UUID, True)
        self.pair_control = PairControlCharacteristic(bus, 0, self, gateway)
        if gateway.transport_mode == TRANSPORT_MODE_BLE_ONLY:
            self.network_info = None
            self.control_stream = ControlStreamCharacteristic(bus, 1, self, gateway)
            self.status = None
        else:
            self.network_info = NetworkInfoCharacteristic(bus, 1, self, gateway)
            self.control_stream = ControlStreamCharacteristic(bus, 2, self, gateway)
            self.status = StatusCharacteristic(bus, 3, self, gateway)
        self.add_characteristic(self.pair_control)
        self.add_characteristic(self.control_stream)
        if self.network_info is not None:
            self.add_characteristic(self.network_info)
        if self.status is not None:
            self.add_characteristic(self.status)


def parse_fields(payload: str) -> Dict[str, str]:
    result: Dict[str, str] = {}
    for raw_line in payload.splitlines():
        line = raw_line.strip()
        if not line or '=' not in line:
            continue
        key, value = line.split('=', 1)
        result[key] = value
    return result


def build_message(fields: Dict[str, Optional[str]]) -> bytes:
    lines = []
    for key, value in fields.items():
        if value is None:
            continue
        lines.append(f'{key}={value}')
    return ('\n'.join(lines) + '\n\n').encode('utf-8')


def join_signature(parts) -> str:
    return '|'.join(parts)


def hmac_hex(pair_code: str, payload: str) -> str:
    return hmac.new(pair_code.encode('utf-8'), payload.encode('utf-8'), hashlib.sha256).hexdigest()


def compact_hmac_token(pair_code: str, parts) -> str:
    digest = hmac.new(
        pair_code.encode('utf-8'),
        '|'.join(parts).encode('utf-8'),
        hashlib.sha256,
    ).digest()[:16]
    return base64.urlsafe_b64encode(digest).decode('utf-8').rstrip('=')


def new_wire_token() -> str:
    return uuid.uuid4().hex[:16]


def compact_family_to_wire(family: Optional[str]) -> Optional[str]:
    if family == 'ipv4':
        return '4'
    if family == 'ipv6':
        return '6'
    return None


def compact_support_mask(support_ipv4: bool, support_ipv6: bool) -> str:
    mask = (1 if support_ipv4 else 0) | (2 if support_ipv6 else 0)
    return str(mask)


def derive_host_id(host_name: str) -> str:
    try:
        with open('/etc/machine-id', 'r', encoding='utf-8') as handle:
            machine_id = handle.readline().strip()
        if machine_id:
            return f'rc26-{hashlib.sha256(machine_id.encode("utf-8")).hexdigest()[:16]}'
    except Exception:
        pass
    return f'rc26-{hashlib.sha256(host_name.encode("utf-8")).hexdigest()[:16]}'


def find_adapter(bus) -> str:
    manager = dbus.Interface(bus.get_object(BLUEZ_SERVICE_NAME, '/'), DBUS_OM_IFACE)
    objects = manager.GetManagedObjects()
    for path, interfaces in objects.items():
        if GATT_MANAGER_IFACE in interfaces and LE_ADVERTISING_MANAGER_IFACE in interfaces:
            return path
    raise RuntimeError('No BLE adapter with GATT peripheral support found')


def collect_host_addresses() -> Tuple[Optional[str], Optional[str]]:
    ipv4 = None
    ipv6 = None
    try:
        result = subprocess.run(
            ['ip', '-j', 'addr', 'show', 'up'],
            check=True,
            capture_output=True,
            text=True,
        )
        payload = json.loads(result.stdout)
    except Exception:
        return None, None

    for interface in payload:
        if 'LOOPBACK' in interface.get('flags', []):
            continue
        for address_info in interface.get('addr_info', []):
            family = address_info.get('family')
            scope = address_info.get('scope')
            local = address_info.get('local')
            if not local:
                continue
            if family == 'inet' and scope == 'global' and not ipv4:
                ipv4 = local
            elif family == 'inet6' and scope == 'global' and not ipv6 and not local.startswith('fe80:'):
                ipv6 = local.split('%', 1)[0]
        if ipv4 and ipv6:
            break
    return ipv4, ipv6


class Ros2BleGatewayNode(Node):
    def __init__(self):
        super().__init__('dji_rc_pro_ble_gateway')
        host_name_default = socket.gethostname()
        host_name = self.declare_parameter('host_name', host_name_default).value or host_name_default
        host_id = self.declare_parameter('host_id', '').value or derive_host_id(host_name)
        advertise_name_default = f'{DEVICE_NAME_PREFIX}-{host_name[:12]}'
        advertise_name = self.declare_parameter('advertise_name', advertise_name_default).value or advertise_name_default

        self.host_name = host_name
        self.host_id = host_id
        self.advertise_name = advertise_name[:28]
        self.pair_code = str(self.declare_parameter('pair_code', DEFAULT_PAIR_CODE).value)
        self.transport_mode = str(self.declare_parameter('transport_mode', TRANSPORT_MODE_BLE_UDP).value or TRANSPORT_MODE_BLE_UDP)
        self.control_port = int(self.declare_parameter('control_port', DEFAULT_CONTROL_PORT).value)
        self.lease_ms = int(self.declare_parameter('lease_ms', DEFAULT_LEASE_MS).value)
        self.require_ready_for_pairing = bool(self.declare_parameter('require_ready_for_pairing', False).value)
        self.transport_status_topic = str(self.declare_parameter('transport_status_topic', '/dji_rc_pro_bridge/transport_status').value)
        self.ble_frame_topic = str(self.declare_parameter('ble_frame_topic', '/dji_rc_pro_bridge/ble/control_frame').value)
        self.status_topic = str(self.declare_parameter('status_topic', '/mcu_comm_node/status').value)

        self.udp_active = False
        self.mcu_ready = False
        self.host_nonce = new_wire_token()
        self.lease = LeaseState()
        self.pending_offer: Dict[str, str] = {}
        self.last_transport_status: Dict[str, str] = {}
        self.last_address_refresh_monotonic = 0.0
        self.last_ble_control_log_monotonic = 0.0
        self.last_ble_drop_log_monotonic = 0.0
        self.ipv4_address: Optional[str] = None
        self.ipv6_address: Optional[str] = None
        self._refresh_addresses(force=True)

        self.ble_frame_publisher = self.create_publisher(UInt8MultiArray, self.ble_frame_topic, 10)
        self.create_subscription(String, self.transport_status_topic, self._on_transport_status, 10)
        self.create_subscription(Header, self.status_topic, self._on_mcu_status, 10)
        self.create_timer(0.5, self._on_housekeeping_timer)

        self.ble_available = False
        self.bus = None
        self.adapter_path = None
        self.application = None
        self.service = None
        self.advertisement = None
        self.glib_loop = None
        self.glib_thread = None
        self.glib_ready = threading.Event()
        self.glib_init_error = None
        if not self._ble_enabled():
            self.get_logger().info('Transport mode udp_only: BLE GATT peripheral disabled')
            return
        try:
            self.glib_thread = threading.Thread(target=self._run_glib_loop, daemon=True)
            self.glib_thread.start()
            if not self.glib_ready.wait(timeout=5.0):
                raise RuntimeError('GLib main loop did not become ready in time')
            if self.glib_init_error is not None:
                raise RuntimeError(str(self.glib_init_error))
            self.ble_available = True
            self.get_logger().info(
                f'BLE gateway ready host_id={self.host_id} host_name={self.host_name} '
                f'advertise_name={self.advertise_name} control={self.control_port} '
                f'ipv4={self.ipv4_address or "-"} ipv6={self.ipv6_address or "-"}'
            )
        except Exception as error:
            self.get_logger().error(
                f'BLE peripheral unavailable: {error}. Running UDP-only bridge and keeping BLE topics idle.'
            )

    def _ble_enabled(self) -> bool:
        return self.transport_mode != TRANSPORT_MODE_UDP_ONLY

    def _run_glib_loop(self):
        try:
            dbus.mainloop.glib.threads_init()
            dbus.mainloop.glib.DBusGMainLoop(set_as_default=True)
            self.bus = dbus.SystemBus()
            self.adapter_path = find_adapter(self.bus)
            self.application = Application(self.bus)
            self.service = Ros2GatewayService(self.bus, 0, self)
            self.application.add_service(self.service)
            characteristic_uuids = ','.join(
                characteristic.uuid for characteristic in self.service.characteristics
            )
            self.get_logger().info(
                f'BLE service composition transport_mode={self.transport_mode} '
                f'characteristics=[{characteristic_uuids}]'
            )
            self.advertisement = Advertisement(self.bus, 0, self.advertise_name)
            self.glib_loop = GLib.MainLoop()
            self._register_ble_stack()
        except Exception as error:
            self.glib_init_error = error
            self.glib_ready.set()
            return

        self.glib_ready.set()
        self.glib_loop.run()

    def destroy_node(self):
        try:
            if self.bus is not None and self.adapter_path is not None and self.advertisement is not None:
                ad_manager = dbus.Interface(self.bus.get_object(BLUEZ_SERVICE_NAME, self.adapter_path), LE_ADVERTISING_MANAGER_IFACE)
                ad_manager.UnregisterAdvertisement(self.advertisement.get_path())
        except Exception:
            pass
        try:
            if self.bus is not None and self.adapter_path is not None and self.application is not None:
                gatt_manager = dbus.Interface(self.bus.get_object(BLUEZ_SERVICE_NAME, self.adapter_path), GATT_MANAGER_IFACE)
                gatt_manager.UnregisterApplication(self.application.get_path())
        except Exception:
            pass
        try:
            if self.glib_loop is not None and self.glib_loop.is_running():
                self.glib_loop.quit()
        except Exception:
            pass
        super().destroy_node()

    def _register_ble_stack(self):
        gatt_manager = dbus.Interface(self.bus.get_object(BLUEZ_SERVICE_NAME, self.adapter_path), GATT_MANAGER_IFACE)
        ad_manager = dbus.Interface(self.bus.get_object(BLUEZ_SERVICE_NAME, self.adapter_path), LE_ADVERTISING_MANAGER_IFACE)
        self.get_logger().info(f'Registering BLE GATT application on adapter {self.adapter_path}')
        self._wait_for_bluez_registration(
            lambda reply_handler, error_handler: gatt_manager.RegisterApplication(
                self.application.get_path(),
                {},
                reply_handler=reply_handler,
                error_handler=error_handler,
            ),
            'GATT application',
        )
        self.get_logger().info(f'Registering BLE advertisement name={self.advertise_name}')
        self._wait_for_bluez_registration(
            lambda reply_handler, error_handler: ad_manager.RegisterAdvertisement(
                self.advertisement.get_path(),
                {},
                reply_handler=reply_handler,
                error_handler=error_handler,
            ),
            'LE advertisement',
        )

    def _wait_for_bluez_registration(self, register_call, label: str, timeout_sec: float = 5.0):
        completed = threading.Event()
        state = {'error': None}

        def on_success():
            completed.set()

        def on_error(error):
            state['error'] = error
            completed.set()

        register_call(on_success, on_error)
        context = GLib.MainContext.default()
        deadline = time.monotonic() + timeout_sec
        while not completed.is_set():
            if time.monotonic() >= deadline:
                raise RuntimeError(f'{label} registration timed out after {timeout_sec:.1f}s')
            context.iteration(True)
        if state['error'] is not None:
            raise RuntimeError(f'{label} registration failed: {state["error"]}')

    def _on_transport_status(self, msg: String):
        fields = parse_fields(msg.data)
        self.last_transport_status = fields
        self.udp_active = fields.get('udp_active', '0') == '1'

    def _on_mcu_status(self, msg: Header):
        self.mcu_ready = msg.frame_id == 'connected'

    def _on_housekeeping_timer(self):
        self._refresh_addresses()
        if not self._lease_active() and self.lease.client_id:
            self.get_logger().info(f'BLE lease expired for client={self.lease.client_id}')
            self._clear_lease()

    def _refresh_addresses(self, force: bool = False):
        now = time.monotonic()
        if not force and now - self.last_address_refresh_monotonic < 2.0:
            return
        self.last_address_refresh_monotonic = now
        self.ipv4_address, self.ipv6_address = collect_host_addresses()

    def _lease_active(self) -> bool:
        return bool(self.lease.client_id) and time.monotonic() < self.lease.expires_at_monotonic

    def touch_lease(self):
        if self._lease_active():
            self.lease.expires_at_monotonic = time.monotonic() + self.lease_ms / 1000.0

    def _clear_lease(self):
        self.lease = LeaseState()
        self.pending_offer = {}

    def _is_ready(self) -> bool:
        return (not self.require_ready_for_pairing) or self.mcu_ready

    def _choose_endpoint(self, support_ipv4: bool, support_ipv6: bool) -> Tuple[Optional[str], Optional[str], Optional[str]]:
        self._refresh_addresses(force=True)
        if support_ipv6 and self.ipv6_address:
            return 'ipv6', self.ipv6_address, 'ipv6'
        if support_ipv4 and self.ipv4_address:
            return 'ipv4', self.ipv4_address, 'ipv4'
        if self.ipv6_address:
            return 'ipv6', self.ipv6_address, 'ipv6'
        if self.ipv4_address:
            return 'ipv4', self.ipv4_address, 'ipv4'
        return None, None, None

    def _current_selected_endpoint(self) -> Tuple[Optional[str], Optional[str], Optional[str]]:
        support_ipv4 = self.lease.support_ipv4 if self.lease.client_id else True
        support_ipv6 = self.lease.support_ipv6 if self.lease.client_id else True
        selected_family, selected_address, preferred_family = self._choose_endpoint(support_ipv4, support_ipv6)
        if self.lease.client_id and selected_family and selected_address:
            self.lease.selected_family = selected_family
            self.lease.selected_address = selected_address
        return selected_family, selected_address, preferred_family

    def _authorize_ble_only_client(
        self,
        client_id: str,
        client_name: str,
        client_nonce: str,
        support_ipv4: bool,
        support_ipv6: bool,
    ) -> bool:
        if not self._is_ready():
            self.get_logger().warning(f'BLE-only auth rejected client={client_id}: host not ready')
            return False
        if self._lease_active() and self.lease.client_id != client_id:
            self.get_logger().warning(
                f'BLE-only auth rejected client={client_id}: busy with client={self.lease.client_id}'
            )
            return False

        selected_family, selected_address, _ = self._choose_endpoint(support_ipv4, support_ipv6)
        session_id = self.lease.session_id if self._lease_active() and self.lease.client_id == client_id else new_wire_token()
        self.lease = LeaseState(
            client_id=client_id,
            client_name=client_name,
            client_nonce=client_nonce,
            session_id=session_id,
            selected_family=selected_family or '',
            selected_address=selected_address or '',
            expires_at_monotonic=time.monotonic() + self.lease_ms / 1000.0,
            support_ipv4=support_ipv4,
            support_ipv6=support_ipv6,
        )
        self.pending_offer = {}
        endpoint = f'{selected_family}:{selected_address}' if selected_family and selected_address else '-'
        self.get_logger().info(
            f'BLE-only auth accepted client={client_id} session={session_id} endpoint={endpoint}'
        )
        return True

    def build_network_info_payload(self) -> bytes:
        selected_family, selected_address, preferred_family = self._current_selected_endpoint()
        return build_message({
            'v': BLE_COMPACT_VERSION,
            't': 'n',
            'h': self.host_id,
            's': self.lease.session_id or None,
            '4': self.ipv4_address,
            '6': self.ipv6_address,
            'pf': compact_family_to_wire(preferred_family),
            'f': compact_family_to_wire(selected_family),
            'a': selected_address,
            'p': str(self.control_port),
            'l': str(self.lease_ms),
            'b': '1' if self._lease_active() else '0',
        })

    def build_status_payload(self) -> bytes:
        selected_family, selected_address, _ = self._current_selected_endpoint()
        return build_message({
            'v': BLE_COMPACT_VERSION,
            't': 's',
            'h': self.host_id,
            's': self.lease.session_id or None,
            'f': compact_family_to_wire(selected_family),
            'a': selected_address,
            'p': str(self.control_port),
            'u': '1' if self.udp_active else '0',
            'b': '1' if self._lease_active() else '0',
            'k': '1' if self.mcu_ready else '0',
            'd': '1' if self._lease_active() else '0',
        })

    def handle_pair_control_write(self, payload: bytes) -> Optional[bytes]:
        if not self._ble_enabled():
            return None
        try:
            fields = parse_fields(payload.decode('utf-8'))
        except Exception:
            self.get_logger().warning('Invalid pair-control payload encoding')
            return None

        message_type = fields.get('type')
        compact_type = fields.get('t')
        if fields.get('v') == BLE_COMPACT_VERSION and compact_type:
            return self._handle_compact_pair_message(fields)
        if fields.get('proto') != PROTOCOL_VERSION:
            return None
        if message_type == 'probe':
            return self._handle_probe(fields)
        if message_type == 'pair_request':
            return self._handle_pair_request(fields)
        if message_type == 'unpair':
            client_id = fields.get('client_id', '')
            if client_id and client_id == self.lease.client_id:
                self.get_logger().info(f'BLE client requested unpair: {client_id}')
                self._clear_lease()
            return None
        return None

    def _handle_compact_pair_message(self, fields: Dict[str, str]) -> Optional[bytes]:
        message_type = fields.get('t')
        if message_type == 'p':
            return self._handle_compact_probe(fields)
        if message_type == 'r':
            return self._handle_compact_pair_request(fields)
        return None

    def _handle_compact_probe(self, fields: Dict[str, str]) -> Optional[bytes]:
        client_id = fields.get('c', '')
        client_nonce = fields.get('n', '')
        support_mask = fields.get('x', '3')
        proof = fields.get('m', '')
        if not client_id or not client_nonce or not proof:
            return None

        expected = compact_hmac_token(
            self.pair_code,
            ['p', BLE_COMPACT_VERSION, client_id, client_nonce, support_mask],
        )
        if proof != expected:
            self.get_logger().warning(f'Rejected compact BLE probe from client={client_id}: invalid proof')
            return None

        support_ipv4 = int(support_mask) & 0x1 != 0 if support_mask.isdigit() else True
        support_ipv6 = int(support_mask) & 0x2 != 0 if support_mask.isdigit() else True
        if self.transport_mode == TRANSPORT_MODE_BLE_ONLY:
            self._authorize_ble_only_client(
                client_id=client_id,
                client_name='DJI_RC_Pro',
                client_nonce=client_nonce,
                support_ipv4=support_ipv4,
                support_ipv6=support_ipv6,
            )
            return None

        selected_family, selected_address, _ = self._choose_endpoint(support_ipv4, support_ipv6)
        if not selected_family or not selected_address:
            return self._build_compact_busy_response(client_id, client_nonce, 'no_address')

        busy = self._lease_active() and self.lease.client_id != client_id
        ready = self._is_ready()
        self.pending_offer = {
            'client_id': client_id,
            'client_nonce': client_nonce,
            'support_ipv4': '1' if support_ipv4 else '0',
            'support_ipv6': '1' if support_ipv6 else '0',
        }
        family_wire = compact_family_to_wire(selected_family)
        offer_proof = compact_hmac_token(
            self.pair_code,
            [
                'o',
                BLE_COMPACT_VERSION,
                self.host_id,
                client_id,
                client_nonce,
                self.host_nonce,
                str(self.control_port),
                str(self.lease_ms),
                family_wire,
                selected_address,
                '1' if ready else '0',
                '1' if busy else '0',
            ],
        )
        self.get_logger().info(
            f'Compact BLE offer sent client={client_id} ready={ready} busy={busy} family={selected_family} address={selected_address}:{self.control_port}'
        )
        return build_message({
            'v': BLE_COMPACT_VERSION,
            't': 'o',
            'h': self.host_id,
            'c': client_id,
            'n': client_nonce,
            'o': self.host_nonce,
            'p': str(self.control_port),
            'l': str(self.lease_ms),
            'f': family_wire,
            'a': selected_address,
            'r': '1' if ready else '0',
            'b': '1' if busy else '0',
            'm': offer_proof,
        })

    def _handle_compact_pair_request(self, fields: Dict[str, str]) -> Optional[bytes]:
        client_id = fields.get('c', '')
        client_nonce = fields.get('n', '')
        host_id = fields.get('h', '')
        host_nonce = fields.get('o', '')
        proof = fields.get('m', '')
        if not client_id or not client_nonce or not host_id or not host_nonce or not proof:
            return None
        if host_id != self.host_id or host_nonce != self.host_nonce:
            return None
        if self.pending_offer.get('client_id') != client_id or self.pending_offer.get('client_nonce') != client_nonce:
            return None

        expected = compact_hmac_token(
            self.pair_code,
            ['r', BLE_COMPACT_VERSION, self.host_id, client_id, client_nonce, host_nonce, str(self.control_port)],
        )
        if proof != expected:
            self.get_logger().warning(f'Rejected compact BLE pair request from client={client_id}: invalid proof')
            return None

        if not self._is_ready():
            return self._build_compact_busy_response(client_id, client_nonce, 'not_ready')
        if self._lease_active() and self.lease.client_id != client_id:
            return self._build_compact_busy_response(client_id, client_nonce, 'busy')

        support_ipv4 = self.pending_offer.get('support_ipv4', '1') == '1'
        support_ipv6 = self.pending_offer.get('support_ipv6', '1') == '1'
        selected_family, selected_address, _ = self._choose_endpoint(support_ipv4, support_ipv6)
        if not selected_family or not selected_address:
            return self._build_compact_busy_response(client_id, client_nonce, 'no_address')

        session_id = self.lease.session_id if self._lease_active() and self.lease.client_id == client_id else new_wire_token()
        family_wire = compact_family_to_wire(selected_family)
        self.lease = LeaseState(
            client_id=client_id,
            client_name='DJI_RC_Pro',
            client_nonce=client_nonce,
            session_id=session_id,
            selected_family=selected_family,
            selected_address=selected_address,
            expires_at_monotonic=time.monotonic() + self.lease_ms / 1000.0,
            support_ipv4=support_ipv4,
            support_ipv6=support_ipv6,
        )
        ack_proof = compact_hmac_token(
            self.pair_code,
            [
                'a',
                BLE_COMPACT_VERSION,
                self.host_id,
                client_id,
                client_nonce,
                self.host_nonce,
                session_id,
                str(self.control_port),
                str(self.lease_ms),
                family_wire,
                selected_address,
            ],
        )
        self.get_logger().info(
            f'Compact BLE paired client={client_id} family={selected_family} address={selected_address}:{self.control_port}'
        )
        return build_message({
            'v': BLE_COMPACT_VERSION,
            't': 'a',
            'h': self.host_id,
            's': session_id,
            'l': str(self.lease_ms),
            'p': str(self.control_port),
            'f': family_wire,
            'a': selected_address,
            'm': ack_proof,
        })

    def _build_compact_busy_response(self, client_id: str, client_nonce: str, reason: str) -> bytes:
        proof = compact_hmac_token(
            self.pair_code,
            ['b', BLE_COMPACT_VERSION, self.host_id, client_id, client_nonce, self.host_nonce, reason, str(self.lease_ms)],
        )
        self.get_logger().warning(f'Compact BLE rejecting pair request client={client_id} reason={reason}')
        return build_message({
            'v': BLE_COMPACT_VERSION,
            't': 'b',
            'h': self.host_id,
            'l': str(self.lease_ms),
            'r': reason,
            'm': proof,
        })

    def _handle_probe(self, fields: Dict[str, str]) -> Optional[bytes]:
        client_id = fields.get('client_id', '')
        client_name = fields.get('client_name', 'DJI_RC_Pro')
        client_nonce = fields.get('client_nonce', '')
        proof = fields.get('proof', '')
        support_ipv4 = fields.get('support_ipv4', '1') == '1'
        support_ipv6 = fields.get('support_ipv6', '1') == '1'
        if not client_id or not client_nonce or not proof:
            return None

        expected = hmac_hex(self.pair_code, join_signature(['probe', PROTOCOL_VERSION, client_id, client_nonce, client_name]))
        if proof != expected:
            self.get_logger().warning(f'Rejected BLE probe from client={client_id}: invalid proof')
            return None

        if self.transport_mode == TRANSPORT_MODE_BLE_ONLY:
            self._authorize_ble_only_client(
                client_id=client_id,
                client_name=client_name,
                client_nonce=client_nonce,
                support_ipv4=support_ipv4,
                support_ipv6=support_ipv6,
            )
            return None

        busy = self._lease_active() and self.lease.client_id != client_id
        self.pending_offer = {
            'client_id': client_id,
            'client_name': client_name,
            'client_nonce': client_nonce,
            'support_ipv4': '1' if support_ipv4 else '0',
            'support_ipv6': '1' if support_ipv6 else '0',
        }
        offer_proof = hmac_hex(
            self.pair_code,
            join_signature([
                'offer',
                PROTOCOL_VERSION,
                self.host_id,
                client_id,
                client_nonce,
                self.host_nonce,
                str(self.control_port),
                str(self.lease_ms),
            ]),
        )
        self.get_logger().info(f'BLE offer sent to client={client_id} ready={self._is_ready()} busy={busy}')
        return build_message({
            'proto': PROTOCOL_VERSION,
            'type': 'offer',
            'host_id': self.host_id,
            'host_name': self.host_name,
            'client_id': client_id,
            'client_nonce': client_nonce,
            'host_nonce': self.host_nonce,
            'control_port': str(self.control_port),
            'discovery_port': '1388',
            'lease_ms': str(self.lease_ms),
            'ready': '1' if self._is_ready() else '0',
            'busy': '1' if busy else '0',
            'ipv4': self.ipv4_address,
            'ipv6': self.ipv6_address,
            'proof': offer_proof,
        })

    def _handle_pair_request(self, fields: Dict[str, str]) -> Optional[bytes]:
        client_id = fields.get('client_id', '')
        client_name = fields.get('client_name', 'DJI_RC_Pro')
        client_nonce = fields.get('client_nonce', '')
        host_id = fields.get('host_id', '')
        host_nonce = fields.get('host_nonce', '')
        proof = fields.get('proof', '')
        if not client_id or not client_nonce or not host_id or not host_nonce or not proof:
            return None
        if host_id != self.host_id or host_nonce != self.host_nonce:
            return None
        if self.pending_offer.get('client_id') != client_id or self.pending_offer.get('client_nonce') != client_nonce:
            return None

        expected = hmac_hex(
            self.pair_code,
            join_signature([
                'pair',
                PROTOCOL_VERSION,
                self.host_id,
                client_id,
                client_nonce,
                host_nonce,
                str(self.control_port),
                str(self.lease_ms),
            ]),
        )
        if proof != expected:
            self.get_logger().warning(f'Rejected BLE pair request from client={client_id}: invalid proof')
            return None

        if not self._is_ready():
            return self._build_busy_response(client_id, client_nonce, 'not_ready')
        if self._lease_active() and self.lease.client_id != client_id:
            return self._build_busy_response(client_id, client_nonce, 'busy')

        support_ipv4 = self.pending_offer.get('support_ipv4', '1') == '1'
        support_ipv6 = self.pending_offer.get('support_ipv6', '1') == '1'
        selected_family, selected_address, _ = self._choose_endpoint(support_ipv4, support_ipv6)
        if not selected_family or not selected_address:
            return self._build_busy_response(client_id, client_nonce, 'no_address')

        session_id = self.lease.session_id if self._lease_active() and self.lease.client_id == client_id else uuid.uuid4().hex
        self.lease = LeaseState(
            client_id=client_id,
            client_name=client_name,
            client_nonce=client_nonce,
            session_id=session_id,
            selected_family=selected_family,
            selected_address=selected_address,
            expires_at_monotonic=time.monotonic() + self.lease_ms / 1000.0,
            support_ipv4=support_ipv4,
            support_ipv6=support_ipv6,
        )
        ack_proof = hmac_hex(
            self.pair_code,
            join_signature([
                'ack',
                PROTOCOL_VERSION,
                self.host_id,
                client_id,
                client_nonce,
                self.host_nonce,
                session_id,
                str(self.control_port),
                str(self.lease_ms),
                selected_family,
                selected_address,
            ]),
        )
        self.get_logger().info(
            f'BLE paired client={client_id} family={selected_family} address={selected_address}:{self.control_port}'
        )
        return build_message({
            'proto': PROTOCOL_VERSION,
            'type': 'pair_ack',
            'host_id': self.host_id,
            'session_id': session_id,
            'lease_ms': str(self.lease_ms),
            'control_port': str(self.control_port),
            'selected_family': selected_family,
            'address': selected_address,
            'proof': ack_proof,
        })

    def _build_busy_response(self, client_id: str, client_nonce: str, reason: str) -> bytes:
        proof = hmac_hex(
            self.pair_code,
            join_signature([
                'busy',
                PROTOCOL_VERSION,
                self.host_id,
                client_id,
                client_nonce,
                self.host_nonce,
                reason,
                str(self.lease_ms),
            ]),
        )
        self.get_logger().warning(f'BLE rejecting pair request client={client_id} reason={reason}')
        return build_message({
            'proto': PROTOCOL_VERSION,
            'type': 'pair_busy',
            'host_id': self.host_id,
            'lease_ms': str(self.lease_ms),
            'reason': reason,
            'proof': proof,
        })

    def handle_control_frame(self, payload: bytes):
        if not self._ble_enabled():
            return
        if not self._lease_active():
            now = time.monotonic()
            if now - self.last_ble_drop_log_monotonic >= 1.0:
                self.get_logger().warning('BLE control frame dropped: link not authorized')
                self.last_ble_drop_log_monotonic = now
            return
        self.touch_lease()
        now = time.monotonic()
        if now - self.last_ble_control_log_monotonic >= 1.0:
            self.get_logger().info(
                f'BLE control frame received bytes={len(payload)} client={self.lease.client_id}'
            )
            self.last_ble_control_log_monotonic = now
        message = UInt8MultiArray()
        message.data = list(payload)
        self.ble_frame_publisher.publish(message)


def main(args=None):
    rclpy.init(args=args)
    node = Ros2BleGatewayNode()
    try:
        rclpy.spin(node)
    except KeyboardInterrupt:
        pass
    finally:
        node.destroy_node()
        rclpy.shutdown()


if __name__ == '__main__':
    main()
