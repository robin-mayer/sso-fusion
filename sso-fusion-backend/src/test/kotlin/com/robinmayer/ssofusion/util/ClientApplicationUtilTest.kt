package com.robinmayer.ssofusion.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test


class ClientApplicationUtilTest {

	@Test
	fun generateClientSecretCheckLength() {
		assertEquals(64, generateClientSecret().length)
	}

	@Test
	fun generateClientIdCheck() {
		val clientId = generateClientId()
		println(clientId)
		assertEquals(36, clientId.length)
		assertEquals("-", clientId[8].toString())
		assertEquals("-", clientId[13].toString())
		assertEquals("-", clientId[18].toString())
		assertEquals("-", clientId[23].toString())
		assertNotEquals(clientId, generateClientId())
	}

}