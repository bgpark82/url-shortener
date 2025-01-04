package com.bgpark.urlshortener.domain

import com.bgpark.urlshortener.utils.TestConstant.LONG_URL
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UrlTest {

    private val url = Url(LONG_URL)

    @Test
    fun `should correctly concatenate baseUrl and hash to form a short URL`() {
        val baseUrl = "http://localhost:8080"
        val hash = "abc123"

        url.addShortUrl(baseUrl, hash)

        assertThat(url.shortUrl).isEqualTo("http://localhost:8080/abc123")
    }

    @Test
    fun `should throw exception for empty baseUrl`() {
        assertThrows<IllegalArgumentException> {
            url.addShortUrl("", "abc123")
        }
    }

    @Test
    fun `should throw exception for empty hash`() {
        assertThrows<IllegalArgumentException> {
            url.addShortUrl("http://localhost:8080", "")
        }
    }

    @Test
    fun `should handle hash with special characters correctly`() {
        val baseUrl = "http://localhost:8080"
        val hash = "abc!@#123"

        url.addShortUrl(baseUrl, hash)

        assertThat(url.shortUrl).isEqualTo("http://localhost:8080/abc!@#123")
    }

    @Test
    fun `should handle baseUrl with trailing slash correctly`() {
        val baseUrl = "http://localhost:8080/"
        val hash = "qwerty123"

        url.addShortUrl(baseUrl, hash)

        assertThat(url.shortUrl).isEqualTo("http://localhost:8080/qwerty123")
    }

    @Test
    fun `should handle baseUrl and hash with spaces correctly`() {
        val baseUrl = "  http://localhost:8080  "
        val hash = "  abc123  "

        url.addShortUrl(baseUrl, hash)

        assertThat(url.shortUrl).isEqualTo("http://localhost:8080/abc123")
    }
}