package com.robinmayer.ssofusion.repository.shared

import org.springframework.data.jpa.repository.JpaRepository

fun <T, ID : Any> JpaRepository<T, ID>.selectById(id: ID): T? = findById(id).orElse(null)

fun <T, ID : Any> JpaRepository<T, ID>.selectByIdOrThrow(id: ID, ex: Throwable): T = findById(id).orElseThrow { ex }