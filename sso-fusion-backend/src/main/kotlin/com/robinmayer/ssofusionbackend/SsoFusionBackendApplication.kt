package com.robinmayer.ssofusionbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SsoFusionBackendApplication

fun main(args: Array<String>) {
	runApplication<SsoFusionBackendApplication>(*args)
}
