#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
WS_DIR="$ROOT_DIR/ros2_ws_dji_rc_pro"
LOG_DIR="$ROOT_DIR/logs/ros2"
TIMESTAMP="$(date +%Y-%m-%d_%H-%M-%S)"
LOG_FILE="$LOG_DIR/dji_rc_pro_stack_${TIMESTAMP}.log"
PAIR_CODE="${PAIR_CODE:-NCURC2026}"
TRANSPORT_MODE="${TRANSPORT_MODE:-ble_udp}"
CMD_VEL_TOPIC="${CMD_VEL_TOPIC:-/dji_rc_pro_bridge/cmd_vel}"
RAW_TOPIC="${RAW_TOPIC:-/dji_rc_pro_bridge/chassis_ctrl_raw}"
STATUS_TOPIC="${STATUS_TOPIC:-/mcu_comm_node/status}"
BLE_FRAME_TOPIC="${BLE_FRAME_TOPIC:-/dji_rc_pro_bridge/ble/control_frame}"
TRANSPORT_STATUS_TOPIC="${TRANSPORT_STATUS_TOPIC:-/dji_rc_pro_bridge/transport_status}"
ADVERTISE_NAME="${ADVERTISE_NAME:-RC26-ROS2-$(hostname | cut -c1-12)}"

mkdir -p "$LOG_DIR"

set +u
source /opt/ros/humble/setup.bash
source "$WS_DIR/install/setup.bash"
set -u

echo "Log file: $LOG_FILE"
ros2 launch dji_rc_pro_bridge dji_rc_pro_stack.launch.py \
  pair_code:="$PAIR_CODE" \
  transport_mode:="$TRANSPORT_MODE" \
  host_name:="$(hostname)" \
  advertise_name:="$ADVERTISE_NAME" \
  cmd_vel_topic:="$CMD_VEL_TOPIC" \
  raw_topic:="$RAW_TOPIC" \
  status_topic:="$STATUS_TOPIC" \
  ble_frame_topic:="$BLE_FRAME_TOPIC" \
  transport_status_topic:="$TRANSPORT_STATUS_TOPIC" 2>&1 | tee "$LOG_FILE"
