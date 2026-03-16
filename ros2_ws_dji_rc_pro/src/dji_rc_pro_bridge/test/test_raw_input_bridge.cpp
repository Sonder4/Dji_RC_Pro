#include <cmath>
#include <cstdint>
#include <iostream>
#include <vector>

#include "dji_rc_pro_bridge/raw_input.hpp"
#include "protocol/protocol_parser.hpp"

namespace {

bool AlmostEqual(float lhs, float rhs, float tolerance = 1e-5f) {
  return std::fabs(lhs - rhs) <= tolerance;
}

int Fail(const char* message) {
  std::cerr << message << std::endl;
  return 1;
}

}  // namespace

int main() {
  dji_rc_pro_bridge::ProtocolParser parser;
  const std::vector<uint8_t> payload{
      255, 0, 64, 192, 0x15, 0x00, 0x00, 0x00, 11, 244};

  const auto frame = parser.buildFrame(
      dji_rc_pro_bridge::PacketID::RAW_INPUT,
      dji_rc_pro_bridge::DeviceID::PC_MASTER,
      payload);
  const auto serialized = frame.serialize();
  const auto parsed_frames = parser.parse(serialized);
  if (parsed_frames.size() != 1) {
    return Fail("expected a single parsed frame");
  }

  dji_rc_pro_bridge::RawInputState state;
  if (!dji_rc_pro_bridge::DecodeRawInputPayload(parsed_frames.front().data, &state)) {
    return Fail("expected raw payload to decode");
  }

  if (state.left_stick_x != 255 || state.left_stick_y != 0 ||
      state.right_stick_x != 64 || state.right_stick_y != 192) {
    return Fail("decoded stick values mismatch");
  }
  if (state.button_mask != 0x15u || state.left_wheel != 11 || state.right_wheel != 244) {
    return Fail("decoded button or wheel values mismatch");
  }

  const auto joy = dji_rc_pro_bridge::RawInputToJoyMessage(state);
  if (joy.axes.size() != 6 || joy.buttons.size() != 32) {
    return Fail("joy message sizes mismatch");
  }
  if (!AlmostEqual(joy.axes[0], 1.0f) ||
      !AlmostEqual(joy.axes[1], -1.0f) ||
      !AlmostEqual(joy.axes[2], (64.0f - 127.0f) / 127.0f) ||
      !AlmostEqual(joy.axes[3], (192.0f - 127.0f) / 127.0f) ||
      !AlmostEqual(joy.axes[4], (11.0f - 127.0f) / 127.0f) ||
      !AlmostEqual(joy.axes[5], (244.0f - 127.0f) / 127.0f)) {
    return Fail("joy axes mismatch");
  }
  if (joy.buttons[0] != 1 || joy.buttons[1] != 0 || joy.buttons[2] != 1 ||
      joy.buttons[4] != 1) {
    return Fail("joy button expansion mismatch");
  }

  return 0;
}
