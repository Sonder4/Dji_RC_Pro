#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
LOG_DIR="$ROOT_DIR/logs/adb"
PACKAGE_NAME="com.example.dji_rc_pro"
ACTIVITY_NAME="$PACKAGE_NAME/.MainActivity"
ADB_BIN="${ADB_BIN:-adb}"
DEVICE_SERIAL=""
TARGET_IP=""
FORCE_STOP=1
LAUNCH_APP=1

usage() {
  cat <<USAGE
Usage: $(basename "$0") --ip <target-ip> [options]

Options:
  -i, --ip <target-ip>        target IPv4/IPv6 address to persist
  -s, --serial <serial>       adb device serial
      --no-force-stop         do not force-stop the app before replacing DataStore
      --no-launch             do not relaunch the app after replacing DataStore
  -h, --help                  show help

Environment:
  ADB_BIN                     adb binary path (default: adb)
USAGE
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    -i|--ip)
      TARGET_IP="$2"
      shift 2
      ;;
    -s|--serial)
      DEVICE_SERIAL="$2"
      shift 2
      ;;
    --no-force-stop)
      FORCE_STOP=0
      shift
      ;;
    --no-launch)
      LAUNCH_APP=0
      shift
      ;;
    -h|--help)
      usage
      exit 0
      ;;
    *)
      echo "Unknown argument: $1" >&2
      usage >&2
      exit 1
      ;;
  esac
done

if [[ -z "$TARGET_IP" ]]; then
  echo "Missing required argument: --ip <target-ip>" >&2
  usage >&2
  exit 1
fi

mkdir -p "$LOG_DIR"
TIMESTAMP="$(date +%Y-%m-%d_%H-%M-%S)"
PAYLOAD_FILE="$LOG_DIR/settings_target_ip_${TIMESTAMP}.pb"
META_FILE="$LOG_DIR/settings_target_ip_${TIMESTAMP}.meta"

adb_cmd() {
  if [[ -n "$DEVICE_SERIAL" ]]; then
    "$ADB_BIN" -s "$DEVICE_SERIAL" "$@"
  else
    "$ADB_BIN" "$@"
  fi
}

adb_cmd start-server >/dev/null

if [[ -z "$DEVICE_SERIAL" ]]; then
  DEVICE_SERIAL="$($ADB_BIN devices | awk 'NR>1 && $2=="device" {print $1; exit}')"
fi

if [[ -z "$DEVICE_SERIAL" ]]; then
  echo "No adb device available" >&2
  exit 1
fi

TARGET_IP="$TARGET_IP" python3 - <<'PY' > "$PAYLOAD_FILE"
import os
import sys

ip = os.environ["TARGET_IP"].strip().encode("ascii")
if not ip:
    raise SystemExit("empty target ip")

key = b"target_ip"
entry = bytes([0x0A, len(key)]) + key + bytes([0x12, len(ip) + 2, 0x2A, len(ip)]) + ip
sys.stdout.buffer.write(bytes([0x0A, len(entry)]) + entry)
PY

{
  echo "timestamp=$TIMESTAMP"
  echo "serial=$DEVICE_SERIAL"
  echo "target_ip=$TARGET_IP"
  echo "payload_file=$PAYLOAD_FILE"
} > "$META_FILE"

adb_cmd push "$PAYLOAD_FILE" /data/local/tmp/settings.preferences_pb >/dev/null

if [[ "$FORCE_STOP" -eq 1 ]]; then
  adb_cmd shell am force-stop "$PACKAGE_NAME"
fi

adb_cmd shell run-as "$PACKAGE_NAME" mkdir -p files/datastore
adb_cmd shell run-as "$PACKAGE_NAME" cp /data/local/tmp/settings.preferences_pb files/datastore/settings.preferences_pb
adb_cmd shell run-as "$PACKAGE_NAME" chmod 600 files/datastore/settings.preferences_pb

echo "=== readback hex ===" | tee -a "$META_FILE"
adb_cmd shell run-as "$PACKAGE_NAME" cat files/datastore/settings.preferences_pb | xxd -g 1 | tee -a "$META_FILE"

if [[ "$LAUNCH_APP" -eq 1 ]]; then
  adb_cmd shell am start -n "$ACTIVITY_NAME" >/dev/null
fi

echo "Updated $PACKAGE_NAME target_ip to $TARGET_IP"
echo "Metadata saved to $META_FILE"
