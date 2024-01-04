package com.robinmayer.ssofusion.controller

import com.robinmayer.ssofusion.model.dto.input.CreateClientApplicationDTO
import com.robinmayer.ssofusion.model.dto.output.ClientApplicationDTO
import com.robinmayer.ssofusion.service.ClientApplicationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("client-applications")
class ClientApplicationController(
	private val clientApplicationService: ClientApplicationService
) {

	@GetMapping("{clientApplicationId}")
	fun getClientApplicationById(
		@PathVariable("clientApplicationId") clientApplicationId: Long
	): ResponseEntity<ClientApplicationDTO> {
		return ResponseEntity(
			clientApplicationService.getClientApplication(clientApplicationId), HttpStatus.OK
		)
	}

	@PostMapping
	fun createClientApplication(
		@RequestBody createClientApplicationDTO: CreateClientApplicationDTO
	): ResponseEntity<ClientApplicationDTO> {
		return ResponseEntity(
			clientApplicationService.createClientApplication(createClientApplicationDTO),
			HttpStatus.CREATED
		)
	}

	@DeleteMapping("{clientApplicationId}")
	fun deleteClientApplication(
		@PathVariable("clientApplicationId") clientApplicationId: Long
	): ResponseEntity<Unit> {
		clientApplicationService.deleteClientApplication(clientApplicationId)
		return ResponseEntity(HttpStatus.OK)
	}

}