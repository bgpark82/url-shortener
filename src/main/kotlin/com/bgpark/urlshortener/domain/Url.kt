package com.bgpark.urlshortener.domain

import com.bgpark.urlshortener.exception.ApplicationException
import com.bgpark.urlshortener.exception.ErrorCode
import com.bgpark.urlshortener.exception.FieldError
import com.bgpark.urlshortener.utils.Constants.BASE_URL
import jakarta.persistence.*

@Entity
@Table(
    name = "url",
    indexes = [Index(name = "idx_hash", columnList = "hash")] // add index to hash column explicitly
)
class Url(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column(nullable = false)
    val longUrl: String,

    @Column(nullable = false, unique = true)
    val shortUrl: String,

    @Column(nullable = false, unique = true)
    val hash: String,

): BaseTimeEntity() {

    constructor(longUrl: String): this(
        id = 0L,
        longUrl = longUrl,
        shortUrl = "",
        hash = ""
    )

    constructor(longUrl: String, shortUrl: String, hash: String): this(
        id = 0L,
        longUrl = longUrl,
        shortUrl = shortUrl,
        hash = hash
    )
}