package com.castmart.random.service

import com.castmart.random.queue.RandomNumberQueueWriter
import org.springframework.stereotype.Service
import kotlin.math.floor

@Service
class DefaultRandomGeneratorService(val randomNumberQueueWriter: RandomNumberQueueWriter) : IRandomGeneratorService {

    override fun generateIntegerInRange(from: Int, to: Int): Int {
        val computed = (Math.random() * (to - from)) + from
        val response = floor(computed).toInt()
        randomNumberQueueWriter.sendRandomNumberToQueue(response)
        return response
    }
}
