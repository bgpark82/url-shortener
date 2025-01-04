package com.bgpark.urlshortener.repository

import com.bgpark.urlshortener.domain.Url
import com.bgpark.urlshortener.utils.TestConstant.LONG_URL
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class UrlRepositoryTest {

    @Autowired
    private lateinit var urlRepository: UrlRepository

    @Test
    fun create() {
        val savedUrl = urlRepository.save(createUrl())

        assertThat(savedUrl.longUrl).isEqualTo(LONG_URL)
        assertThat(savedUrl.shortUrl).isEqualTo("")
        assertThat(savedUrl.createdAt).isNotNull()
        assertThat(savedUrl.updatedAt).isNotNull()
    }

    private fun createUrl(): Url = Url(
            longUrl = LONG_URL,
        )
}