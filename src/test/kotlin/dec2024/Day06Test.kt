package dec2024

import AdventOfCodeConstant.ZERO
import AdventPart.PART_ONE
import AdventPart.PART_TWO
import arrow.core.getOrElse
import arrow.core.toOption
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// Context interfaces for the sample
interface Logger {
    fun log(message: String)
}

interface Configuration {
    val environment: String
}

// Function that uses context receivers
context(Logger, Configuration)
fun executeWithContext(action: String): String {
    log("Executing action: $action in environment: $environment")
    return "Executed: $action in $environment"
}

class Day06Test {
    private val answer = mapOf(
        PART_ONE to 4515,
        PART_TWO to 1261,
    )

    @Test
    fun partOneTest() {
        Day06.partOneSolution().let { ans ->
            assertTrue(ans == answer[PART_ONE].toOption().getOrElse { ZERO })
        }
    }

//    @Test
//    fun partTwoTest() {
//        Day06.partTwoSolution().let { ans ->
//            assertTrue(ans == answer[PART_TWO].toOption().getOrElse { ZERO })
//        }
//    }

    @Test
    fun contextReceiverSampleTest() {
        // Implement the context interfaces
        val logger = object : Logger {
            val logs = mutableListOf<String>()

            override fun log(message: String) {
                logs.add(message)
            }
        }

        val config = object : Configuration {
            override val environment = "test"
        }

        // Use the context receivers
        with(logger) {
            with(config) {
                val result = executeWithContext("sample action")
                assertEquals("Executed: sample action in test", result)
                assertEquals(1, logs.size)
                assertEquals("Executing action: sample action in environment: test", logs.first())
            }
        }
    }
}
