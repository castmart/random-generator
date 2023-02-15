package com.castmart.random.queue

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Service

@Service
class RandomNumberQueueImpl(
    @Qualifier("queue-name") val queueName: String,
    val sqsClient: QueueMessagingTemplate
) : RandomNumberQueueWriter, RandomNumberQueueReader {

    private val logger = LoggerFactory.getLogger(RandomNumberQueueImpl::class.java)

    private val objectMapper = jacksonObjectMapper()

    override fun sendRandomNumberToQueue(randomNumber: Int) {
        sqsClient.convertAndSend(
            queueName,
            RNMessageDTO(
                randomNumber = randomNumber,
                sentOnInMillis = System.currentTimeMillis()
            )
        )
    }

    @SqsListener(
        value = ["random-numbers-queue"],
        deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS
    )
    override fun readRandomNumberInQueue(messagePayload: String) {
        val randomNumberDTO = objectMapper.readValue(messagePayload, RNMessageDTO::class.java)
        logger.info("Number in queue: ${randomNumberDTO.randomNumber}, sent on ${randomNumberDTO.sentOnInMillis}")
    }
}
