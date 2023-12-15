package com.robinmayer.soofusion

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SsoFusionApplication

fun main(args: Array<String>) {
	runApplication<SsoFusionApplication>(*args)
}
