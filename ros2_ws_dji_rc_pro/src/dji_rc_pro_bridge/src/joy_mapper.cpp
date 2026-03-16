#include "dji_rc_pro_bridge/joy_mapper.hpp"

#include <cstddef>

namespace dji_rc_pro_bridge {

namespace {

double ReadAxis(const sensor_msgs::msg::Joy& joy, int index) {
  if (index < 0 || static_cast<std::size_t>(index) >= joy.axes.size()) {
    return 0.0;
  }
  return static_cast<double>(joy.axes[static_cast<std::size_t>(index)]);
}

int32_t ReadButton(const sensor_msgs::msg::Joy& joy, int index) {
  if (index < 0 || static_cast<std::size_t>(index) >= joy.buttons.size()) {
    return 0;
  }
  return joy.buttons[static_cast<std::size_t>(index)];
}

}  // namespace

geometry_msgs::msg::Twist ComputeCmdVelFromJoy(const sensor_msgs::msg::Joy& joy,
                                               const JoyToCmdVelConfig& config) {
  geometry_msgs::msg::Twist twist;

  const bool turbo_enabled = ReadButton(joy, config.enable_turbo_button) != 0;
  const bool normal_enabled = ReadButton(joy, config.enable_button) != 0;
  if (config.require_enable_button && !turbo_enabled && !normal_enabled) {
    return twist;
  }

  const ScaleMapping& scales = turbo_enabled ? config.scale_chassis_turbo : config.scale_chassis;
  twist.linear.x = ReadAxis(joy, config.axis_chassis.x) * scales.x;
  twist.linear.y = ReadAxis(joy, config.axis_chassis.y) * scales.y;
  twist.angular.z = ReadAxis(joy, config.axis_chassis.yaw) * scales.yaw;
  return twist;
}

}  // namespace dji_rc_pro_bridge
