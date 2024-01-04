package com.robinmayer.ssofusion.service

import com.robinmayer.ssofusion.model.db.ClientApplication
import com.robinmayer.ssofusion.model.dto.input.CreateClientApplicationDTO
import com.robinmayer.ssofusion.model.dto.output.ClientApplicationDTO
import com.robinmayer.ssofusion.repository.ClientApplicationRepository
import com.robinmayer.ssofusion.repository.shared.selectByIdOrThrow
import com.robinmayer.ssofusion.util.generateClientId
import com.robinmayer.ssofusion.util.generateClientSecret
import org.springframework.stereotype.Service

@Service
class ClientApplicationService(
	private val clientApplicationRepository: ClientApplicationRepository
) {

	fun getClientApplication(clientApplicationId: Long): ClientApplicationDTO {
		val result = clientApplicationRepository.selectByIdOrThrow(
			clientApplicationId, Throwable("No client application found with id: ${clientApplicationId}")
		)
		return result.getDTO()
	}

	fun createClientApplication(input: CreateClientApplicationDTO): ClientApplicationDTO {
		val newClientApplication = clientApplicationRepository.save(
			ClientApplication(
				input.name, generateClientId(), generateClientSecret()
			)
		)
		return newClientApplication.getDTO()
	}

	fun deleteClientApplication(clientApplicationId: Long) {
		val clientApplication = clientApplicationRepository.selectByIdOrThrow(
			clientApplicationId, Throwable("No client application found with id: ${clientApplicationId}")
		)
		clientApplicationRepository.delete(clientApplication)
	}

}