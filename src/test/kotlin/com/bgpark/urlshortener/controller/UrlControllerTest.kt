package com.bgpark.urlshortener.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
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

        shortenUrl(
            status = status().isOk,
            request = """{ "longUrl": "$longUrl" }""",
            response = """
                {
                   "id": 1,
                   "longUrl": "$longUrl",
                   "shortUrl": "$shortUrl"
                }
            """,
        )
    }

    @Test
    fun `should fail validation for invalid URLs`() {
        val longUrl = "htp://example.com"

        shortenUrl(
            status = status().isBadRequest,
            request = """{ "longUrl": "$longUrl" }""",
            response = """
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
            """,
        )
    }

    @Test
    fun `should fail validation when URL is blank`() {
        val longUrl = ""

        shortenUrl(
            status = status().isBadRequest,
            request = """{ "longUrl": "$longUrl" }""",
            response = """
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
            """,
        )
    }

    private fun shortenUrl(request: String, response: String, status: ResultMatcher) {
        mvc.perform(
            post("/api/v1/shorten")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request.trimIndent())
        )
            .andExpect(status)
            .andExpect(
                content().json(
                    response.trimIndent()
                )
            )
    }
}