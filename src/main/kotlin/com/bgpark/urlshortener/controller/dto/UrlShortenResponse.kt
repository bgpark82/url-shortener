package com.bgpark.urlshortener.controller.dto

data class UrlShortenResponse(
    val id: Long,
    val longUrl: String,
    val shortUrl: String
)