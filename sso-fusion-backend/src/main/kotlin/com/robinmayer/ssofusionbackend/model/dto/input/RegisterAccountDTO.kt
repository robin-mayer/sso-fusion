package com.robinmayer.ssofusionbackend.model.dto.input

import com.robinmayer.ssofusionbackend.model.enums.AccountRole

class RegisterAccountDTO (
	val username: String,
	val email: String,
	val password: String,
	val role: AccountRole
)