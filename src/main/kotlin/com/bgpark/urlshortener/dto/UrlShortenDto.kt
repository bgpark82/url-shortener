package com.bgpark.urlshortener.dto

data class UrlShortenDto(
    val userId: Long,
    val longUrl: String,
    val shortUrl: String,
    val hash: String
)