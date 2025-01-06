package com.bgpark.urlshortener.service

import com.bgpark.urlshortener.domain.Url
import com.bgpark.urlshortener.dto.UrlShortenDto
import com.bgpark.urlshortener.exception.ApplicationException
import com.bgpark.urlshortener.repository.db.UrlRepository
import com.bgpark.urlshortener.service.shortener.UrlShortener
import com.bgpark.urlshortener.utils.TestConstant.LONG_URL
import com.bgpark.urlshortener.utils.TestConstant.SHORT_URL
import org.assertj.core.api.Assertions.LONG
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UrlServiceTest {

    @Autowired
    private lateinit var urlRepository: UrlRepository

    @Autowired
    private lateinit var urlShortener: UrlShortener

    @Autowired
    private lateinit var urlService: UrlService

    @AfterEach
    fun tearDown() {
        urlRepository.deleteAll()
    }

    @Nested
    inner class SaveUrl {

        @Test
        fun `save URL`() {
            val longUrl = LONG_URL
            val hash = "123"
            val shortUrl = "http://localhost:8080/$hash"
            val userId = 1L

            val response = urlService.save(UrlShortenDto(
                longUrl = longUrl,
                shortUrl = shortUrl,
                userId = userId,
                hash = hash,
            ))

            assertThat(response.longUrl).isEqualTo(longUrl)
            assertThat(response.shortUrl).isEqualTo(shortUrl)
        }
    }

    @Nested
    inner class ResolveUrl {

        @Test
        fun `should resolve URL when URL exists`() {
            val response = urlService.save(UrlShortenDto(
                longUrl = LONG_URL,
                shortUrl = SHORT_URL,
                userId = 1L,
                hash = "hash",
            ))

            val result = urlService.resolve(extractHash(response))

            assertThat(result).isEqualTo(LONG_URL)
        }

        @Test
        fun `should throw exception when URL not found for hash`() {
            val unsavedHash = "123"

            assertThrows<ApplicationException> {
                urlService.resolve(unsavedHash)
            }
        }

        private fun extractHash(url: Url) = url.shortUrl.split("/").last()
    }
}