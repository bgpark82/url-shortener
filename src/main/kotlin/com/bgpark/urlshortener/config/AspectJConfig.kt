package com.bgpark.urlshortener.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
class AspectJConfig {
}