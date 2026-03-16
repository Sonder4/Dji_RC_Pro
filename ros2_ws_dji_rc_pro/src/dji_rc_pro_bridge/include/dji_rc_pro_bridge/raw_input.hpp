#ifndef DJI_RC_PRO_BRIDGE_RAW_INPUT_HPP
#define DJI_RC_PRO_BRIDGE_RAW_INPUT_HPP

#include <cstddef>
#include <cstdint>
#include <vector>

#include <sensor_msgs/msg/joy.hpp>

namespace dji_rc_pro_bridge {

constexpr std::size_t kRawInputPayloadSize = 10;
constexpr std::size_t kRawInputAxisCount = 6;
constexpr std::size_t kRawInputButtonCount = 32;

struct RawInputState {
  uint8_t left_stick_x{127};
  uint8_t left_stick_y{127};
  uint8_t right_stick_x{127};
  uint8_t right_stick_y{127};
  uint32_t button_mask{0};
  uint8_t left_wheel{127};
  uint8_t right_wheel{127};
};

bool DecodeRawInputPayload(const std::vector<uint8_t>& data, RawInputState* out);
float NormalizeRawAxis(uint8_t value);
std::vector<int32_t> ExpandButtonMask(uint32_t button_mask,
                                      std::size_t button_count = kRawInputButtonCount);
sensor_msgs::msg::Joy RawInputToJoyMessage(const RawInputState& state);

}  // namespace dji_rc_pro_bridge

#endif  // DJI_RC_PRO_BRIDGE_RAW_INPUT_HPP
