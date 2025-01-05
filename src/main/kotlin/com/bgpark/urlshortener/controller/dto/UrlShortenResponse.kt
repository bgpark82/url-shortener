package com.bgpark.urlshortener.controller.dto

import com.bgpark.urlshortener.domain.Url
import java.time.LocalDateTime

data class UrlShortenResponse(
    val id: Long,
    val longUrl: String,
    val shortUrl: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
) {

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