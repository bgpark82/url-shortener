package com.bgpark.urlshortener.service

import com.bgpark.urlshortener.dto.UrlShortenDto
import com.bgpark.urlshortener.utils.UrlRedisUtils.URL_HASH_CACHE_NAME
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.framework.AopContext
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class UrlCacheService(
    private val urlService: UrlService,
) {

    private val log: Logger = LoggerFactory.getLogger(UrlCacheService::class.java)

    @CachePut(cacheNames = [URL_HASH_CACHE_NAME], key = "#hash", cacheManager = "localCacheManager")
    fun shortenUrlLocalCache(url: UrlShortenDto): String {
        log.info("shorten url for hash in local cache, hash : ${url.hash}")
        return proxy().shortenUrlCache(url)
    }

    @CachePut(cacheNames = [URL_HASH_CACHE_NAME], key = "#hash")
    fun shortenUrlCache(url: UrlShortenDto): String {
        log.info("shorten url for hash in cache, hash : ${url.hash}")
        val savedUrl = urlService.save(url.longUrl, url.shortUrl, url.hash)
        return savedUrl.longUrl
    }

    @Cacheable(cacheNames = [URL_HASH_CACHE_NAME], key="#hash", cacheManager = "localCacheManager")
    fun resolveUrlLocalCache(hash: String): String {
        log.info("resolving url for hash in local cache, hash: $hash")
        return proxy().resolveUrlCache(hash)
    }

    @Cacheable(cacheNames = [URL_HASH_CACHE_NAME], key="#hash")
    fun resolveUrlCache(hash: String): String {
        log.info("resolving url for hash in cache, hash: $hash")
        return urlService.resolve(hash)
    }

    private fun proxy(): UrlCacheService {
        return AopContext.currentProxy() as UrlCacheService
    }
}