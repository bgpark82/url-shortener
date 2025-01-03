package com.bgpark.urlshortener.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UrlServiceTest {

    private val urlService = UrlService()

    @Test
    fun `shorten URL`() {
        val longUrl = "https://example.com/longUrl"

        val response = urlService.shorten(longUrl)

        assertThat(response.id).isEqualTo(1)
        assertThat(response.longUrl).isEqualTo(longUrl)
        assertThat(response.shortUrl).isEqualTo("http://localhost:8080/hash")
    }
}