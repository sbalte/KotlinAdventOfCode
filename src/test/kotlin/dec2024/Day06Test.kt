package dec2024

import AdventOfCodeConstant.ZERO
import AdventPart.PART_ONE
import AdventPart.PART_TWO
import arrow.core.getOrElse
import arrow.core.toOption
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class Day06Test {
    private val answer = mapOf(
        PART_ONE to 4515,
        PART_TWO to 82857512,
    )
    @Test
    fun partOneTest() {
        Day06.partOneSolution().let { ans ->
            assertTrue(ans == answer[PART_ONE].toOption().getOrElse { ZERO })
        }
    }
}