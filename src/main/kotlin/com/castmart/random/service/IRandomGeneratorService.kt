package com.castmart.random.service

interface IRandomGeneratorService {

    fun generateIntegerInRange(from: Int, to: Int): Int
}