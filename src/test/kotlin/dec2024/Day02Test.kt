package dec2024

import AdventOfCodeConstant.ZERO
import AdventPart.PART_ONE
import AdventPart.PART_TWO
import arrow.core.getOrElse
import arrow.core.toOption
import dec2024.DayTwo.isSafePartOne
import dec2024.DayTwo.isSafePartTwo
import dec2024.DayTwo.numListPair
import dec2024.DayTwo.safeCount
import kotlin.test.Test
import kotlin.test.assertTrue

class Day02Test {
    private val numListPair by lazy { numListPair() }
    private val answer = mapOf(
        PART_ONE to 3,
        PART_TWO to 5,
    )
    val sampleList = listOf(
        listOf(7, 6, 4, 2, 1),
        listOf(1, 2, 7, 8, 9),
        listOf(9, 7, 6, 2, 1),
        listOf(1, 3, 2, 4, 5),
        listOf(8, 6, 4, 4, 1),
        listOf(1, 3, 6, 7, 9),
        listOf(1, 3, 6, 7, 9, 11, 13),
    )
    @Test
    fun partOneTest() {
        safeCount(sampleList){ list -> isSafePartOne(list) }.let { ans ->
            assertTrue(ans == answer[PART_ONE].toOption().getOrElse { ZERO })
        }
    }
    @Test
    fun partTwoTest() {
        safeCount(sampleList){ list -> isSafePartTwo(list) }.let { ans ->
            assertTrue(ans == answer[PART_TWO].toOption().getOrElse { ZERO })
        }
    }
}