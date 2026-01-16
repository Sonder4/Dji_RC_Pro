package com.example.dji_rc_pro.domain.config

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ConfigRepository private constructor(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        private var INSTANCE: ConfigRepository? = null
        
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ConfigRepository(context)
            }
        }
        
        fun get(): ConfigRepository {
            return INSTANCE ?: throw IllegalStateException("ConfigRepository not initialized")
        }
        
        private val KEY_TARGET_IP = stringPreferencesKey("target_ip")
        private val KEY_TARGET_PORT = intPreferencesKey("target_port")
    }

    val targetIp: Flow<String> = dataStore.data.map { prefs ->
        prefs[KEY_TARGET_IP] ?: "192.168.1.100"
    }

    val targetPort: Flow<Int> = dataStore.data.map { prefs ->
        prefs[KEY_TARGET_PORT] ?: 8888
    }

    suspend fun setTargetIp(ip: String) {
        dataStore.edit { prefs ->
            prefs[KEY_TARGET_IP] = ip
        }
    }

    suspend fun setTargetPort(port: Int) {
        dataStore.edit { prefs ->
            prefs[KEY_TARGET_PORT] = port
        }
    }
}
