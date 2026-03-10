#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
LOG_DIR="$ROOT_DIR/logs/adb"
PACKAGE_NAME="com.example.dji_rc_pro"
ADB_BIN="${ADB_BIN:-adb}"
DEVICE_SERIAL=""
CONNECT_TARGET=""
CLEAR_LOGCAT=1
FOLLOW_MODE=1

usage() {
  cat <<USAGE
Usage: $(basename "$0") [options]

Options:
  -s, --serial <serial>        adb device serial
  -c, --connect <host:port>    run 'adb connect' before capturing
      --no-clear               do not clear logcat before capture
      --dump-once              dump current app logs once and exit
  -h, --help                   show help

Environment:
  ADB_BIN                      adb binary path (default: adb)
USAGE
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    -s|--serial)
      DEVICE_SERIAL="$2"
      shift 2
      ;;
    -c|--connect)
      CONNECT_TARGET="$2"
      shift 2
      ;;
    --no-clear)
      CLEAR_LOGCAT=0
      shift
      ;;
    --dump-once)
      FOLLOW_MODE=0
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

mkdir -p "$LOG_DIR"
TIMESTAMP="$(date +%Y-%m-%d_%H-%M-%S)"
LOG_FILE="$LOG_DIR/udp_logcat_${TIMESTAMP}.log"
META_FILE="$LOG_DIR/udp_logcat_${TIMESTAMP}.meta"

adb_cmd() {
  if [[ -n "$DEVICE_SERIAL" ]]; then
    "$ADB_BIN" -s "$DEVICE_SERIAL" "$@"
  else
    "$ADB_BIN" "$@"
  fi
}

{
  echo "timestamp=$TIMESTAMP"
  echo "package=$PACKAGE_NAME"
  echo "adb_bin=$ADB_BIN"
  echo "serial=${DEVICE_SERIAL:-auto}"
  echo "connect_target=${CONNECT_TARGET:-none}"
} > "$META_FILE"

adb_cmd start-server >/dev/null

if [[ -n "$CONNECT_TARGET" ]]; then
  adb_cmd connect "$CONNECT_TARGET"
fi

adb_cmd devices -l | tee -a "$META_FILE"

if [[ -z "$DEVICE_SERIAL" ]]; then
  DEVICE_SERIAL="$("$ADB_BIN" devices | awk 'NR>1 && $2=="device" {print $1; exit}')"
fi

if [[ -z "$DEVICE_SERIAL" ]]; then
  echo "No adb device available" >&2
  exit 1
fi

APP_PID="$("$ADB_BIN" -s "$DEVICE_SERIAL" shell pidof -s "$PACKAGE_NAME" 2>/dev/null | tr -d '\r')"
if [[ -z "$APP_PID" ]]; then
  echo "Package $PACKAGE_NAME is not running on device $DEVICE_SERIAL" >&2
  echo "Tip: launch the app on RC Pro first, then rerun this script." >&2
  exit 1
fi

echo "device_serial=$DEVICE_SERIAL" | tee -a "$META_FILE"
echo "app_pid=$APP_PID" | tee -a "$META_FILE"
echo "log_file=$LOG_FILE" | tee -a "$META_FILE"

if [[ "$CLEAR_LOGCAT" -eq 1 ]]; then
  "$ADB_BIN" -s "$DEVICE_SERIAL" logcat -c
fi

COMMON_FILTER='*:S UdpService:D MainViewModel:D DataLogManager:D AndroidRuntime:E'

if [[ "$FOLLOW_MODE" -eq 1 ]]; then
  echo "Capturing live UDP logs to $LOG_FILE"
  echo "Stop with Ctrl-C"
  exec "$ADB_BIN" -s "$DEVICE_SERIAL" logcat --pid="$APP_PID" -v threadtime $COMMON_FILTER | tee "$LOG_FILE"
else
  echo "Dumping current UDP logs to $LOG_FILE"
  "$ADB_BIN" -s "$DEVICE_SERIAL" logcat -d --pid="$APP_PID" -v threadtime $COMMON_FILTER | tee "$LOG_FILE"
  echo
  echo "Suggested grep: grep -E 'UDP (TX|RX)' '$LOG_FILE'"
fi
