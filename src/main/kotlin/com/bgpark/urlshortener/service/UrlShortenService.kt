package com.bgpark.urlshortener.service

import com.bgpark.urlshortener.exception.ApplicationException
import com.bgpark.urlshortener.exception.ErrorCode
import com.bgpark.urlshortener.exception.FieldError
import com.bgpark.urlshortener.repository.cache.UrlCacheRepository
import com.bgpark.urlshortener.service.shortener.UrlShortener
import com.bgpark.urlshortener.utils.Constants.BASE_URL
import com.bgpark.urlshortener.utils.UrlRedisUtils.getCounterKey
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class UrlShortenService(
    @Qualifier("base58UrlShortener") private val urlShortener: UrlShortener,
    private val urlCacheService: UrlCacheService,
    private val urlCacheRepository: UrlCacheRepository
) {

    fun shortenUrl(longUrl: String) {
        val id = urlCacheRepository.increment(getCounterKey()) ?: throw ApplicationException(ErrorCode.URL_NOT_FOUND)
        val hash = urlShortener.encode(id)
        val shortUrl = createShortUrl(BASE_URL, hash)
        urlCacheService.shortenUrl(hash, longUrl, shortUrl)
    }

    fun createShortUrl(baseUrl: String, hash: String): String {
        validateUrl(baseUrl)
        validateHash(hash)

        val cleanedBaseUrl = baseUrl.trim().removeSuffix("/") // Remove trailing slash if exists
        return "$cleanedBaseUrl/${hash.trim()}"
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