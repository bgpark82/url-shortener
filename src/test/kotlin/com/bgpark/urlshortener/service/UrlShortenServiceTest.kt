package com.bgpark.urlshortener.service

import com.bgpark.urlshortener.exception.ApplicationException
import com.bgpark.urlshortener.repository.cache.UrlCacheRepository
import com.bgpark.urlshortener.service.shortener.UrlShortener
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UrlShortenServiceTest {

    @Mock
    private lateinit var urlShortener: UrlShortener

    @Mock
    private lateinit var urlCacheService: UrlCacheService

    @Mock
    private lateinit var urlCacheRepository: UrlCacheRepository

    @InjectMocks
    private lateinit var userShortenService: UrlShortenService

    @Test
    fun `should correctly concatenate baseUrl and hash to form a short URL`() {
        val baseUrl = "http://localhost:8080"
        val hash = "abc123"

        val shortUrl = userShortenService.createShortUrl(baseUrl, hash)

        assertThat(shortUrl).isEqualTo("http://localhost:8080/abc123")
    }

    @Test
    fun `should throw exception for empty baseUrl`() {
        org.junit.jupiter.api.assertThrows<ApplicationException> {
            userShortenService.createShortUrl("", "abc123")
        }
    }

    @Test
    fun `should throw exception for empty hash`() {
        org.junit.jupiter.api.assertThrows<ApplicationException> {
            userShortenService.createShortUrl("http://localhost:8080", "")
        }
    }

    @Test
    fun `should handle hash with special characters correctly`() {
        val baseUrl = "http://localhost:8080"
        val hash = "abc!@#123"

        val shortUrl = userShortenService.createShortUrl(baseUrl, hash)

        assertThat(shortUrl).isEqualTo("http://localhost:8080/abc!@#123")
    }

    @Test
    fun `should handle baseUrl with trailing slash correctly`() {
        val baseUrl = "http://localhost:8080/"
        val hash = "qwerty123"

        val shortUrl = userShortenService.createShortUrl(baseUrl, hash)

        assertThat(shortUrl).isEqualTo("http://localhost:8080/qwerty123")
    }

    @Test
    fun `should handle baseUrl and hash with spaces correctly`() {
        val baseUrl = "  http://localhost:8080  "
        val hash = "  abc123  "

        val shortUrl = userShortenService.createShortUrl(baseUrl, hash)

        assertThat(shortUrl).isEqualTo("http://localhost:8080/abc123")
    }
}