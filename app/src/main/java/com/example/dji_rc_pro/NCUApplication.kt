package com.example.dji_rc_pro

import android.app.Application
import android.content.Context
import com.example.dji_rc_pro.domain.config.ConfigRepository

class NCUApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        // Initialize Config
        ConfigRepository.initialize(this)
    }
}
