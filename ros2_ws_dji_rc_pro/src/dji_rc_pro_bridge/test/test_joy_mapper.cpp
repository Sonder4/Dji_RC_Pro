#include <cmath>
#include <iostream>

#include <sensor_msgs/msg/joy.hpp>

#include "dji_rc_pro_bridge/joy_mapper.hpp"

namespace {

bool AlmostEqual(double lhs, double rhs, double tolerance = 1e-6) {
  return std::fabs(lhs - rhs) <= tolerance;
}

int Fail(const char* message) {
  std::cerr << message << std::endl;
  return 1;
}

sensor_msgs::msg::Joy MakeJoy() {
  sensor_msgs::msg::Joy joy;
  joy.axes = {-1.0f, 1.0f, 0.5f, 0.0f, 0.0f, 0.0f};
  joy.buttons.assign(32, 0);
  return joy;
}

}  // namespace

int main() {
  dji_rc_pro_bridge::JoyToCmdVelConfig config;
  auto joy = MakeJoy();

  auto twist = dji_rc_pro_bridge::ComputeCmdVelFromJoy(joy, config);
  if (!AlmostEqual(twist.linear.x, 1.0) ||
      !AlmostEqual(twist.linear.y, 1.0) ||
      !AlmostEqual(twist.angular.z, 0.5)) {
    return Fail("default chassis mapping mismatch");
  }

  config.require_enable_button = true;
  twist = dji_rc_pro_bridge::ComputeCmdVelFromJoy(joy, config);
  if (!AlmostEqual(twist.linear.x, 0.0) ||
      !AlmostEqual(twist.linear.y, 0.0) ||
      !AlmostEqual(twist.angular.z, 0.0)) {
    return Fail("expected disabled output when enable button is required");
  }

  joy.buttons[config.enable_button] = 1;
  twist = dji_rc_pro_bridge::ComputeCmdVelFromJoy(joy, config);
  if (!AlmostEqual(twist.linear.x, 1.0) ||
      !AlmostEqual(twist.linear.y, 1.0) ||
      !AlmostEqual(twist.angular.z, 0.5)) {
    return Fail("enable button should allow normal output");
  }

  config.scale_chassis_turbo.x = 2.0;
  config.scale_chassis_turbo.y = -2.0;
  config.scale_chassis_turbo.yaw = 3.0;
  joy.buttons[config.enable_turbo_button] = 1;
  twist = dji_rc_pro_bridge::ComputeCmdVelFromJoy(joy, config);
  if (!AlmostEqual(twist.linear.x, 2.0) ||
      !AlmostEqual(twist.linear.y, 2.0) ||
      !AlmostEqual(twist.angular.z, 1.5)) {
    return Fail("turbo mapping mismatch");
  }

  sensor_msgs::msg::Joy invalid_joy;
  invalid_joy.axes = {0.9f};
  invalid_joy.buttons.clear();
  twist = dji_rc_pro_bridge::ComputeCmdVelFromJoy(invalid_joy, config);
  if (!AlmostEqual(twist.linear.x, 0.0) ||
      !AlmostEqual(twist.linear.y, 0.0) ||
      !AlmostEqual(twist.angular.z, 0.0)) {
    return Fail("expected safe zero output for incomplete joy message");
  }

  return 0;
}
