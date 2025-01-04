package com.bgpark.urlshortener.service.shortener

/**
 * Interface for generating and decoding hash values for short URL
 * - Encode a given ID into a hash string.
 * - Decode a given hash string back into the original ID.
 */
interface UrlShortener {

    /**
     * Encodes the given ID into a hash string.
     * @param id the ID to be encoded
     * @return the encoded hash string
     */
    fun encode(id: Long): String

    /**
     * Decodes the given hash string into the original ID.
     * @param hash the hash string to be decoded
     * @return the decoded ID
     */
    fun decode(hash: String): Long
}