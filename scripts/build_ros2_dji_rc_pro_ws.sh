#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
WS_DIR="$ROOT_DIR/ros2_ws_dji_rc_pro"
LOG_DIR="$ROOT_DIR/logs/ros2"
TIMESTAMP="$(date +%Y-%m-%d_%H-%M-%S)"
LOG_FILE="$LOG_DIR/colcon_build_${TIMESTAMP}.log"

mkdir -p "$LOG_DIR"

if [[ ! -f /opt/ros/humble/setup.bash ]]; then
  echo "ROS2 Humble setup not found at /opt/ros/humble/setup.bash" >&2
  exit 1
fi

set +u
source /opt/ros/humble/setup.bash
set -u
cd "$WS_DIR"

echo "Building workspace: $WS_DIR"
echo "Build log: $LOG_FILE"
colcon build --symlink-install --event-handlers console_direct+ 2>&1 | tee "$LOG_FILE"
