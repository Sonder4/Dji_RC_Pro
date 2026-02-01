#!/usr/bin/env python3
"""UDP test sender for DJI RC Pro debugging"""

import socket
import time
import sys

def send_test_packets(target_ip, target_port, count=5, interval=1.0):
    """发送测试UDP数据包"""
    
    # 测试数据 - 简单的二进制数据
    test_data = bytes([
        0x52, 0x43, 0xA2, 0x02, 0x1C, 0x03, 0x0C, 0x00, 0x00, 0x03, 0xF0, 
        0x00, 0x00, 0x00, 0x07, 0x9E, 0x9F, 0x64, 0x2C
    ])
    
    print(f"Sending {count} UDP packets to {target_ip}:{target_port}")
    print(f"Data: {test_data.hex(' ').upper()}")
    print("-" * 50)
    
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    
    try:
        for i in range(count):
            sock.sendto(test_data, (target_ip, target_port))
            print(f"Packet {i+1}/{count} sent")
            if i < count - 1:
                time.sleep(interval)
        
        print("-" * 50)
        print(f"All {count} packets sent successfully!")
        
    except Exception as e:
        print(f"Error: {e}")
    finally:
        sock.close()

if __name__ == "__main__":
    # DJI RC Pro 的IP和端口
    TARGET_IP = "192.168.1.86"    # 设备IP
    TARGET_PORT = 1346             # 设备监听端口
    
    send_test_packets(TARGET_IP, TARGET_PORT, count=5, interval=0.5)
