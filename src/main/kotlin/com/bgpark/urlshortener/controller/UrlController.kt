package com.bgpark.urlshortener.controller

import com.bgpark.urlshortener.controller.dto.UrlShortenRequest
import com.bgpark.urlshortener.service.UrlCacheService
import com.bgpark.urlshortener.service.UrlShortenService
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UrlController(
    private val urlShortenService: UrlShortenService,
    private val urlCacheService: UrlCacheService
) {

    @PostMapping("/api/v1/shorten")
    @ResponseStatus(HttpStatus.CREATED)
    fun shorten(@Valid @RequestBody request: UrlShortenRequest) {
        urlShortenService.shortenUrl(request.longUrl)
    }

    @GetMapping("/{hash}")
    fun resolve(@PathVariable("hash") hash: String): ResponseEntity<Void> {
        val url = urlCacheService.resolveUrlLocalCache(hash)
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
            .header(HttpHeaders.LOCATION, url)
            .build()
    }
}