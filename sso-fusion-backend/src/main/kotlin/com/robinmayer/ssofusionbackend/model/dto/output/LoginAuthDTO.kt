package com.robinmayer.ssofusionbackend.model.dto.output

import com.robinmayer.ssofusionbackend.model.enums.AccountRole

class LoginAuthDTO (
	val username: String,
	val email: String,
	val role: AccountRole,
	val token: String
)