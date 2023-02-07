package com.castmart.random.service

import org.springframework.stereotype.Service
import kotlin.math.floor

@Service
class DefaultRandomGeneratorService: IRandomGeneratorService {

    override fun generateIntegerInRange(from: Int, to: Int): Int {
        val response = (Math.random() * (to - from)) + from
        return floor(response).toInt()
    }
}