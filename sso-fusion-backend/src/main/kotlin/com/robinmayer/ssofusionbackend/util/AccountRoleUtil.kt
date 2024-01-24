package com.robinmayer.ssofusionbackend.util

import com.robinmayer.ssofusionbackend.model.enums.AccountRole

class AccountRoleUtil {

	companion object {
		fun getAccountRoleFromClaim(role: String): List<AccountRole> {
			return when (role) {
				"USER" -> listOf(AccountRole.USER)
				"DEVELOPER" -> listOf(AccountRole.USER, AccountRole.DEVELOPER)
				"ADMIN" -> listOf(AccountRole.USER, AccountRole.DEVELOPER, AccountRole.ADMIN)
				else -> emptyList()
			}
		}
	}
}