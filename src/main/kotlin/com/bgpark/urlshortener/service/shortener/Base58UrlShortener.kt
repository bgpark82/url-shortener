package com.bgpark.urlshortener.service.shortener

import com.bgpark.urlshortener.exception.ApplicationException
import com.bgpark.urlshortener.exception.ErrorCode
import com.bgpark.urlshortener.exception.FieldError
import com.bgpark.urlshortener.utils.Constants.BASE58_ALPHABET
import com.bgpark.urlshortener.utils.Constants.BASE58_SIZE
import org.springframework.stereotype.Component

/**
 * A class that implements the Base58 algorithm to encode a Long value into a Base58 formatted string.
 * This class can be useful for shortening URLs or representing unique IDs in a shorter format.
 */
@Component
class Base58UrlShortener: UrlShortener {

    /**
     * Encodes the given Long value into a Base58 encoded string.
     *
     * @param id The Long value to be encoded.
     * @return A Base58 encoded string representing the given Long value.
     * @throws IllegalArgumentException If the given id is negative.
     * @see BASE58_ALPHABET
     */
    override fun encode(id: Long): String {
        validateId(id)

        if (id == 0L) return BASE58_ALPHABET[0].toString() // If the id is 0, return the first character '1'

        val builder = StringBuilder()
        var number = id

        // Convert the number to Base58 format
        while (number > 0) {
            val index = (number % BASE58_SIZE).toInt()
            builder.insert(0, BASE58_ALPHABET[index])
            number /= BASE58_SIZE
        }

        return builder.toString()
    }

    /**
     * Decodes a Base58 encoded string to its corresponding long value.
     *
     * @param hash The Base58 encoded string to be decoded.
     * @return The decoded long value.
     * @throws IllegalArgumentException If the provided string is invalid, such as
     *         containing unsupported characters, being empty, or violating Base58 format.
     */
    override fun decode(hash: String): Long {
        validateHash(hash)

        // accumulate the Base58 index of each character into a Long value
        return hash.fold(0L) { number, char ->
            number * BASE58_SIZE + BASE58_ALPHABET.indexOf(char)
        }
    }

    private fun validateId(id: Long) {
        if (id < 0) {
            throw ApplicationException(ErrorCode.NEGATIVE_VALUE_NOT_SUPPORTED, FieldError(field = "id", value = id))
        }
    }

    private fun validateHash(hash: String) {
        if (hash.isBlank()) {
            throw ApplicationException(ErrorCode.EMPTY_OR_WHITESPACE_INPUT, FieldError(field = "hash", value = hash))
        }

        if (containsInvalidCharacters(hash)) {
            throw ApplicationException(ErrorCode.INVALID_CHARACTERS_IN_INPUT, FieldError(field = "hash", value = hash))
        }
    }

    private fun containsInvalidCharacters(hash: String): Boolean {
        return hash.any { BASE58_ALPHABET.indexOf(it) == -1 }
    }
}