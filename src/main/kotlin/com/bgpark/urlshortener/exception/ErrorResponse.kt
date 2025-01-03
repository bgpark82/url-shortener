package com.bgpark.urlshortener.exception


data class ErrorResponse (
    private val errorCode: ErrorCode,
    val errors: List<FieldError>?
) {
    val message: String = errorCode.message
    val code: String = errorCode.code
    val status: Int = errorCode.status

    data class FieldError(
        val field: String,
        val value: Any?,
        val reason: String?
    )
}