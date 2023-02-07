package com.castmart.random

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RandomApplication

fun main(args: Array<String>) {
	runApplication<RandomApplication>(*args)
}
