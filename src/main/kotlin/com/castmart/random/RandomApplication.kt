package com.castmart.random

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@SpringBootApplication
class RandomApplication

fun main(args: Array<String>) {
    runApplication<RandomApplication>(*args)
}

@Configuration
@EnableSqs
class AWSBeanClients {

    @Bean
    @Primary
    fun registerJacksonKotlinObjectMapper(): ObjectMapper {
        return jacksonObjectMapper()
    }

    @Bean
    @Qualifier("queue-name")
    fun queueName(): String = "random-numbers-queue"

    @Bean
    @Primary
    @Qualifier("mine")
    fun asyncSqsClient(): AmazonSQSAsync? {
        return AmazonSQSAsyncClientBuilder
            .standard()
            .withRegion("eu-central-1")
            .withCredentials(
                DefaultAWSCredentialsProviderChain()
            ).build()
    }

    @Bean
    fun queueTemplate(): QueueMessagingTemplate = QueueMessagingTemplate(asyncSqsClient())
}
