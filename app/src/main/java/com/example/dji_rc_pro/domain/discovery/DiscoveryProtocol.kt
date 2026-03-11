package com.example.dji_rc_pro.domain.discovery

import java.nio.charset.StandardCharsets
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object DiscoveryProtocol {
    const val PROTOCOL_VERSION = "RCBRIDGE_DISCOVERY/2"
    const val DEFAULT_DISCOVERY_GROUP = "ff12::2026"
    const val DEFAULT_DISCOVERY_PORT = 1388
    const val DEFAULT_CONTROL_PORT = 1387
    const val DEFAULT_PAIR_CODE = "CHANGE_ME_PAIR_CODE"
    const val CLIENT_NAME = "RCBridge_Controller"

    enum class AddressFamily(val wireValue: String) {
        IPV4("ipv4"),
        IPV6("ipv6");

        companion object {
            fun fromWireValue(value: String?): AddressFamily? {
                return values().firstOrNull { it.wireValue == value }
            }
        }
    }

    data class DiscoveredHost(
        val hostId: String,
        val hostName: String,
        val controlPort: Int,
        val discoveryPort: Int,
        val ready: Boolean,
        val busy: Boolean,
        val leaseMs: Long,
        val hostNonce: String,
        val ipv4Address: String?,
        val ipv6Address: String?,
        val selectedFamily: AddressFamily,
        val lastSeenAtMs: Long
    ) {
        val address: String
            get() = when (selectedFamily) {
                AddressFamily.IPV6 -> ipv6Address ?: ipv4Address.orEmpty()
                AddressFamily.IPV4 -> ipv4Address ?: ipv6Address.orEmpty()
            }

        val label: String
            get() = "$hostName ($address:$controlPort)"
    }

    data class PairAck(
        val hostId: String,
        val address: String,
        val sessionId: String,
        val leaseMs: Long,
        val controlPort: Int,
        val selectedFamily: AddressFamily
    )

    data class PairBusy(
        val hostId: String,
        val address: String,
        val leaseMs: Long,
        val reason: String?
    )

    data class ProbeState(
        val clientId: String,
        val clientNonce: String,
        val pairCode: String
    )

    data class PendingPairContext(
        val hostId: String,
        val hostNonce: String,
        val clientId: String,
        val clientNonce: String,
        val pairCode: String
    )

    private fun parseFields(payload: String): Map<String, String> {
        return payload
            .lineSequence()
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .mapNotNull { line ->
                val separator = line.indexOf('=')
                if (separator <= 0) {
                    null
                } else {
                    line.substring(0, separator) to line.substring(separator + 1)
                }
            }
            .toMap()
    }

    private fun buildMessage(fields: Map<String, String>): ByteArray {
        val payload = buildString {
            fields.forEach { (key, value) ->
                append(key)
                append('=')
                append(value)
                append('\n')
            }
            append('\n')
        }
        return payload.toByteArray(StandardCharsets.UTF_8)
    }

    private fun hmacHex(pairCode: String, payload: String): String {
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(pairCode.toByteArray(StandardCharsets.UTF_8), "HmacSHA256"))
        return mac.doFinal(payload.toByteArray(StandardCharsets.UTF_8)).joinToString("") { "%02x".format(it) }
    }

    fun generateProof(pairCode: String, payload: String): String = hmacHex(pairCode, payload)

    private fun buildProbeSignature(clientId: String, clientName: String, clientNonce: String): String {
        return listOf("probe", PROTOCOL_VERSION, clientId, clientNonce, clientName).joinToString("|")
    }

    private fun buildOfferSignature(
        hostId: String,
        clientId: String,
        clientNonce: String,
        hostNonce: String,
        controlPort: Int,
        leaseMs: Long
    ): String {
        return listOf(
            "offer",
            PROTOCOL_VERSION,
            hostId,
            clientId,
            clientNonce,
            hostNonce,
            controlPort.toString(),
            leaseMs.toString()
        ).joinToString("|")
    }

    private fun buildPairSignature(
        hostId: String,
        clientId: String,
        clientNonce: String,
        hostNonce: String,
        controlPort: Int,
        leaseMs: Long
    ): String {
        return listOf(
            "pair",
            PROTOCOL_VERSION,
            hostId,
            clientId,
            clientNonce,
            hostNonce,
            controlPort.toString(),
            leaseMs.toString()
        ).joinToString("|")
    }

    private fun buildAckSignature(
        hostId: String,
        clientId: String,
        clientNonce: String,
        hostNonce: String,
        sessionId: String,
        controlPort: Int,
        leaseMs: Long,
        selectedFamily: AddressFamily,
        address: String
    ): String {
        return listOf(
            "ack",
            PROTOCOL_VERSION,
            hostId,
            clientId,
            clientNonce,
            hostNonce,
            sessionId,
            controlPort.toString(),
            leaseMs.toString(),
            selectedFamily.wireValue,
            address
        ).joinToString("|")
    }

    private fun buildBusySignature(
        hostId: String,
        clientId: String,
        clientNonce: String,
        hostNonce: String,
        reason: String,
        leaseMs: Long
    ): String {
        return listOf(
            "busy",
            PROTOCOL_VERSION,
            hostId,
            clientId,
            clientNonce,
            hostNonce,
            reason,
            leaseMs.toString()
        ).joinToString("|")
    }

    fun buildProbe(
        clientId: String,
        clientNonce: String,
        pairCode: String,
        clientName: String = CLIENT_NAME,
        supportIpv4: Boolean = true,
        supportIpv6: Boolean = true
    ): ByteArray {
        val proof = hmacHex(pairCode, buildProbeSignature(clientId, clientName, clientNonce))
        return buildMessage(
            linkedMapOf(
                "proto" to PROTOCOL_VERSION,
                "type" to "probe",
                "client_id" to clientId,
                "client_name" to clientName,
                "client_nonce" to clientNonce,
                "support_ipv4" to if (supportIpv4) "1" else "0",
                "support_ipv6" to if (supportIpv6) "1" else "0",
                "proof" to proof
            )
        )
    }

    fun parseOffer(
        payload: String,
        sourceAddress: String,
        probeState: ProbeState,
        nowMs: Long = System.currentTimeMillis()
    ): DiscoveredHost? {
        val fields = parseFields(payload)
        if (fields["proto"] != PROTOCOL_VERSION || fields["type"] != "offer") {
            return null
        }

        val hostId = fields["host_id"] ?: return null
        val hostName = fields["host_name"] ?: hostId
        val clientId = fields["client_id"] ?: return null
        val clientNonce = fields["client_nonce"] ?: return null
        val hostNonce = fields["host_nonce"] ?: return null
        val controlPort = fields["control_port"]?.toIntOrNull() ?: DEFAULT_CONTROL_PORT
        val discoveryPort = fields["discovery_port"]?.toIntOrNull() ?: DEFAULT_DISCOVERY_PORT
        val leaseMs = fields["lease_ms"]?.toLongOrNull() ?: 0L
        val proof = fields["proof"] ?: return null

        if (clientId != probeState.clientId || clientNonce != probeState.clientNonce) {
            return null
        }

        val expectedProof = hmacHex(
            probeState.pairCode,
            buildOfferSignature(hostId, clientId, clientNonce, hostNonce, controlPort, leaseMs)
        )
        if (proof != expectedProof) {
            return null
        }

        val ipv4Address = fields["ipv4"]?.takeIf { it.isNotBlank() }
            ?: sourceAddress.takeIf { '.' in it }
        val ipv6Address = fields["ipv6"]?.takeIf { it.isNotBlank() }
            ?: sourceAddress.takeIf { ':' in it }
        val selectedFamily = when {
            !ipv6Address.isNullOrBlank() -> AddressFamily.IPV6
            !ipv4Address.isNullOrBlank() -> AddressFamily.IPV4
            else -> return null
        }

        return DiscoveredHost(
            hostId = hostId,
            hostName = hostName,
            controlPort = controlPort,
            discoveryPort = discoveryPort,
            ready = fields["ready"] == "1",
            busy = fields["busy"] == "1",
            leaseMs = leaseMs,
            hostNonce = hostNonce,
            ipv4Address = ipv4Address,
            ipv6Address = ipv6Address,
            selectedFamily = selectedFamily,
            lastSeenAtMs = nowMs
        )
    }

    fun buildPairRequest(host: DiscoveredHost, probeState: ProbeState, clientName: String = CLIENT_NAME): ByteArray {
        val proof = hmacHex(
            probeState.pairCode,
            buildPairSignature(
                hostId = host.hostId,
                clientId = probeState.clientId,
                clientNonce = probeState.clientNonce,
                hostNonce = host.hostNonce,
                controlPort = host.controlPort,
                leaseMs = host.leaseMs
            )
        )
        return buildMessage(
            linkedMapOf(
                "proto" to PROTOCOL_VERSION,
                "type" to "pair_request",
                "host_id" to host.hostId,
                "client_id" to probeState.clientId,
                "client_name" to clientName,
                "client_nonce" to probeState.clientNonce,
                "host_nonce" to host.hostNonce,
                "control_port" to host.controlPort.toString(),
                "proof" to proof
            )
        )
    }

    fun parsePairAck(payload: String, sourceAddress: String, context: PendingPairContext): PairAck? {
        val fields = parseFields(payload)
        if (fields["proto"] != PROTOCOL_VERSION || fields["type"] != "pair_ack") {
            return null
        }

        val hostId = fields["host_id"] ?: return null
        val sessionId = fields["session_id"] ?: return null
        val leaseMs = fields["lease_ms"]?.toLongOrNull() ?: return null
        val controlPort = fields["control_port"]?.toIntOrNull() ?: DEFAULT_CONTROL_PORT
        val selectedFamily = AddressFamily.fromWireValue(fields["selected_family"]) ?: return null
        val address = fields["address"]?.takeIf { it.isNotBlank() } ?: sourceAddress
        val proof = fields["proof"] ?: return null

        if (hostId != context.hostId) {
            return null
        }

        val expectedProof = hmacHex(
            context.pairCode,
            buildAckSignature(
                hostId = hostId,
                clientId = context.clientId,
                clientNonce = context.clientNonce,
                hostNonce = context.hostNonce,
                sessionId = sessionId,
                controlPort = controlPort,
                leaseMs = leaseMs,
                selectedFamily = selectedFamily,
                address = address
            )
        )
        if (proof != expectedProof) {
            return null
        }

        return PairAck(
            hostId = hostId,
            address = address,
            sessionId = sessionId,
            leaseMs = leaseMs,
            controlPort = controlPort,
            selectedFamily = selectedFamily
        )
    }

    fun parsePairBusy(payload: String, sourceAddress: String, context: PendingPairContext): PairBusy? {
        val fields = parseFields(payload)
        if (fields["proto"] != PROTOCOL_VERSION || fields["type"] != "pair_busy") {
            return null
        }

        val hostId = fields["host_id"] ?: return null
        val leaseMs = fields["lease_ms"]?.toLongOrNull() ?: 0L
        val reason = fields["reason"] ?: "busy"
        val proof = fields["proof"] ?: return null

        if (hostId != context.hostId) {
            return null
        }

        val expectedProof = hmacHex(
            context.pairCode,
            buildBusySignature(
                hostId = hostId,
                clientId = context.clientId,
                clientNonce = context.clientNonce,
                hostNonce = context.hostNonce,
                reason = reason,
                leaseMs = leaseMs
            )
        )
        if (proof != expectedProof) {
            return null
        }

        return PairBusy(
            hostId = hostId,
            address = sourceAddress,
            leaseMs = leaseMs,
            reason = reason
        )
    }

    fun buildUnpair(clientId: String, sessionId: String?): ByteArray {
        val fields = linkedMapOf(
            "proto" to PROTOCOL_VERSION,
            "type" to "unpair",
            "client_id" to clientId
        )
        if (!sessionId.isNullOrBlank()) {
            fields["session_id"] = sessionId
        }
        return buildMessage(fields)
    }

    fun choosePreferredFamily(ipv4Address: String?, ipv6Address: String?): AddressFamily {
        return if (!ipv6Address.isNullOrBlank()) {
            AddressFamily.IPV6
        } else {
            AddressFamily.IPV4
        }
    }

    fun mergeHost(existing: DiscoveredHost?, incoming: DiscoveredHost): DiscoveredHost {
        if (existing == null) {
            return incoming
        }

        val ipv4Address = incoming.ipv4Address ?: existing.ipv4Address
        val ipv6Address = incoming.ipv6Address ?: existing.ipv6Address
        val selectedFamily = choosePreferredFamily(ipv4Address, ipv6Address)

        return incoming.copy(
            ipv4Address = ipv4Address,
            ipv6Address = ipv6Address,
            selectedFamily = selectedFamily,
            hostNonce = incoming.hostNonce,
            lastSeenAtMs = maxOf(existing.lastSeenAtMs, incoming.lastSeenAtMs)
        )
    }
}
