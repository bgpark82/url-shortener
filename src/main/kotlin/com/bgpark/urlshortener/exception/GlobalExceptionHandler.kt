package com.bgpark.urlshortener.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(e: MethodArgumentNotValidException): ErrorResponse {
        log.error(e.message)
        return ErrorResponse(
            errorCode = ErrorCode.INVALID_INPUT_VALUE,
            errors = e.bindingResult.fieldErrors.map {
                ErrorResponse.FieldError(
                    reason = it.defaultMessage,
                    field = it.field,
                    value = it.rejectedValue
                )
            },
        )
    }
}