package com.bgpark.urlshortener.domain

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

    val userId: Long? = null

): BaseTimeEntity() {

    constructor(longUrl: String, shortUrl: String, hash: String, userId: Long): this(
        id = 0L,
        longUrl = longUrl,
        shortUrl = shortUrl,
        hash = hash,
        userId = userId,
    )
}