package com.bgpark.urlshortener.repository.db

import com.bgpark.urlshortener.domain.Url
import org.springframework.data.jpa.repository.JpaRepository

interface UrlRepository : JpaRepository<Url, Long> {

    fun findByHash(hash: String): Url?

    fun findAllByUserId(userId: Long): List<Url>
}