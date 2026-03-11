package com.example.dji_rc_pro

import android.app.Application
import android.content.Context
import com.example.dji_rc_pro.domain.config.ConfigRepository
import timber.log.Timber

class RcProApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        if (Timber.treeCount == 0) {
            Timber.plant(Timber.DebugTree())
        }
        ConfigRepository.initialize(this)
    }
}
