#include <string>

#include <geometry_msgs/msg/twist.hpp>
#include <rclcpp/rclcpp.hpp>
#include <sensor_msgs/msg/joy.hpp>

#include "dji_rc_pro_bridge/joy_mapper.hpp"

namespace {

class DjiRcProJoyToCmdVelNode : public rclcpp::Node {
 public:
  DjiRcProJoyToCmdVelNode()
      : Node("dji_rc_pro_joy_to_cmd_vel") {
    joy_topic_ = this->declare_parameter<std::string>("joy_topic", "/dji_rc_pro_bridge/joy");
    cmd_vel_topic_ = this->declare_parameter<std::string>("cmd_vel_topic", "/dji_rc_pro_bridge/cmd_vel");
    robot_base_frame_ = this->declare_parameter<std::string>("robot_base_frame", "base_link");
    control_mode_ = this->declare_parameter<std::string>("control_mode", "manual_control");

    config_.require_enable_button = this->declare_parameter<bool>("require_enable_button", false);
    config_.enable_button = this->declare_parameter<int>("enable_button", 4);
    config_.enable_turbo_button = this->declare_parameter<int>("enable_turbo_button", 5);

    config_.axis_chassis.x = this->declare_parameter<int>("axis_chassis.x", 1);
    config_.axis_chassis.y = this->declare_parameter<int>("axis_chassis.y", 0);
    config_.axis_chassis.yaw = this->declare_parameter<int>("axis_chassis.yaw", 2);

    config_.scale_chassis.x = this->declare_parameter<double>("scale_chassis.x", 1.0);
    config_.scale_chassis.y = this->declare_parameter<double>("scale_chassis.y", -1.0);
    config_.scale_chassis.yaw = this->declare_parameter<double>("scale_chassis.yaw", 1.0);

    config_.scale_chassis_turbo.x = this->declare_parameter<double>("scale_chassis_turbo.x", 1.0);
    config_.scale_chassis_turbo.y = this->declare_parameter<double>("scale_chassis_turbo.y", -1.0);
    config_.scale_chassis_turbo.yaw = this->declare_parameter<double>("scale_chassis_turbo.yaw", 1.0);

    cmd_vel_publisher_ = this->create_publisher<geometry_msgs::msg::Twist>(cmd_vel_topic_, 10);
    joy_subscription_ = this->create_subscription<sensor_msgs::msg::Joy>(
        joy_topic_, 10,
        [this](const sensor_msgs::msg::Joy::SharedPtr msg) { this->HandleJoy(msg); });

    RCLCPP_INFO(
        this->get_logger(),
        "joy_to_cmd_vel ready joy=%s cmd_vel=%s mode=%s base_frame=%s",
        joy_topic_.c_str(),
        cmd_vel_topic_.c_str(),
        control_mode_.c_str(),
        robot_base_frame_.c_str());
  }

 private:
  void HandleJoy(const sensor_msgs::msg::Joy::SharedPtr msg) {
    auto twist = dji_rc_pro_bridge::ComputeCmdVelFromJoy(*msg, config_);
    cmd_vel_publisher_->publish(twist);
    RCLCPP_INFO_THROTTLE(
        this->get_logger(), *this->get_clock(), 1000,
        "cmd_vel mapped: x=%.3f y=%.3f yaw=%.3f",
        twist.linear.x, twist.linear.y, twist.angular.z);
  }

  std::string joy_topic_;
  std::string cmd_vel_topic_;
  std::string robot_base_frame_;
  std::string control_mode_;
  dji_rc_pro_bridge::JoyToCmdVelConfig config_;
  rclcpp::Publisher<geometry_msgs::msg::Twist>::SharedPtr cmd_vel_publisher_;
  rclcpp::Subscription<sensor_msgs::msg::Joy>::SharedPtr joy_subscription_;
};

}  // namespace

int main(int argc, char** argv) {
  rclcpp::init(argc, argv);
  auto node = std::make_shared<DjiRcProJoyToCmdVelNode>();
  rclcpp::spin(node);
  rclcpp::shutdown();
  return 0;
}
