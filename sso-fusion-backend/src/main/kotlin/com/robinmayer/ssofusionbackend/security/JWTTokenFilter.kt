package com.robinmayer.ssofusionbackend.security

import com.robinmayer.ssofusionbackend.util.AccountRoleUtil
import com.robinmayer.ssofusionbackend.util.jwt.JWTUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.security.Principal
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JWTTokenFilter(
	private val jwtUtil: JWTUtil
): OncePerRequestFilter() {

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain
	) {
		try {
			val authorizationHeader = request.getHeader("Authorization")
			if (authorizationHeader != null) {
				val token = authorizationHeader.replace("Bearer ", "")
				val claims = jwtUtil.decodeJWT(token) ?: throw java.lang.Exception("No claims found")

				val authorities: MutableList<SimpleGrantedAuthority> = mutableListOf()
				val roles = AccountRoleUtil.getAccountRoleFromClaim(claims["role"].toString())
				roles.forEach { role ->
					authorities.add(SimpleGrantedAuthority("ROLE_$role"))
				}

				val authentication = UsernamePasswordAuthenticationToken(
					claims["username"],
					null,
					authorities
				)
				SecurityContextHolder.getContext().authentication = authentication
			}
		} catch (e: Exception) {
			e.printStackTrace()
		} finally {
			filterChain.doFilter(request, response)
		}
	}
}