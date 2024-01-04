package com.robinmayer.ssofusion.model.db

import com.robinmayer.ssofusion.model.dto.output.ClientApplicationDTO
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "client_application")
class ClientApplication(
	val name: String,
	val clientId: String,
	val clientSecret: String
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null

	fun getDTO(): ClientApplicationDTO {
		return ClientApplicationDTO(
			id!!,
			name,
			clientId,
			clientSecret
		)
	}
}