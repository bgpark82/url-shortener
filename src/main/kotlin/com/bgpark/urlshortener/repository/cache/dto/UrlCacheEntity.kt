package com.bgpark.urlshortener.repository.cache.dto

import com.bgpark.urlshortener.domain.Url
import java.time.LocalDateTime

data class UrlCacheEntity(
    val id: Long,
    val longUrl: String,
    val shortUrl: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
) {
    constructor(url: Url) : this(
        id = url.id,
        longUrl = url.longUrl,
        shortUrl = url.shortUrl,
        createdAt = url.createdAt,
        updatedAt = url.updatedAt,
    )
}