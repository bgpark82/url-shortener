package com.bgpark.urlshortener.controller

import com.bgpark.urlshortener.controller.dto.UrlShortenRequest
import com.bgpark.urlshortener.controller.dto.UrlShortenResponse
import com.bgpark.urlshortener.service.UrlService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class UrlController(
    private val urlService: UrlService
) {

    @PostMapping("/shorten")
    @ResponseStatus(HttpStatus.CREATED)
    fun shorten(@Valid @RequestBody request: UrlShortenRequest): UrlShortenResponse {
        return urlService.shorten(request.longUrl)
    }
}