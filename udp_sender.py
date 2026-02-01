#!/usr/bin/env python3
"""UDP test sender for DJI RC Pro"""

import socket
import time
import sys

def main():
    # Target: DJI RC Pro IP and port
    target_ip = "192.168.1.86"
    target_port = 1346

    # Test data (HEX: 52 43 A2 02 1C 03 0C 00 00 03 F0 00 00 00 07 9E 9F 64 2C 05 0C 00 00 03 F0 00 00 00 07 9E 9F 64 2C 01 09 ED)
    test_data = bytes([
        0x52, 0x43, 0xA2, 0x02, 0x1C, 0x03, 0x0C, 0x00, 0x00, 0x03, 0xF0, 0x00, 0x00, 0x00, 0x07, 0x9E,
        0x9F, 0x64, 0x2C, 0x05, 0x0C, 0x00, 0x00, 0x03, 0xF0, 0x00, 0x00, 0x00, 0x07, 0x9E, 0x9F, 0x64,
        0x2C, 0x01, 0x09, 0xED
    ])

    print(f"Starting UDP sender to {target_ip}:{target_port}")
    print(f"Test data: {test_data.hex(' ').upper()}")
    print("Press Ctrl+C to stop")
    print()

    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    try:
        count = 0
        while True:
            sock.sendto(test_data, (target_ip, target_port))
            count += 1
            print(f"Sent packet #{count}", end='\r')
            time.sleep(0.1)  # 10 packets per second
    except KeyboardInterrupt:
        print(f"\n\nStopped. Total packets sent: {count}")
    finally:
        sock.close()

if __name__ == "__main__":
    main()
