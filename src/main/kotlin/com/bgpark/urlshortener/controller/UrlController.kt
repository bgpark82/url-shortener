package com.bgpark.urlshortener.controller

import com.bgpark.urlshortener.controller.dto.UrlShortenRequest
import com.bgpark.urlshortener.controller.dto.UrlShortenResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UrlController {

    @PostMapping("/shorten")
    fun shorten(@RequestBody request: UrlShortenRequest): ResponseEntity<Any> {
        return ResponseEntity.ok(UrlShortenResponse(id = 1L, longUrl = request.longUrl, shortUrl = "http://localhost:8080/hash"))
    }
}