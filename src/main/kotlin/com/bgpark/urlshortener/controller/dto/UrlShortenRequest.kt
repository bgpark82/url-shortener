package com.bgpark.urlshortener.controller.dto

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.URL

data class UrlShortenRequest(

    @field:NotBlank(message = "URL cannot be blank")
    @field:URL(message = "Invalid URL format")
    val longUrl: String,

    val userId: Long
)

