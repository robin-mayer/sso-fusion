package com.robinmayer.ssofusionbackend.service

import com.robinmayer.ssofusionbackend.model.dto.input.LoginAccountDTO
import com.robinmayer.ssofusionbackend.model.dto.input.RegisterAccountDTO
import com.robinmayer.ssofusionbackend.model.dto.output.LoginAuthDTO
import com.robinmayer.ssofusionbackend.model.entity.Account
import com.robinmayer.ssofusionbackend.repository.AccountRepository
import com.robinmayer.ssofusionbackend.util.jwt.JWTUtil
import com.robinmayer.ssofusionbackend.util.jwt.JWTClaim
import io.jsonwebtoken.security.Keys
import java.lang.Exception
import java.net.URI
import javax.crypto.SecretKey
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService (
	private val accountRepository: AccountRepository,
	private val passwordEncoder: BCryptPasswordEncoder,
	private val jwtUtil: JWTUtil
) {

	fun createAccount(input: RegisterAccountDTO): URI {
		if (accountRepository.existsByEmailOrUsername(input.email, input.username)) {
			throw Exception("Email and/or username already exists")
		}

		val newAccount = Account(
			input.email,
			input.username,
			passwordEncoder.encode(input.password),
			input.role,
			false
		)

		accountRepository.save(newAccount)

		return URI("/account/login")
	}

	fun login(input: LoginAccountDTO): LoginAuthDTO {
		val account = accountRepository.findByEmailOrUsername(input.userNameOrEmail, input.userNameOrEmail) ?: throw Exception("Account not found")
		if(!account.enabled) {
			throw Exception("Account not enabled")
		}

		if (!passwordEncoder.matches(input.password, account.password)) {
			throw Exception("Password incorrect")
		}

		val jwt = jwtUtil.generateJWT(
			listOf(
				JWTClaim("email", account.email),
				JWTClaim("username", account.username),
				JWTClaim("role", account.role.toString())
			)
		)

		return LoginAuthDTO(
			account.username,
			account.email,
			account.role,
			jwt
		)
	}
}