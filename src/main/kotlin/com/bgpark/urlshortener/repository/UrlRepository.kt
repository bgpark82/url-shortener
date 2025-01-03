package com.bgpark.urlshortener.repository

import com.bgpark.urlshortener.domain.Url
import org.springframework.data.jpa.repository.JpaRepository

interface UrlRepository : JpaRepository<Url, Long> {
}