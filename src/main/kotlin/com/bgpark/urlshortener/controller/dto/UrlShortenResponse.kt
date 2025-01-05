package com.bgpark.urlshortener.controller.dto

import com.bgpark.urlshortener.domain.Url
import com.bgpark.urlshortener.repository.cache.dto.UrlCacheEntity
import java.time.LocalDateTime

data class UrlShortenResponse(
    val id: Long,
    val longUrl: String,
    val shortUrl: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
) {
    constructor(entity: UrlCacheEntity): this(
        id = entity.id,
        longUrl = entity.longUrl,
        shortUrl = entity.shortUrl,
        createdAt = entity.createdAt,
        updatedAt = entity.updatedAt
    )

    companion object  {
        fun toDto(url: Url): UrlShortenResponse {
            return UrlShortenResponse(
                id = url.id,
                longUrl = url.longUrl,
                shortUrl = url.shortUrl,
                createdAt = url.createdAt,
                updatedAt = url.updatedAt
            )
        }
    }
}