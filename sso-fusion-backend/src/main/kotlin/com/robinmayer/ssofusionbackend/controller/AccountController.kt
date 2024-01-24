package com.robinmayer.ssofusionbackend.controller

import com.robinmayer.ssofusionbackend.model.dto.input.LoginAccountDTO
import com.robinmayer.ssofusionbackend.model.dto.input.RegisterAccountDTO
import com.robinmayer.ssofusionbackend.model.dto.output.LoginAuthDTO
import com.robinmayer.ssofusionbackend.service.AccountService
import java.security.Principal
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class AccountController(
	private val accountService: AccountService
) {

	@PostMapping("/register")
	fun registerAccount(@RequestBody input: RegisterAccountDTO): ResponseEntity<Void> {
		val uri = accountService.createAccount(input)
		return ResponseEntity.created(uri).build()
	}

	@PostMapping("/login")
	fun login(@RequestBody loginAccountDTO: LoginAccountDTO): ResponseEntity<LoginAuthDTO> {
		val authData = accountService.login(loginAccountDTO)
		return ResponseEntity.ok(authData)
	}

	@GetMapping("/test")
	fun test(principal: Principal): ResponseEntity<String> {
		return ResponseEntity.ok("Hello ${principal.name}")
	}

}