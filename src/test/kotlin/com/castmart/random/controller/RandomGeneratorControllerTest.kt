package com.castmart.random.controller

import com.castmart.random.MockBeans
import com.castmart.random.service.IRandomGeneratorService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(RandomGeneratorController::class)
@ContextConfiguration(
    classes = [
        MockBeans::class,
        RandomGeneratorController::class
    ]
)
class RandomGeneratorControllerTest {

    @Autowired lateinit var mockMvc: MockMvc

    @Autowired lateinit var randomGeneratorService: IRandomGeneratorService

    @Test
    fun `Test response code is 200 for get method and contains the right response structure`() {
        val from = 1
        val to = 3
        val result = 2
        Mockito.`when`(
            randomGeneratorService.generateIntegerInRange(from, to)
        ).thenReturn(result)

        mockMvc.get("/api/v1/random/$from/$to/").andExpect {
            status { isOk() }
            content {
                jsonPath("$.from") { equals(from) }
                jsonPath("$.to") { equals(to) }
                jsonPath("$.value") { equals(result) }
            }
        }
    }
}
