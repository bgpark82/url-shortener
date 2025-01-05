package com.bgpark.urlshortener.service

import com.bgpark.urlshortener.repository.cache.UrlCacheRepository
import com.bgpark.urlshortener.repository.cache.dto.UrlCacheEntity
import com.bgpark.urlshortener.utils.UrlRedisUtils.getUrlShortenHashKey
import org.springframework.stereotype.Service

@Service
class UrlCacheService(
    private val urlService: UrlService,
    private val urlCacheRepository: UrlCacheRepository
) {

    fun shortenUrl(longUrl: String): UrlCacheEntity {
        val url = urlService.shortenUrl(longUrl)
        val hash = url.shortUrl.split("/").last()
        val key = getUrlShortenHashKey(hash)

        urlCacheRepository.save(key, longUrl)

        return UrlCacheEntity(url)
    }
}