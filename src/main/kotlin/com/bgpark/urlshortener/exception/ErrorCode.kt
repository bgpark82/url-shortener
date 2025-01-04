package com.bgpark.urlshortener.exception

enum class ErrorCode(
    val code: String,
    val message: String,
    val status: Int
) {
    INVALID_INPUT_VALUE("CM_001", "Invalid Input Value", 400),
    NEGATIVE_VALUE_NOT_SUPPORTED("URL_001", "Negative values are not supported", 400),
    EMPTY_OR_WHITESPACE_INPUT("URL_002", "Input string is empty or contains only whitespace.", 400),
    INVALID_CHARACTERS_IN_INPUT("URL_003", "Input string contains invalid characters.", 400),
    URL_NOT_FOUND("URL_004", "URL not found", 404),
}