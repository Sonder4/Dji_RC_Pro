package com.example.dji_rc_pro.domain.discovery

sealed class HostSelectionDecision {
    data class AutoSelected(val host: DiscoveryProtocol.DiscoveredHost) : HostSelectionDecision()
    data class RequiresUserChoice(val hosts: List<DiscoveryProtocol.DiscoveredHost>) : HostSelectionDecision()
    data object WaitForDiscovery : HostSelectionDecision()
}

object WifiPairingSelector {
    fun selectHost(
        rememberedHostId: String?,
        discoveredHosts: List<DiscoveryProtocol.DiscoveredHost>
    ): HostSelectionDecision {
        val candidates = discoveredHosts
            .filter { it.ready && !it.busy }
            .sortedWith(compareBy<DiscoveryProtocol.DiscoveredHost> { it.hostName }.thenBy { it.address })

        if (candidates.isEmpty()) {
            return HostSelectionDecision.WaitForDiscovery
        }

        if (!rememberedHostId.isNullOrBlank()) {
            candidates.firstOrNull { it.hostId == rememberedHostId }?.let {
                return HostSelectionDecision.AutoSelected(it)
            }
        }

        return when (candidates.size) {
            1 -> HostSelectionDecision.AutoSelected(candidates.first())
            else -> HostSelectionDecision.RequiresUserChoice(candidates)
        }
    }
}
