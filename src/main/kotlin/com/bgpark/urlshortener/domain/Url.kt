package com.bgpark.urlshortener.domain

import com.bgpark.urlshortener.exception.ApplicationException
import com.bgpark.urlshortener.exception.ErrorCode
import com.bgpark.urlshortener.exception.FieldError
import com.bgpark.urlshortener.utils.Constants.BASE_URL
import jakarta.persistence.*

@Entity
@Table(name = "url")
class Url(
    longUrl: String
): BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0L

    @Column(nullable = false)
    val longUrl: String = longUrl

    @Column(nullable = false, unique = true)
    var shortUrl: String = ""
        protected set

    /**
     * Generates a shortened URL by appending the given hash to the base URL.
     *
     * @param hash The unique identifier (hash) for the shortened URL.
     * @see BASE_URL The base URL to which the hash will be appended.
     */
    fun addShortUrl(baseUrl: String, hash: String) {
        validateUrl(baseUrl)
        validateHash(hash)

        val cleanedBaseUrl = baseUrl.trim().removeSuffix("/") // Remove trailing slash if exists
        this.shortUrl = "$cleanedBaseUrl/${hash.trim()}"
    }

    private fun validateUrl(baseUrl: String) {
        if (baseUrl.isBlank()) {
            throw ApplicationException(ErrorCode.EMPTY_OR_WHITESPACE_INPUT, FieldError(field = "baseUrl", value = baseUrl))
        }
    }

    private fun validateHash(hash: String) {
        if (hash.isBlank()) {
            throw ApplicationException(ErrorCode.EMPTY_OR_WHITESPACE_INPUT, FieldError(field = "hash", value = hash))
        }
    }
}