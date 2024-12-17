package dec2024

import AdventOfCodeConstant.ZERO
import AdventPart.PART_ONE
import arrow.core.getOrElse
import arrow.core.toOption
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class Day06Test {
    private val answer = mapOf(
        PART_ONE to 4515,
        PART_ONE to 1261,
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
}