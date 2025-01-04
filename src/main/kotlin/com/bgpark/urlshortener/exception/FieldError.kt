package com.bgpark.urlshortener.exception

data class FieldError(
    val field: String,
    val value: Any?,
    val reason: String? = null
)