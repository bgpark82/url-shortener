package com.bgpark.urlshortener.controller

import com.bgpark.urlshortener.controller.dto.UrlResponse
import com.bgpark.urlshortener.repository.db.UrlRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val urlRepository: UrlRepository
) {

    @GetMapping("/api/v1/users/{userId}/urls")
    @ResponseStatus(HttpStatus.OK)
    fun getUrlByUserId(@PathVariable("userId") userId: Long): List<UrlResponse> {
        return urlRepository.findAllByUserId(userId).map(::UrlResponse)
    }
}