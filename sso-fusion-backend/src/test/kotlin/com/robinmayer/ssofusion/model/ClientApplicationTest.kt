package com.robinmayer.ssofusion.model

import com.robinmayer.ssofusion.model.db.ClientApplication
import com.robinmayer.ssofusion.model.dto.input.CreateClientApplicationDTO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ClientApplicationTest {

	@Test
	fun getClientApplicationModel() {
		val clientApplication = ClientApplication(
			"Test Client Application",
			"test-client-id",
			"test-client-secret"
		).apply {
			id = 1
		}
		assertEquals(1, clientApplication.id)
		assertEquals("Test Client Application", clientApplication.name)
		assertEquals("test-client-id", clientApplication.clientId)
		assertEquals("test-client-secret", clientApplication.clientSecret)

		val clientApplicationDTO = clientApplication.getDTO()
		assertEquals(1, clientApplicationDTO.id)
		assertEquals("Test Client Application", clientApplicationDTO.name)
		assertEquals("test-client-id", clientApplicationDTO.clientId)
		assertEquals("test-client-secret", clientApplicationDTO.clientSecret)
	}

	@Test
	fun setApplicationModel() {
		val createClientApplicationDTO = CreateClientApplicationDTO(
			"Test Client Application"
		)
		assertEquals("Test Client Application", createClientApplicationDTO.name)

		val clientApplication = ClientApplication(
			createClientApplicationDTO.name,
			"test-client-id",
			"test-client-secret"
		).apply {
			id = 1
		}
		assertEquals(1, clientApplication.id)
		assertEquals("Test Client Application", clientApplication.name)
		assertEquals("test-client-id", clientApplication.clientId)
		assertEquals("test-client-secret", clientApplication.clientSecret)
	}

}