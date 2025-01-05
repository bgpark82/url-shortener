package com.bgpark.urlshortener.service

import com.bgpark.urlshortener.utils.UrlRedisUtils.URL_HASH_CACHE_NAME
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class UrlCacheService(
    private val urlService: UrlService,
) {
    private val log: Logger = LoggerFactory.getLogger(UrlCacheService::class.java)

    @CachePut(cacheNames = [URL_HASH_CACHE_NAME], key = "#hash")
    fun shortenUrl(hash: String, longUrl: String, shortUrl: String): String {
        val url = urlService.save(longUrl, shortUrl, hash)
        return url.longUrl
    }

    @Cacheable(cacheNames = [URL_HASH_CACHE_NAME], key="#hash")
    fun resolveUrl(hash: String): String {
        log.info("resolving url for hash: $hash")
        return urlService.resolve(hash)
    }
}