package com.bgpark.urlshortener.controller

import com.bgpark.urlshortener.service.UrlCacheService
import com.bgpark.urlshortener.service.UrlShortenService
import com.bgpark.urlshortener.utils.TestConstant.LONG_URL
import com.bgpark.urlshortener.utils.TestConstant.SHORT_URL
import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(UrlController::class)
class UrlControllerTest {

    @MockkBean
    private lateinit var urlShortenService: UrlShortenService

    @MockkBean
    private lateinit var urlCacheService: UrlCacheService

    @Autowired
    private lateinit var mvc: MockMvc

    @Nested
    inner class ShortenUrl {

        @Test
        fun `shorten url`() {
            val longUrl = LONG_URL
            val shortUrl = SHORT_URL
            every { urlShortenService.shortenUrl(longUrl) } just Runs

            mvc.perform(
                post("/api/v1/shorten")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content("""{ "longUrl": "$longUrl" }""".trimIndent()))
                .andExpect(status().isCreated)
        }

        @Test
        fun `should fail validation for invalid URLs`() {
            val longUrl = "htp://example.com"

            mvc.perform(
                post("/api/v1/shorten")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content("""{ "longUrl": "$longUrl" }""".trimIndent()))
                .andExpect(status().isBadRequest)
                .andExpect(
                    content().json(
                        """
                {
                  "errors": [
                    {
                      "field": "longUrl",
                      "value": "$longUrl",
                      "reason": "Invalid URL format"
                    }
                  ],
                  "message": "Invalid Input Value",
                  "code": "CM_001",
                  "status": 400
                }
            """.trimIndent()
                    )
                )
        }

        @Test
        fun `should fail validation when URL is blank`() {
            val longUrl = ""

            mvc.perform(
                post("/api/v1/shorten")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content("""{ "longUrl": "$longUrl" }""".trimIndent()))
                .andExpect(status().isBadRequest)
                .andExpect(
                    content().json(
                        """
                {
                  "errors": [
                    {
                      "field": "longUrl",
                      "value": "$longUrl",
                      "reason": "URL cannot be blank"
                    }
                  ],
                  "message": "Invalid Input Value",
                  "code": "CM_001",
                  "status": 400
                }
            """.trimIndent()
                    )
                )
        }
    }

    @Nested
    inner class ResolveUrl {

        @Test
        fun `resolve url`() {
            val hash = "123"
            every { urlCacheService.resolveUrlLocalCache(hash) } returns LONG_URL

            mvc.perform(get("/$hash"))
                .andExpect(status().isMovedPermanently)
                .andExpect(header().string("Location", LONG_URL))
        }
    }
}