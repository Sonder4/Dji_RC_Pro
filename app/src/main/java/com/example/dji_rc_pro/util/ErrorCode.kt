package com.example.dji_rc_pro.util

enum class ErrorCode(
    val code: Int,
    val category: ErrorCategory,
    val severity: ErrorSeverity,
    val message: String
) {
    // 连接错误 (Connection Errors) - Category: CONNECTION
    UDP_CONNECTION_FAILED(
        1001,
        ErrorCategory.CONNECTION,
        ErrorSeverity.HIGH,
        "UDP连接失败"
    ),
    UDP_SOCKET_CREATE_FAILED(
        1002,
        ErrorCategory.CONNECTION,
        ErrorSeverity.HIGH,
        "UDP Socket创建失败"
    ),
    UDP_BIND_FAILED(
        1003,
        ErrorCategory.CONNECTION,
        ErrorSeverity.HIGH,
        "UDP绑定失败"
    ),
    UDP_SEND_FAILED(
        1004,
        ErrorCategory.CONNECTION,
        ErrorSeverity.MEDIUM,
        "UDP发送失败"
    ),
    UDP_RECEIVE_FAILED(
        1005,
        ErrorCategory.CONNECTION,
        ErrorSeverity.MEDIUM,
        "UDP接收失败"
    ),
    BLE_SCAN_FAILED(
        1101,
        ErrorCategory.CONNECTION,
        ErrorSeverity.HIGH,
        "BLE扫描失败"
    ),
    BLE_CONNECTION_FAILED(
        1102,
        ErrorCategory.CONNECTION,
        ErrorSeverity.HIGH,
        "BLE连接失败"
    ),
    BLE_CONNECTION_REFUSED(
        1106,
        ErrorCategory.CONNECTION,
        ErrorSeverity.HIGH,
        "BLE连接被拒绝"
    ),
    BLE_DISCONNECTED(
        1103,
        ErrorCategory.CONNECTION,
        ErrorSeverity.MEDIUM,
        "BLE断开连接"
    ),
    BLE_SERVICE_NOT_FOUND(
        1104,
        ErrorCategory.CONNECTION,
        ErrorSeverity.HIGH,
        "BLE服务未发现"
    ),
    BLE_CHARACTERISTIC_NOT_FOUND(
        1105,
        ErrorCategory.CONNECTION,
        ErrorSeverity.HIGH,
        "BLE特征值未发现"
    ),

    // 数据错误 (Data Errors) - Category: DATA
    DATA_ENCODE_FAILED(
        2001,
        ErrorCategory.DATA,
        ErrorSeverity.MEDIUM,
        "数据编码失败"
    ),
    DATA_DECODE_FAILED(
        2002,
        ErrorCategory.DATA,
        ErrorSeverity.MEDIUM,
        "数据解码失败"
    ),
    DATA_CHECKSUM_FAILED(
        2003,
        ErrorCategory.DATA,
        ErrorSeverity.HIGH,
        "校验失败"
    ),
    DATA_OVERFLOW(
        2004,
        ErrorCategory.DATA,
        ErrorSeverity.LOW,
        "数据队列溢出"
    ),
    DATA_NULL(
        2005,
        ErrorCategory.DATA,
        ErrorSeverity.MEDIUM,
        "数据为空"
    ),

    // 协议错误 (Protocol Errors) - Category: PROTOCOL
    PROTOCOL_VERSION_MISMATCH(
        3001,
        ErrorCategory.PROTOCOL,
        ErrorSeverity.HIGH,
        "协议版本不匹配"
    ),
    PROTOCOL_INVALID_HEADER(
        3002,
        ErrorCategory.PROTOCOL,
        ErrorSeverity.HIGH,
        "无效的协议头"
    ),
    PROTOCOL_INVALID_PACKET(
        3003,
        ErrorCategory.PROTOCOL,
        ErrorSeverity.MEDIUM,
        "无效的数据包"
    ),
    PROTOCOL_TIMEOUT(
        3004,
        ErrorCategory.PROTOCOL,
        ErrorSeverity.MEDIUM,
        "协议超时"
    ),

    // 系统错误 (System Errors) - Category: SYSTEM
    SYSTEM_OUT_OF_MEMORY(
        4001,
        ErrorCategory.SYSTEM,
        ErrorSeverity.CRITICAL,
        "内存不足"
    ),
    SYSTEM_THREAD_INTERRUPTED(
        4002,
        ErrorCategory.SYSTEM,
        ErrorSeverity.HIGH,
        "线程被中断"
    ),
    SYSTEM_PERMISSION_DENIED(
        4003,
        ErrorCategory.SYSTEM,
        ErrorSeverity.CRITICAL,
        "权限被拒绝"
    ),
    SYSTEM_UNKNOWN(
        4000,
        ErrorCategory.SYSTEM,
        ErrorSeverity.MEDIUM,
        "未知系统错误"
    ),

    // 状态错误 (State Errors) - Category: STATE
    STATE_NOT_INITIALIZED(
        5001,
        ErrorCategory.STATE,
        ErrorSeverity.HIGH,
        "未初始化"
    ),
    STATE_ALREADY_INITIALIZED(
        5002,
        ErrorCategory.STATE,
        ErrorSeverity.LOW,
        "已初始化"
    ),
    STATE_INVALID(
        5003,
        ErrorCategory.STATE,
        ErrorSeverity.MEDIUM,
        "状态无效"
    ),

    // 心跳错误 (Heartbeat Errors) - Category: HEARTBEAT
    HEARTBEAT_TIMEOUT(
        6001,
        ErrorCategory.HEARTBEAT,
        ErrorSeverity.HIGH,
        "心跳超时"
    ),
    HEARTBEAT_MISSED(
        6002,
        ErrorCategory.HEARTBEAT,
        ErrorSeverity.MEDIUM,
        "心跳丢失"
    ),
    HEARTBEAT_SEND_FAILED(
        6003,
        ErrorCategory.HEARTBEAT,
        ErrorSeverity.LOW,
        "心跳发送失败"
    );

    companion object {
        fun fromCode(code: Int): ErrorCode? {
            return entries.find { it.code == code }
        }

        fun byCategory(category: ErrorCategory): List<ErrorCode> {
            return entries.filter { it.category == category }
        }

        fun bySeverity(severity: ErrorSeverity): List<ErrorCode> {
            return entries.filter { it.severity == severity }
        }
    }
}

enum class ErrorCategory {
    CONNECTION,
    DATA,
    PROTOCOL,
    SYSTEM,
    STATE,
    HEARTBEAT
}

enum class ErrorSeverity {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL
}
