package com.robinmayer.ssofusion.repository

import com.robinmayer.ssofusion.model.db.ClientApplication
import org.springframework.data.jpa.repository.JpaRepository

interface ClientApplicationRepository: JpaRepository<ClientApplication, Long>