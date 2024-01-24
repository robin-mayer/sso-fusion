package com.robinmayer.ssofusionbackend.util.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.*
import javax.crypto.SecretKey
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JWTUtil(
	@Value("\${jwt.secret}") private val jwtSecret: String
) {
	val key: SecretKey = Keys.hmacShaKeyFor(jwtSecret.toByteArray())

	fun generateJWT(claims: List<JWTClaim>): String {
		val now = System.currentTimeMillis()

		val jwtBuilder = Jwts.builder()
			.issuer("https://ssofusion.robin-mayer.com")
			.expiration(Date(now + 1000 * 60 * 60 * 2)) // 2 hours
			.issuedAt(Date(now))
			.signWith(key)

		for(claim in claims) {
			jwtBuilder.claim(claim.key, claim.value)
		}

		return jwtBuilder.compact()
	}

	fun decodeJWT(token: String): Claims? {
		return Jwts
			.parser()
			.verifyWith(key)
			.build()
			.parseSignedClaims(token)
			.payload
	}

}