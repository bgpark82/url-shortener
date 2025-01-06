package com.bgpark.urlshortener.controller.dto

import com.bgpark.urlshortener.domain.Url
import java.time.LocalDateTime

data class UrlResponse(
    val id: Long,
    val longUrl: String,
    val shortUrl: String,
    val hash: String,
    val userId: Long?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    constructor(url: Url): this(
        id = url.id,
        longUrl = url.longUrl,
        shortUrl = url.shortUrl,
        hash = url.hash,
        userId = url.userId,
        createdAt = url.createdAt!!,
        updatedAt = url.updatedAt!!,
    )

}