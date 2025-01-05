package com.bgpark.urlshortener.service

import com.bgpark.urlshortener.domain.Url
import com.bgpark.urlshortener.exception.ApplicationException
import com.bgpark.urlshortener.exception.ErrorCode
import com.bgpark.urlshortener.exception.FieldError
import com.bgpark.urlshortener.repository.UrlRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UrlService(
    private val urlRepository: UrlRepository
) {

    @Transactional
    fun save(longUrl: String, shortUrl: String, hash: String): Url {
        val url = Url(longUrl = longUrl, shortUrl = shortUrl, hash = hash)
        return urlRepository.save(url)
    }

    @Transactional(readOnly = true)
    fun resolve(hash: String): String {
        val url = urlRepository.findByHash(hash)
        return url?.longUrl ?: throw ApplicationException(ErrorCode.URL_NOT_FOUND, FieldError(field = "hash", value = hash))
    }
}