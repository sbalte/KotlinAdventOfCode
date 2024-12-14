package com.balte.kotlin.adventofcode.`2024`

import arrow.core.getOrElse
import arrow.core.toOption
import com.balte.kotlin.adventofcode.util.AdventOfCodeConstant.ZERO
import com.balte.kotlin.adventofcode.util.AdventPart.PART_ONE
import com.balte.kotlin.adventofcode.util.AdventPart.PART_TWO
import kotlin.test.Test
import kotlin.test.assertTrue

class AdventOfCodeDayOneTest {
    private val numListPair by lazy { numListPair() }
    private val answer = mapOf(
        PART_ONE to 2375403.toLong(),
        PART_TWO to 23082277.toLong(),
    )
    @Test
    fun partOneTest() {
        numListPair.let { listPair ->
            partOne(listPair).also { println("Part One Answer: $it") }
        }.apply {
            assertTrue( this == answer[PART_ONE].toOption().getOrElse { ZERO }.toLong(), "Part One test failed")
        }
    }

    @Test
    fun partTwoTest() {
        numListPair.let { listPair ->
            partTwo(listPair).also { println("Part Two Answer: $it") }
        }.apply {
            assertTrue( this == answer[PART_TWO].toOption().getOrElse { ZERO }.toLong(), "Part One test failed")
        }
    }
}