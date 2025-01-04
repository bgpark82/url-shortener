package com.bgpark.urlshortener.domain

import jakarta.persistence.*

@Entity
@Table(name = "url")
class Url(
    longUrl: String
): BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @Column(nullable = false)
    val longUrl: String = longUrl

    @Column(nullable = false, unique = true)
    var shortUrl: String = ""
        protected set

): BaseTimeEntity() {
}