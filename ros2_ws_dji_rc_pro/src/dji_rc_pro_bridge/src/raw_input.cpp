#include "dji_rc_pro_bridge/raw_input.hpp"

#include <algorithm>

namespace dji_rc_pro_bridge {

bool DecodeRawInputPayload(const std::vector<uint8_t>& data, RawInputState* out) {
  if (out == nullptr || data.size() < kRawInputPayloadSize) {
    return false;
  }

  out->left_stick_x = data[0];
  out->left_stick_y = data[1];
  out->right_stick_x = data[2];
  out->right_stick_y = data[3];
  out->button_mask = static_cast<uint32_t>(data[4]) |
                     (static_cast<uint32_t>(data[5]) << 8) |
                     (static_cast<uint32_t>(data[6]) << 16) |
                     (static_cast<uint32_t>(data[7]) << 24);
  out->left_wheel = data[8];
  out->right_wheel = data[9];
  return true;
}

float NormalizeRawAxis(uint8_t value) {
  const float normalized = (static_cast<float>(value) - 127.0f) / 127.0f;
  return std::clamp(normalized, -1.0f, 1.0f);
}

std::vector<int32_t> ExpandButtonMask(uint32_t button_mask, std::size_t button_count) {
  std::vector<int32_t> buttons(button_count, 0);
  for (std::size_t index = 0; index < button_count; ++index) {
    buttons[index] = ((button_mask >> index) & 0x1u) != 0u ? 1 : 0;
  }
  return buttons;
}

sensor_msgs::msg::Joy RawInputToJoyMessage(const RawInputState& state) {
  sensor_msgs::msg::Joy joy;
  joy.axes.reserve(kRawInputAxisCount);
  joy.axes.push_back(NormalizeRawAxis(state.left_stick_x));
  joy.axes.push_back(NormalizeRawAxis(state.left_stick_y));
  joy.axes.push_back(NormalizeRawAxis(state.right_stick_x));
  joy.axes.push_back(NormalizeRawAxis(state.right_stick_y));
  joy.axes.push_back(NormalizeRawAxis(state.left_wheel));
  joy.axes.push_back(NormalizeRawAxis(state.right_wheel));
  joy.buttons = ExpandButtonMask(state.button_mask);
  return joy;
}

}  // namespace dji_rc_pro_bridge
