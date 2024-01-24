package com.robinmayer.ssofusionbackend.model.dto.output

import com.robinmayer.ssofusionbackend.model.enums.AccountRole

class AccountDTO (
	val username: String,
	val email: String,
	val role: AccountRole,
	enabled: Boolean
)