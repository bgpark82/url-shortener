package com.bgpark.urlshortener.domain

import com.bgpark.urlshortener.exception.ApplicationException
import com.bgpark.urlshortener.exception.ErrorCode
import com.bgpark.urlshortener.exception.FieldError
import com.bgpark.urlshortener.utils.Constants.BASE_URL
import jakarta.persistence.*

@Entity
@Table(name = "url")
class Url(
    id: Long,
    longUrl: String,
    shortUrl: String,
): BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = id

    @Column(nullable = false)
    val longUrl: String = longUrl

    @Column(nullable = false, unique = true)
    var shortUrl: String = shortUrl
        protected set

    constructor(longUrl: String): this(
        id = 0L,
        longUrl = longUrl,
        shortUrl = ""
    )

    constructor(longUrl: String, shortUrl: String): this(
        id = 0L,
        longUrl = longUrl,
        shortUrl = shortUrl
    )
}