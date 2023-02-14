package com.castmart.random.queue

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.stereotype.Service

@Service
class RandomNumberQueueImpl(
    @Qualifier("queue-name") val queueName: String,
    val sqsClient: QueueMessagingTemplate
) : RandomNumberQueueWriter, RandomNumberQueueReader {

    override fun sendRandomNumberToQueue(randomNumber: Int) {
        sqsClient.convertAndSend(
            queueName,
            RNMessageDTO(
                randomNumber = randomNumber,
                sentOnInMillis = System.currentTimeMillis()
            )
        )
    }

    override fun readRandomNumberInQueue(): Int {
        TODO("Not yet implemented")
    }
}
