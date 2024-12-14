import AdventOfCodeConstant.ZERO
import AdventPart.PART_ONE
import AdventPart.PART_TWO
import dec2024.DayOne.numListPair
import dec2024.DayOne.part1
import dec2024.DayOne.part2
import arrow.core.getOrElse
import arrow.core.toOption
import kotlin.test.Test
import kotlin.test.assertTrue

class Day01Test {
    private val numListPair by lazy { numListPair() }
    private val answer = mapOf(
        PART_ONE to 2375403.toLong(),
        PART_TWO to 23082277.toLong(),
    )
    @Test
    fun partOneTest() {
        numListPair.let { listPair ->
            part1(listPair).also { println("Part One Answer: $it") }
        }.apply {
            assertTrue( this == answer[PART_ONE].toOption().getOrElse { ZERO }.toLong(), "Part One test failed")
        }
    }

    @Test
    fun partTwoTest() {
        numListPair.let { listPair ->
            part2(listPair).also { println("Part Two Answer: $it") }
        }.apply {
            assertTrue( this == answer[PART_TWO].toOption().getOrElse { ZERO }.toLong(), "Part One test failed")
        }
    }
}