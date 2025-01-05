package com.bgpark.urlshortener.repository.cache

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class UrlCacheRepository(
    private val redisTemplate: RedisTemplate<String, String>
) {

    fun findByKey(key: String): String? {
        return redisTemplate.opsForValue().get(key)
    }

    fun increment(counterKey: String): Long? {
        return redisTemplate.opsForValue().increment(counterKey)
    }
}