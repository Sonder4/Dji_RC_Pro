package com.example.dji_rc_pro.domain.config

enum class ConnectionMode(val storageValue: String) {
    AUTO_PAIR("auto_pair"),
    MANUAL("manual");

    companion object {
        fun fromStorageValue(value: String?): ConnectionMode {
            return values().firstOrNull { it.storageValue == value } ?: AUTO_PAIR
        }
    }
}
