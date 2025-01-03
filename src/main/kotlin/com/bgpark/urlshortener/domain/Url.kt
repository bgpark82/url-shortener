package com.bgpark.urlshortener.domain

import jakarta.persistence.*

@Entity
@Table(name = "url")
class Url(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column(nullable = false)
    val longUrl: String,

    @Column(nullable = false, unique = true)
    val shortUrl: String,

): BaseTimeEntity() {
}