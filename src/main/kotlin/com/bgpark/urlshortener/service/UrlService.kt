package com.bgpark.urlshortener.service

import com.bgpark.urlshortener.controller.dto.UrlShortenResponse
import com.bgpark.urlshortener.domain.Url
import com.bgpark.urlshortener.repository.UrlRepository
import com.bgpark.urlshortener.service.shortener.UrlShortener
import com.bgpark.urlshortener.utils.Constants
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UrlService(
    @Qualifier("base58UrlShortener") private val urlShortener: UrlShortener,
    private val urlRepository: UrlRepository
) {

    @Transactional
    fun shorten(longUrl: String): UrlShortenResponse {
        val url = urlRepository.save(Url(longUrl = longUrl))
        val hash = urlShortener.encode(url.id)

        url.addShortUrl(Constants.BASE_URL, hash) // update short url

        return UrlShortenResponse.toDto(url)
    }
}