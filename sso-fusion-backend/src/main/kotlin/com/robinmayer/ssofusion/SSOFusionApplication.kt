package com.robinmayer.ssofusion

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SSOFusionApplication

fun main(args: Array<String>) {
	runApplication<SSOFusionApplication>(*args)
}
