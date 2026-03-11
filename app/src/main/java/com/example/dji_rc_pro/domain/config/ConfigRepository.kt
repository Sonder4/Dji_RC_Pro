package com.example.dji_rc_pro.domain.config

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.dji_rc_pro.domain.discovery.DiscoveryProtocol
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.UUID

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
        private val KEY_LOCAL_PORT = intPreferencesKey("local_port")
        private val KEY_CONNECTION_MODE = stringPreferencesKey("connection_mode")
        private val KEY_TRANSPORT_ISOLATION_MODE = stringPreferencesKey("transport_isolation_mode")
        private val KEY_CLIENT_ID = stringPreferencesKey("client_id")
        private val KEY_PAIR_CODE = stringPreferencesKey("pair_code")
        private val KEY_PAIRED_HOST_ID = stringPreferencesKey("paired_host_id")
        private val KEY_PAIRED_HOST_NAME = stringPreferencesKey("paired_host_name")
        private val KEY_PAIRED_HOST_IPV4 = stringPreferencesKey("paired_host_ipv4")
        private val KEY_PAIRED_HOST_IPV6 = stringPreferencesKey("paired_host_ipv6")
        private val KEY_PAIRED_HOST_FAMILY = stringPreferencesKey("paired_host_family")
        private val KEY_PAIRED_CONTROL_PORT = intPreferencesKey("paired_control_port")
    }

    val targetIp: Flow<String> = dataStore.data.map { prefs ->
        prefs[KEY_TARGET_IP] ?: "198.51.100.83"
    }

    val targetPort: Flow<Int> = dataStore.data.map { prefs ->
        prefs[KEY_TARGET_PORT] ?: 1387
    }

    val localPort: Flow<Int> = dataStore.data.map { prefs ->
        prefs[KEY_LOCAL_PORT] ?: 1346
    }

    val connectionMode: Flow<ConnectionMode> = dataStore.data.map { prefs ->
        ConnectionMode.fromStorageValue(prefs[KEY_CONNECTION_MODE])
    }

    val transportIsolationMode: Flow<TransportIsolationMode> = dataStore.data.map { prefs ->
        TransportIsolationMode.fromStorageValue(prefs[KEY_TRANSPORT_ISOLATION_MODE])
    }

    val clientId: Flow<String> = dataStore.data.map { prefs ->
        prefs[KEY_CLIENT_ID] ?: ""
    }

    val pairCode: Flow<String> = dataStore.data.map { prefs ->
        prefs[KEY_PAIR_CODE] ?: DiscoveryProtocol.DEFAULT_PAIR_CODE
    }

    val pairedHostId: Flow<String?> = dataStore.data.map { prefs ->
        prefs[KEY_PAIRED_HOST_ID]
    }

    val pairedHostName: Flow<String?> = dataStore.data.map { prefs ->
        prefs[KEY_PAIRED_HOST_NAME]
    }

    val pairedHostIpv4: Flow<String?> = dataStore.data.map { prefs ->
        prefs[KEY_PAIRED_HOST_IPV4]
    }

    val pairedHostIpv6: Flow<String?> = dataStore.data.map { prefs ->
        prefs[KEY_PAIRED_HOST_IPV6]
    }

    val pairedHostFamily: Flow<DiscoveryProtocol.AddressFamily?> = dataStore.data.map { prefs ->
        DiscoveryProtocol.AddressFamily.fromWireValue(prefs[KEY_PAIRED_HOST_FAMILY])
    }

    val pairedHostAddress: Flow<String?> = dataStore.data.map { prefs ->
        val preferredFamily = DiscoveryProtocol.AddressFamily.fromWireValue(prefs[KEY_PAIRED_HOST_FAMILY])
        val ipv4 = prefs[KEY_PAIRED_HOST_IPV4]
        val ipv6 = prefs[KEY_PAIRED_HOST_IPV6]
        when (preferredFamily) {
            DiscoveryProtocol.AddressFamily.IPV6 -> ipv6 ?: ipv4
            DiscoveryProtocol.AddressFamily.IPV4 -> ipv4 ?: ipv6
            null -> ipv6 ?: ipv4
        }
    }

    val pairedControlPort: Flow<Int?> = dataStore.data.map { prefs ->
        prefs[KEY_PAIRED_CONTROL_PORT]
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

    suspend fun setLocalPort(port: Int) {
        dataStore.edit { prefs ->
            prefs[KEY_LOCAL_PORT] = port
        }
    }

    suspend fun setConnectionMode(mode: ConnectionMode) {
        dataStore.edit { prefs ->
            prefs[KEY_CONNECTION_MODE] = mode.storageValue
        }
    }

    suspend fun setTransportIsolationMode(mode: TransportIsolationMode) {
        dataStore.edit { prefs ->
            prefs[KEY_TRANSPORT_ISOLATION_MODE] = mode.storageValue
        }
    }

    suspend fun setPairCode(pairCode: String) {
        dataStore.edit { prefs ->
            prefs[KEY_PAIR_CODE] = pairCode.ifBlank { DiscoveryProtocol.DEFAULT_PAIR_CODE }
        }
    }

    suspend fun getOrCreateClientId(): String {
        val existing = clientId.first()
        if (existing.isNotBlank()) {
            return existing
        }

        val newId = UUID.randomUUID().toString()
        dataStore.edit { prefs ->
            prefs[KEY_CLIENT_ID] = newId
        }
        return newId
    }

    suspend fun savePairedHost(
        hostId: String,
        hostName: String,
        ipv4Address: String?,
        ipv6Address: String?,
        preferredFamily: DiscoveryProtocol.AddressFamily,
        controlPort: Int
    ) {
        dataStore.edit { prefs ->
            prefs[KEY_PAIRED_HOST_ID] = hostId
            prefs[KEY_PAIRED_HOST_NAME] = hostName
            if (ipv4Address.isNullOrBlank()) {
                prefs.remove(KEY_PAIRED_HOST_IPV4)
            } else {
                prefs[KEY_PAIRED_HOST_IPV4] = ipv4Address
            }
            if (ipv6Address.isNullOrBlank()) {
                prefs.remove(KEY_PAIRED_HOST_IPV6)
            } else {
                prefs[KEY_PAIRED_HOST_IPV6] = ipv6Address
            }
            prefs[KEY_PAIRED_HOST_FAMILY] = preferredFamily.wireValue
            prefs[KEY_PAIRED_CONTROL_PORT] = controlPort
        }
    }

    suspend fun clearPairing() {
        dataStore.edit { prefs ->
            prefs.remove(KEY_PAIRED_HOST_ID)
            prefs.remove(KEY_PAIRED_HOST_NAME)
            prefs.remove(KEY_PAIRED_HOST_IPV4)
            prefs.remove(KEY_PAIRED_HOST_IPV6)
            prefs.remove(KEY_PAIRED_HOST_FAMILY)
            prefs.remove(KEY_PAIRED_CONTROL_PORT)
        }
    }
}
