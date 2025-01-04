package com.bgpark.urlshortener.service

import com.bgpark.urlshortener.service.shortener.Base58UrlShortener
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class Base58UrlShortenerTest {

    private val shortener = Base58UrlShortener()

    @Nested
    inner class Encode {

        @Test
        fun `encode should return the correct Base58 representation for given ID`() {
            val resultForZero = shortener.encode(0L)
            val resultForOne = shortener.encode(1L)
            val resultForMax = shortener.encode(Long.MAX_VALUE)

            assertThat(resultForZero).isEqualTo("1")
            assertThat(resultForOne).isEqualTo("2")
            assertThat(resultForMax).isEqualTo("NQm6nKp8qFC")
        }

        @Test
        fun `encode should handle negative numbers correctly`() {
            assertThrows<IllegalArgumentException> {
                shortener.encode(-1L)
            }
        }

        @Test
        fun `encode should produce unique results for different inputs`() {
            val resultForIdOne = shortener.encode(123L)
            val resultForIdTwo = shortener.encode(456L)

            assertThat(resultForIdOne).isNotEqualTo(resultForIdTwo)
        }
    }

    @Nested
    inner class Decode {

        @Test
        fun `should decode valid base58 string correctly`() {
            val encoded = shortener.encode(123456789L)
            val decoded = shortener.decode(encoded)

            assertThat(decoded).isEqualTo(123456789L)
        }

        @Test
        fun `should decode MAX_VALUE correctly`() {
            val encoded = shortener.encode(Long.MAX_VALUE)
            val decoded = shortener.decode(encoded)

            assertThat(decoded).isEqualTo(Long.MAX_VALUE)
        }

        @ParameterizedTest
        @ValueSource(strings = ["-12345", "", " ", "invalid_chars!"])
        fun `should throw exception for invalid input`(invalidInput: String) {
            assertThrows<IllegalArgumentException> {
                shortener.decode(invalidInput)
            }
        }
    }
}