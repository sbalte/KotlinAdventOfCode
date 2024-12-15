package dec2024

import AdventOfCodeConstant.ZERO
import AdventPart.PART_ONE
import AdventPart.PART_TWO
import arrow.core.getOrElse
import arrow.core.toOption
import dec2024.Day03.partOneSolution
import dec2024.Day03.partTwoSolution
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class Day03Test {
    private val answer = mapOf(
        PART_ONE to 190604937L,
        PART_TWO to 82857512,
    )
    @Test
    fun partOneTest() {
        Day03.inputString.map { partOneSolution(it) }.sum().let { ans ->
            assertTrue(ans == answer[PART_ONE].toOption().getOrElse { ZERO })
        }
    }
    @Test
    fun partTwoTest() {
        Day03.inputString.map { partTwoSolution(it) }.sum().let { ans ->
            assertTrue(ans == answer[PART_TWO].toOption().getOrElse { ZERO })
        }
    }
}