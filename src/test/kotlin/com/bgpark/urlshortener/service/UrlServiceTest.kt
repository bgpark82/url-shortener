package com.bgpark.urlshortener.service

import com.bgpark.urlshortener.repository.UrlRepository
import com.bgpark.urlshortener.service.shortener.UrlShortener
import com.bgpark.urlshortener.utils.TestConstant.LONG_URL
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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


    @Test
    fun `shorten URL`() {
        val longUrl = LONG_URL

        val response = urlService.shorten(longUrl)

        val hash = urlShortener.encode(response.id)
        assertThat(response.id).isEqualTo(1L)
        assertThat(response.longUrl).isEqualTo(longUrl)
        assertThat(response.shortUrl).isEqualTo("http://localhost:8080/${hash}")
    }
}