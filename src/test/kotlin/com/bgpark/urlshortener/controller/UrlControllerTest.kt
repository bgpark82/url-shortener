package com.bgpark.urlshortener.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UrlController::class)
class UrlControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun `shorten url`() {
        val longUrl = "https://example.com/long-url"
        val shortUrl = "http://localhost:8080/hash"

        mvc.perform(post("/api/v1/shorten")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content("""{ "longUrl": "$longUrl" }""".trimIndent()))
            .andExpect(status().isOk)
            .andExpect(content().json("""
                {
                   "id": 1,
                   "longUrl": "$longUrl",
                   "shortUrl": "$shortUrl"
                }
            """.trimIndent()))
    }
}