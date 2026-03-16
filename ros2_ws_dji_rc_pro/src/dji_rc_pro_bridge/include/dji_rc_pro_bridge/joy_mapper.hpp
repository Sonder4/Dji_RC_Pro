#ifndef DJI_RC_PRO_BRIDGE_JOY_MAPPER_HPP
#define DJI_RC_PRO_BRIDGE_JOY_MAPPER_HPP

#include <geometry_msgs/msg/twist.hpp>
#include <sensor_msgs/msg/joy.hpp>

namespace dji_rc_pro_bridge {

struct AxisMapping {
  int x{1};
  int y{0};
  int yaw{2};
};

struct ScaleMapping {
  double x{1.0};
  double y{-1.0};
  double yaw{1.0};
};

struct JoyToCmdVelConfig {
  bool require_enable_button{false};
  int enable_button{4};
  int enable_turbo_button{5};
  AxisMapping axis_chassis;
  ScaleMapping scale_chassis;
  ScaleMapping scale_chassis_turbo;
};

geometry_msgs::msg::Twist ComputeCmdVelFromJoy(const sensor_msgs::msg::Joy& joy,
                                               const JoyToCmdVelConfig& config);

}  // namespace dji_rc_pro_bridge

#endif  // DJI_RC_PRO_BRIDGE_JOY_MAPPER_HPP
