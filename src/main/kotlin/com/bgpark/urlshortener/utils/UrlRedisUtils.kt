package com.bgpark.urlshortener.utils

object UrlRedisUtils {

    fun getUrlShortenHashKey(hash: String) = "url.shorten.hash=$hash"

    fun getCounterKey(): String = "url.counter"

    const val URL_HASH_CACHE_NAME = "url.hash"
}