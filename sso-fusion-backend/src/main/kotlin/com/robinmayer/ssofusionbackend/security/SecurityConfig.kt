package com.robinmayer.ssofusionbackend.security

import com.robinmayer.ssofusionbackend.util.jwt.JWTUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
	private val jwtUtil: JWTUtil
) {

	@Bean
	fun passwordEncoder(): BCryptPasswordEncoder {
		return BCryptPasswordEncoder()
	}

	@Bean
	@Throws(Exception::class)
	fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
		return httpSecurity
			.addFilterBefore(JWTTokenFilter(jwtUtil), UsernamePasswordAuthenticationFilter::class.java)
			.authorizeHttpRequests { auth ->
				auth
					.requestMatchers(HttpMethod.POST,"/account/register").permitAll()
					.requestMatchers(HttpMethod.POST,"/account/login").permitAll()
					.requestMatchers(HttpMethod.GET,"/account/test").hasAnyRole("DEVELOPER", "ADMIN")
					.anyRequest().authenticated()
			}
			.csrf{ csrf -> csrf.disable()}
			.build()
	}

}