package com.bgpark.urlshortener.service

import com.bgpark.urlshortener.domain.Url
import com.bgpark.urlshortener.repository.cache.UrlCacheRepository
import com.bgpark.urlshortener.utils.TestConstant.LONG_URL
import com.bgpark.urlshortener.utils.UrlRedisUtils.getUrlShortenHashKey
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class UrlCacheServiceTest {

    @MockkBean
    private lateinit var urlService: UrlService

    @Autowired
    private lateinit var urlCacheRepository: UrlCacheRepository

    @Autowired
    private lateinit var urlCacheService: UrlCacheService

    @Test
    fun `should shorten valid URL and save to cache`() {
        val longUrl = LONG_URL
        val shortUrl = "http://localhost:8080/abc123"
        val key = getUrlShortenHashKey("abc123")

        every { urlService.save(longUrl, shortUrl) } returns Url(id = 1L, longUrl = longUrl, shortUrl = shortUrl)

        val result = urlCacheService.shortenUrl("abc123", longUrl, shortUrl)

        assertThat(result).isEqualTo(longUrl)
        assertThat(urlCacheRepository.findByKey(key)).isEqualTo(LONG_URL)
    }
}