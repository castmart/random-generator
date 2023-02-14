package com.castmart.random

import com.castmart.random.service.IRandomGeneratorService
import org.mockito.Mockito
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MockBeans {

    @Bean
    fun randomGenerator(): IRandomGeneratorService = Mockito.mock(IRandomGeneratorService::class.java)
}
