package com.robinmayer.ssofusionbackend.repository

import com.robinmayer.ssofusionbackend.model.entity.Account
import org.springframework.data.jpa.repository.JpaRepository


interface AccountRepository: JpaRepository<Account, Long> {
	fun existsByEmailOrUsername(email: String, username: String): Boolean
	fun findByEmailOrUsername(email: String, username: String): Account?
}