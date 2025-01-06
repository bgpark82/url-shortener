package com.bgpark.urlshortener.controller.dto

import jakarta.validation.Validation
import jakarta.validation.Validator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test

class UrlShortenRequestTest {

    private lateinit var validator: Validator

    @BeforeEach
    fun setUp() {
        validator = Validation.buildDefaultValidatorFactory().validator
    }

    @Test
    fun `should fail validation when URL is blank`() {
        val request = UrlShortenRequest(longUrl = "", userId = 1L)

        val violations = validator.validate(request)

        assertThat(violations).hasSize(1)
        val violation = violations.first()
        assertThat(violation.propertyPath.toString()).isEqualTo("longUrl")
        assertThat(violation.message).isEqualTo("URL cannot be blank")
    }

    @ParameterizedTest(name = "Valid URL test case: {0}")
    @CsvSource(
        "https://example.com",
        "http://subdomain.example.com",
        "https://example.com:8080",
        "https://example.com?query=param",
        "https://example.com#section",
        "ftp://ftp.example.com",
        "https://xn--bcher-kva.example"
    )
    fun `should pass validation for valid URLs`(url: String) {
        val request = UrlShortenRequest(longUrl = url, userId = 1L)

        val violations = validator.validate(request)

        assertThat(violations).isEmpty()
    }

    @ParameterizedTest(name = "Invalid URL test case: {0}")
    @CsvSource(
        "example.com",
        "www.example.com",
        "htp://example.com",
        "http://example .com",
        "http://example.com /path",
    )
    fun `should fail validation for invalid URLs`(url: String) {
        val request = UrlShortenRequest(longUrl = url, userId = 1L)

        val violations = validator.validate(request)

        assertThat(violations).hasSize(1)
        val violation = violations.first()
        assertThat(violation.propertyPath.toString()).isEqualTo("longUrl")
        assertThat(violation.message).isEqualTo("Invalid URL format")
    }
}