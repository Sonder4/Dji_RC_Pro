package com.example.dji_rc_pro

import com.example.dji_rc_pro.domain.discovery.DiscoveryProtocol
import com.example.dji_rc_pro.domain.discovery.HostSelectionDecision
import com.example.dji_rc_pro.domain.discovery.WifiPairingSelector
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class WifiDiscoveryUnitTest {

    @Test
    fun `build probe includes protocol header and proof`() {
        val payload = DiscoveryProtocol.buildProbe(
            clientId = "client-1",
            clientNonce = "nonce-1",
            pairCode = DiscoveryProtocol.DEFAULT_PAIR_CODE
        ).decodeToString()

        assertTrue(payload.contains("proto=${DiscoveryProtocol.PROTOCOL_VERSION}"))
        assertTrue(payload.contains("type=probe"))
        assertTrue(payload.contains("client_id=client-1"))
        assertTrue(payload.contains("client_nonce=nonce-1"))
        assertTrue(payload.contains("proof="))
    }

    @Test
    fun `parse offer accepts matching pair code and nonces`() {
        val probeState = DiscoveryProtocol.ProbeState(
            clientId = "client-1",
            clientNonce = "client-nonce-1",
            pairCode = DiscoveryProtocol.DEFAULT_PAIR_CODE
        )
        val hostId = "host-alpha"
        val hostNonce = "host-nonce-1"
        val controlPort = 1387
        val leaseMs = 5000L
        val proof = DiscoveryProtocol.generateProof(
            probeState.pairCode,
            listOf(
                "offer",
                DiscoveryProtocol.PROTOCOL_VERSION,
                hostId,
                probeState.clientId,
                probeState.clientNonce,
                hostNonce,
                controlPort.toString(),
                leaseMs.toString()
            ).joinToString("|")
        )
        val payload = """
            proto=${DiscoveryProtocol.PROTOCOL_VERSION}
            type=offer
            host_id=$hostId
            host_name=Alpha
            client_id=${probeState.clientId}
            client_nonce=${probeState.clientNonce}
            host_nonce=$hostNonce
            control_port=$controlPort
            discovery_port=1388
            lease_ms=$leaseMs
            ready=1
            busy=0
            ipv4=192.168.10.2
            ipv6=240e::2026
            proof=$proof

        """.trimIndent()

        val host = DiscoveryProtocol.parseOffer(payload, "240e::2026", probeState)

        assertNotNull(host)
        assertEquals(hostId, host!!.hostId)
        assertEquals("Alpha", host.hostName)
        assertEquals("192.168.10.2", host.ipv4Address)
        assertEquals("240e::2026", host.ipv6Address)
        assertEquals(DiscoveryProtocol.AddressFamily.IPV6, host.selectedFamily)
        assertTrue(host.ready)
        assertTrue(!host.busy)
    }

    @Test
    fun `parse offer rejects wrong pair code`() {
        val probeState = DiscoveryProtocol.ProbeState(
            clientId = "client-1",
            clientNonce = "client-nonce-1",
            pairCode = "RIGHT-CODE"
        )
        val payload = """
            proto=${DiscoveryProtocol.PROTOCOL_VERSION}
            type=offer
            host_id=host-alpha
            host_name=Alpha
            client_id=${probeState.clientId}
            client_nonce=${probeState.clientNonce}
            host_nonce=host-nonce-1
            control_port=1387
            discovery_port=1388
            lease_ms=5000
            ready=1
            busy=0
            proof=${DiscoveryProtocol.generateProof("WRONG-CODE", "offer|${DiscoveryProtocol.PROTOCOL_VERSION}|host-alpha|${probeState.clientId}|${probeState.clientNonce}|host-nonce-1|1387|5000")}

        """.trimIndent()

        val host = DiscoveryProtocol.parseOffer(payload, "192.168.10.2", probeState)

        assertNull(host)
    }

    @Test
    fun `build pair request includes host nonce and proof`() {
        val probeState = DiscoveryProtocol.ProbeState(
            clientId = "client-1",
            clientNonce = "client-nonce-1",
            pairCode = DiscoveryProtocol.DEFAULT_PAIR_CODE
        )
        val host = discoveredHost(
            hostId = "host-alpha",
            hostName = "Alpha",
            ipv4 = "192.168.10.2",
            ipv6 = null,
            selectedFamily = DiscoveryProtocol.AddressFamily.IPV4,
            hostNonce = "host-nonce-1"
        )

        val payload = DiscoveryProtocol.buildPairRequest(host, probeState).decodeToString()

        assertTrue(payload.contains("proto=${DiscoveryProtocol.PROTOCOL_VERSION}"))
        assertTrue(payload.contains("type=pair_request"))
        assertTrue(payload.contains("host_nonce=host-nonce-1"))
        assertTrue(payload.contains("client_id=${probeState.clientId}"))
        assertTrue(payload.contains("proof="))
    }

    @Test
    fun `remembered host wins when ready`() {
        val hostA = discoveredHost(
            hostId = "host-a",
            hostName = "Alpha",
            ipv4 = "192.168.10.11",
            ipv6 = null,
            selectedFamily = DiscoveryProtocol.AddressFamily.IPV4
        )
        val hostB = discoveredHost(
            hostId = "host-b",
            hostName = "Beta",
            ipv4 = "192.168.10.12",
            ipv6 = null,
            selectedFamily = DiscoveryProtocol.AddressFamily.IPV4
        )

        val decision = WifiPairingSelector.selectHost("host-b", listOf(hostA, hostB))

        assertTrue(decision is HostSelectionDecision.AutoSelected)
        assertEquals("host-b", (decision as HostSelectionDecision.AutoSelected).host.hostId)
    }

    @Test
    fun `single ready host auto selects`() {
        val host = discoveredHost(
            hostId = "host-a",
            hostName = "Alpha",
            ipv4 = "192.168.10.11",
            ipv6 = null,
            selectedFamily = DiscoveryProtocol.AddressFamily.IPV4
        )

        val decision = WifiPairingSelector.selectHost(null, listOf(host))

        assertTrue(decision is HostSelectionDecision.AutoSelected)
        assertEquals("host-a", (decision as HostSelectionDecision.AutoSelected).host.hostId)
    }

    @Test
    fun `multiple ready hosts require user choice`() {
        val hosts = listOf(
            discoveredHost(
                hostId = "host-a",
                hostName = "Alpha",
                ipv4 = "192.168.10.11",
                ipv6 = null,
                selectedFamily = DiscoveryProtocol.AddressFamily.IPV4
            ),
            discoveredHost(
                hostId = "host-b",
                hostName = "Beta",
                ipv4 = "192.168.10.12",
                ipv6 = null,
                selectedFamily = DiscoveryProtocol.AddressFamily.IPV4
            )
        )

        val decision = WifiPairingSelector.selectHost(null, hosts)

        assertTrue(decision is HostSelectionDecision.RequiresUserChoice)
        assertEquals(2, (decision as HostSelectionDecision.RequiresUserChoice).hosts.size)
    }

    private fun discoveredHost(
        hostId: String,
        hostName: String,
        ipv4: String?,
        ipv6: String?,
        selectedFamily: DiscoveryProtocol.AddressFamily,
        hostNonce: String = "host-nonce"
    ): DiscoveryProtocol.DiscoveredHost {
        return DiscoveryProtocol.DiscoveredHost(
            hostId = hostId,
            hostName = hostName,
            controlPort = 1387,
            discoveryPort = 1388,
            ready = true,
            busy = false,
            leaseMs = 5000L,
            hostNonce = hostNonce,
            ipv4Address = ipv4,
            ipv6Address = ipv6,
            selectedFamily = selectedFamily,
            lastSeenAtMs = 1L
        )
    }
}
