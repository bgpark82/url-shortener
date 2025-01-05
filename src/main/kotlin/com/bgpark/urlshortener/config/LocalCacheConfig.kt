package com.bgpark.urlshortener.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class LocalCacheConfig {

    @Bean
    fun localCacheManager(): CacheManager {
        val caffeineCacheManager = CaffeineCacheManager()
        caffeineCacheManager.setCaffeine(
            Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofHours(1)) // expire
                .maximumSize(1000) // 1000 item maximum
        )
        return caffeineCacheManager
    }
}