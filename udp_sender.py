#!/usr/bin/env python3
"""UDP test sender for DJI RC Pro"""

import socket
import time
import sys

def main():
    # Target: DJI RC Pro IP and port
    # 默认配置，可通过参数修改
    target_ip = "192.168.1.86"  # DJI RC Pro 默认IP
    target_port = 1346          # DJI RC Pro 默认接收端口

    print(f"Starting UDP sender to {target_ip}:{target_port}")
    print("Press Ctrl+C to stop")
    print()

    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    try:
        count = 0
        while True:
            # 用户可在此处自定义发送数据
            # 示例：发送一个简单的测试数据包
            test_data = bytes([0xAA, 0x01, 0x02, 0x03, 0x04, 0x05])
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
