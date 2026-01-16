package com.example.dji_rc_pro

import android.app.Application
import android.content.Context
import com.example.dji_rc_pro.domain.config.ConfigRepository
import com.example.dji_rc_pro.domain.msdk.MsdkManager

class NCUApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // Strictly follow reference project: MSDKRemote
        try {
            android.util.Log.i("NCUApplication", "attachBaseContext: Installing MSDK Helper")
            com.cySdkyc.clx.Helper.install(this)
            android.util.Log.i("NCUApplication", "attachBaseContext: MSDK Helper installed successfully")
        } catch (e: Throwable) {
            android.util.Log.e("NCUApplication", "Failed to install MSDK Helper: ${e.message}")
            e.printStackTrace()
        }
    }

    override fun onCreate() {
        super.onCreate()
        // Initialize Config
        ConfigRepository.initialize(this)
        // Initialize MSDK
        try {
            MsdkManager.instance.init(this)
        } catch (e: Exception) {
            android.util.Log.e("NCUApplication", "Failed to init MsdkManager: ${e.message}")
            e.printStackTrace()
        }
    }
}
