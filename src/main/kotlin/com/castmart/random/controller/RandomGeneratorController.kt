package com.castmart.random.controller

import com.castmart.random.service.IRandomGeneratorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/random/")
class RandomGeneratorController(val randomGeneratorService: IRandomGeneratorService) {

    @GetMapping("{from}/{to}/")
    fun generatePseudoRandom(@PathVariable from: Int, @PathVariable to: Int): ResponseEntity<Any> {
        return ResponseEntity.ok(
            RandomGeneratedResponse(
                from = from,
                to = to,
                value = randomGeneratorService.generateIntegerInRange(from, to)
            )
        )
    }

    data class RandomGeneratedResponse(
        val from: Int, val to: Int, val value: Int
    )
}