package com.bgpark.urlshortener.service

import com.bgpark.urlshortener.utils.UrlRedisUtils.URL_HASH_CACHE_NAME
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service

@Service
class UrlCacheService(
    private val urlService: UrlService,
) {

    @CachePut(cacheNames = [URL_HASH_CACHE_NAME], key = "#hash")
    fun shortenUrl(hash: String, longUrl: String, shortUrl: String): String {
        val url = urlService.save(longUrl, shortUrl)
        return url.longUrl
    }
}