package com.robinmayer.ssofusionbackend.model.entity

import com.robinmayer.ssofusionbackend.model.enums.AccountRole
import com.robinmayer.ssofusionbackend.model.dto.output.AccountDTO
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Account (
	val email: String,
	val username: String,
	val password: String,
	val role: AccountRole,
	val enabled: Boolean = false
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null

	fun toDTO(): AccountDTO {
		return AccountDTO(
			username,
			email,
			role,
			enabled
		)
	}
}