package com.bgpark.urlshortener.utils

object UrlRedisUtils {

    fun getUrlShortenHashKey(hash: String) = "url.shorten.hash=$hash"
}