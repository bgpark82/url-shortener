package com.bgpark.urlshortener.dto

data class UrlShortenDto(
    val longUrl: String,
    val shortUrl: String,
    val hash: String
)