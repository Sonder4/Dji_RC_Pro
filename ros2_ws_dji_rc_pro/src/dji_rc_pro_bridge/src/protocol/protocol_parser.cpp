/**
 * @file protocol_parser.cpp
 * @brief 协议解析器实现（自动生成）
 * 
 * 该文件由代码生成器自动生成，请勿手动修改。
 * 协议版本: 2.0.0
 */

#include "protocol/protocol_parser.hpp"
#include <cstring>
#include <iostream>
#include <iomanip>

namespace dji_rc_pro_bridge {

#ifndef DJI_RC_PRO_BRIDGE_DEBUG_TRACE
#define DJI_RC_PRO_BRIDGE_DEBUG_TRACE 0
#endif

#if DJI_RC_PRO_BRIDGE_DEBUG_TRACE
namespace {
uint32_t g_parser_crc_ok_count = 0;
uint32_t g_parser_crc_reject_count = 0;
uint32_t g_parser_packet_count = 0;
}
#endif

/* ============================================================================ */
/*                              CRC16查找表                                     */
/* ============================================================================ */

const std::array<uint16_t, 256> ProtocolParser::crc16_table_ = {{    0x0000, 0x1021, 0x2042, 0x3063, 0x4084, 0x50A5, 0x60C6, 0x70E7,
    0x8108, 0x9129, 0xA14A, 0xB16B, 0xC18C, 0xD1AD, 0xE1CE, 0xF1EF,
    0x1231, 0x0210, 0x3273, 0x2252, 0x52B5, 0x4294, 0x72F7, 0x62D6,
    0x9339, 0x8318, 0xB37B, 0xA35A, 0xD3BD, 0xC39C, 0xF3FF, 0xE3DE,
    0x2462, 0x3443, 0x0420, 0x1401, 0x64E6, 0x74C7, 0x44A4, 0x5485,
    0xA56A, 0xB54B, 0x8528, 0x9509, 0xE5EE, 0xF5CF, 0xC5AC, 0xD58D,
    0x3653, 0x2672, 0x1611, 0x0630, 0x76D7, 0x66F6, 0x5695, 0x46B4,
    0xB75B, 0xA77A, 0x9719, 0x8738, 0xF7DF, 0xE7FE, 0xD79D, 0xC7BC,
    0x48C4, 0x58E5, 0x6886, 0x78A7, 0x0840, 0x1861, 0x2802, 0x3823,
    0xC9CC, 0xD9ED, 0xE98E, 0xF9AF, 0x8948, 0x9969, 0xA90A, 0xB92B,
    0x5AF5, 0x4AD4, 0x7AB7, 0x6A96, 0x1A71, 0x0A50, 0x3A33, 0x2A12,
    0xDBFD, 0xCBDC, 0xFBBF, 0xEB9E, 0x9B79, 0x8B58, 0xBB3B, 0xAB1A,
    0x6CA6, 0x7C87, 0x4CE4, 0x5CC5, 0x2C22, 0x3C03, 0x0C60, 0x1C41,
    0xEDAE, 0xFD8F, 0xCDEC, 0xDDCD, 0xAD2A, 0xBD0B, 0x8D68, 0x9D49,
    0x7E97, 0x6EB6, 0x5ED5, 0x4EF4, 0x3E13, 0x2E32, 0x1E51, 0x0E70,
    0xFF9F, 0xEFBE, 0xDFDD, 0xCFFC, 0xBF1B, 0xAF3A, 0x9F59, 0x8F78,
    0x9188, 0x81A9, 0xB1CA, 0xA1EB, 0xD10C, 0xC12D, 0xF14E, 0xE16F,
    0x1080, 0x00A1, 0x30C2, 0x20E3, 0x5004, 0x4025, 0x7046, 0x6067,
    0x83B9, 0x9398, 0xA3FB, 0xB3DA, 0xC33D, 0xD31C, 0xE37F, 0xF35E,
    0x02B1, 0x1290, 0x22F3, 0x32D2, 0x4235, 0x5214, 0x6277, 0x7256,
    0xB5EA, 0xA5CB, 0x95A8, 0x8589, 0xF56E, 0xE54F, 0xD52C, 0xC50D,
    0x34E2, 0x24C3, 0x14A0, 0x0481, 0x7466, 0x6447, 0x5424, 0x4405,
    0xA7DB, 0xB7FA, 0x8799, 0x97B8, 0xE75F, 0xF77E, 0xC71D, 0xD73C,
    0x26D3, 0x36F2, 0x0691, 0x16B0, 0x6657, 0x7676, 0x4615, 0x5634,
    0xD94C, 0xC96D, 0xF90E, 0xE92F, 0x99C8, 0x89E9, 0xB98A, 0xA9AB,
    0x5844, 0x4865, 0x7806, 0x6827, 0x18C0, 0x08E1, 0x3882, 0x28A3,
    0xCB7D, 0xDB5C, 0xEB3F, 0xFB1E, 0x8BF9, 0x9BD8, 0xABBB, 0xBB9A,
    0x4A75, 0x5A54, 0x6A37, 0x7A16, 0x0AF1, 0x1AD0, 0x2AB3, 0x3A92,
    0xFD2E, 0xED0F, 0xDD6C, 0xCD4D, 0xBDAA, 0xAD8B, 0x9DE8, 0x8DC9,
    0x7C26, 0x6C07, 0x5C64, 0x4C45, 0x3CA2, 0x2C83, 0x1CE0, 0x0CC1,
    0xEF1F, 0xFF3E, 0xCF5D, 0xDF7C, 0xAF9B, 0xBFBA, 0x8FD9, 0x9FF8,
    0x6E17, 0x7E36, 0x4E55, 0x5E74, 0x2E93, 0x3EB2, 0x0ED1, 0x1EF0
}};

/* ============================================================================ */
/*                              ProtocolFrame实现                               */
/* ============================================================================ */

std::vector<uint8_t> ProtocolFrame::serialize() const {
    std::vector<uint8_t> result;
    result.reserve(10 + data.size());
    
    result.push_back(header1);
    result.push_back(header2);
    result.push_back(heart);
    result.push_back(mid);
    result.push_back(pl);
    result.push_back(pid);
    result.push_back(len);
    result.insert(result.end(), data.begin(), data.end());
    
    // CRC
    uint16_t crc_val = calculateCRC();
    result.push_back(static_cast<uint8_t>((crc_val >> 8) & 0xFF));
    result.push_back(static_cast<uint8_t>(crc_val & 0xFF));
    
    result.push_back(tail);
    
    return result;
}

uint16_t ProtocolFrame::calculateCRC() const {
    uint16_t crc = PROTOCOL_CRC_INIT;
    
    crc = ProtocolParser::crc16Byte(crc, pid);
    crc = ProtocolParser::crc16Byte(crc, len);
    
    for (auto byte : data) {
        crc = ProtocolParser::crc16Byte(crc, byte);
    }
    
    return crc;
}

bool ProtocolFrame::verifyCRC() const {
    return calculateCRC() == crc;
}

/* ============================================================================ */
/*                              ProtocolParser实现                              */
/* ============================================================================ */

ProtocolParser::ProtocolParser() : state_(ParserState::WAIT_HEADER_H1), 
                                   data_index_(0), 
                                   calculated_crc_(PROTOCOL_CRC_INIT) {
}

uint16_t ProtocolParser::crc16Byte(uint16_t crc, uint8_t data) {
    return (crc << 8) ^ crc16_table_[((crc >> 8) ^ data) & 0xFF];
}

bool ProtocolParser::processByte(uint8_t byte) {
    switch (state_) {
        case ParserState::WAIT_HEADER_H1:
            if (byte == PROTOCOL_HEADER_H1) {
                current_frame_.header1 = byte;
                state_ = ParserState::WAIT_HEADER_H2;
            }
            break;
            
        case ParserState::WAIT_HEADER_H2:
            if (byte == PROTOCOL_HEADER_H2) {
                current_frame_.header2 = byte;
                state_ = ParserState::WAIT_HEART;
            } else {
                reset();
            }
            break;
            
        case ParserState::WAIT_HEART:
            current_frame_.heart = byte;
            state_ = ParserState::WAIT_MID;
            break;
            
        case ParserState::WAIT_MID:
            current_frame_.mid = byte;
            state_ = ParserState::WAIT_PL;
            break;
            
        case ParserState::WAIT_PL:
            current_frame_.pl = byte;
            if (current_frame_.pl > MAX_PACKET_SIZE) {
                reset();
            } else if (current_frame_.pl == 0) {
                state_ = ParserState::WAIT_CRC_H;
            } else {
                state_ = ParserState::WAIT_PID;
            }
            break;
            
        case ParserState::WAIT_PID:
            current_frame_.pid = byte;
            calculated_crc_ = crc16Byte(PROTOCOL_CRC_INIT, byte);
            state_ = ParserState::WAIT_LEN;
            break;
            
        case ParserState::WAIT_LEN:
            current_frame_.len = byte;
            calculated_crc_ = crc16Byte(calculated_crc_, byte);
#if DJI_RC_PRO_BRIDGE_DEBUG_TRACE
            g_parser_packet_count++;
            std::cerr << "[proto_parser] RX pkt#" << g_parser_packet_count
                      << " pid=0x" << std::hex << std::uppercase
                      << static_cast<int>(current_frame_.pid)
                      << std::dec
                      << " len=" << static_cast<int>(current_frame_.len)
                      << " pl=" << static_cast<int>(current_frame_.pl)
                      << std::endl;
#endif
            if (current_frame_.len == 0) {
                state_ = ParserState::WAIT_CRC_H;
            } else {
                data_index_ = 0;
                current_frame_.data.clear();
                current_frame_.data.reserve(current_frame_.len);
                state_ = ParserState::WAIT_DATA;
            }
            break;
            
        case ParserState::WAIT_DATA:
            current_frame_.data.push_back(byte);
            calculated_crc_ = crc16Byte(calculated_crc_, byte);
            if (++data_index_ >= current_frame_.len) {
                state_ = ParserState::WAIT_CRC_H;
            }
            break;
            
        case ParserState::WAIT_CRC_H:
            current_frame_.crc = static_cast<uint16_t>(byte) << 8;
            state_ = ParserState::WAIT_CRC_L;
            break;
            
        case ParserState::WAIT_CRC_L:
            current_frame_.crc |= byte;
            state_ = ParserState::WAIT_TAIL;
            break;
            
        case ParserState::WAIT_TAIL:
            if (byte == PROTOCOL_TAIL) {
                current_frame_.tail = byte;
                if (calculated_crc_ == current_frame_.crc) {
#if DJI_RC_PRO_BRIDGE_DEBUG_TRACE
                    g_parser_crc_ok_count++;
                    std::cerr << "[proto_parser] CRC OK #"
                              << g_parser_crc_ok_count
                              << " pid=0x" << std::hex << std::uppercase
                              << static_cast<int>(current_frame_.pid)
                              << " crc=0x" << std::setw(4) << std::setfill('0')
                              << current_frame_.crc
                              << std::dec << std::setfill(' ')
                              << std::endl;
#endif
                    return true;  // 帧完成
                }
#if DJI_RC_PRO_BRIDGE_DEBUG_TRACE
                g_parser_crc_reject_count++;
                std::cerr << "[proto_parser] CRC reject #"
                          << g_parser_crc_reject_count
                          << " pid=0x" << std::hex << std::uppercase
                          << static_cast<int>(current_frame_.pid)
                          << " calc=0x" << std::setw(4) << std::setfill('0')
                          << calculated_crc_
                          << " recv=0x" << std::setw(4)
                          << current_frame_.crc
                          << std::dec << std::setfill(' ')
                          << std::endl;
#endif
            }
#if DJI_RC_PRO_BRIDGE_DEBUG_TRACE
            else {
                std::cerr << "[proto_parser] tail mismatch: 0x"
                          << std::hex << std::uppercase << std::setw(2)
                          << std::setfill('0') << static_cast<int>(byte)
                          << std::dec << std::setfill(' ')
                          << std::endl;
            }
#endif
            reset();
            break;
    }
    
    return false;
}

std::vector<ProtocolFrame> ProtocolParser::parse(const uint8_t* data, size_t size) {
    std::vector<ProtocolFrame> frames;
    
    for (size_t i = 0; i < size; ++i) {
        if (processByte(data[i])) {
            frames.push_back(current_frame_);
            reset();
        }
    }
    
    return frames;
}

std::vector<ProtocolFrame> ProtocolParser::parse(const std::vector<uint8_t>& data) {
    return parse(data.data(), data.size());
}

ProtocolFrame ProtocolParser::buildFrame(PacketID pid, DeviceID mid, 
                                         const std::vector<uint8_t>& data) {
    ProtocolFrame frame;
    frame.pid = static_cast<uint8_t>(pid);
    frame.mid = static_cast<uint8_t>(mid);
    frame.len = static_cast<uint8_t>(data.size());
    frame.pl = 2 + frame.len;  // PID + LEN + DATA
    frame.data = data;
    frame.crc = frame.calculateCRC();
    return frame;
}

void ProtocolParser::reset() {
    state_ = ParserState::WAIT_HEADER_H1;
    data_index_ = 0;
    calculated_crc_ = PROTOCOL_CRC_INIT;
    current_frame_ = ProtocolFrame();
}

} // namespace dji_rc_pro_bridge
