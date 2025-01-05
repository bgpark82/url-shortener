package com.bgpark.urlshortener.service

import com.bgpark.urlshortener.domain.Url
import com.bgpark.urlshortener.exception.ApplicationException
import com.bgpark.urlshortener.exception.ErrorCode
import com.bgpark.urlshortener.exception.FieldError
import com.bgpark.urlshortener.repository.UrlRepository
import com.bgpark.urlshortener.service.shortener.UrlShortener
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UrlService(
    @Qualifier("base58UrlShortener") private val urlShortener: UrlShortener,
    private val urlRepository: UrlRepository
) {

    @Transactional
    fun save(longUrl: String, shortUrl: String): Url {
        val url = Url(longUrl = longUrl, shortUrl = shortUrl)
        return urlRepository.save(url)
    }

    @Transactional(readOnly = true)
    fun resolve(hash: String): String {
        val id = urlShortener.decode(hash)
        val url = urlRepository.findByIdOrNull(id)
        return url?.longUrl ?: throw ApplicationException(ErrorCode.URL_NOT_FOUND, FieldError(field = "id", value = id))
    }
}