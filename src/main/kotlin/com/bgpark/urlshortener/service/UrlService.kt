package com.bgpark.urlshortener.service

import com.bgpark.urlshortener.controller.dto.UrlShortenResponse

class UrlService {

    fun shorten(longUrl: String): UrlShortenResponse {
        return UrlShortenResponse(id = 1L, longUrl = longUrl, shortUrl = "http://localhost:8080/hash")
    }
}