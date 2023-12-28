package com.robinmayer.ssofusion.util

import java.util.UUID

fun generateClientSecret(): String {
	val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')
	return (1..64).map { charPool.random() }.joinToString("")
}

fun generateClientId(): String {
	return UUID.randomUUID().toString()
}