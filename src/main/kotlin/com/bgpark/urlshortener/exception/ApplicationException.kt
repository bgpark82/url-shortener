package com.bgpark.urlshortener.exception

class ApplicationException(
    val errorCode: ErrorCode,
    val fieldError: FieldError? = null
): RuntimeException(errorCode.message) {
}