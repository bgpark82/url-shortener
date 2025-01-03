package com.bgpark.urlshortener.exception

enum class ErrorCode(
    val code: String,
    val message: String,
    val status: Int
) {
    INVALID_INPUT_VALUE("CM_001", "Invalid Input Value", 400)
}