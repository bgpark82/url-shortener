package com.bgpark.urlshortener.repository.cache

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class UrlCacheRepository(
    private val redisTemplate: RedisTemplate<String, String>
) {

    fun save(key: String, value: String) {
        redisTemplate.opsForValue().set(key, value)
    }

    fun findByKey(key: String): String? {
        return redisTemplate.opsForValue().get(key)
    }
}