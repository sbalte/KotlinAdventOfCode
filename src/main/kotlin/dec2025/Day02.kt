package dec2025

import AdventOfCodeConstant.COMMA_SPLIT_REGEX
import AdventOfCodeConstant.DASH_SPLIT_REGEX
import AdventOfCodeDay
import AdventOfCodeYear
import FileUtil.readInputFileLine

object DayTwo {
    fun numListPair(): Sequence<LongRange> =
        readInputFileLine(AdventOfCodeDay.DAY_TWO to AdventOfCodeYear.YEAR_2025)
            .filter { it.isNotEmpty() }
            .flatMap { line ->
                line.split(COMMA_SPLIT_REGEX)
                    .map { it.split(DASH_SPLIT_REGEX).let { (first, last) -> first.toLong() .. last.toLong() } }
            }.asSequence()

    fun partOne(listPair: Sequence<LongRange>): Long = listPair.flatMap { range ->
        range.asSequence().map { num -> num to num.toString() }
            .filter { it.second.length.rem(2) == 0 }
            .map { (first, second) -> first to (second.length / 2).let { half -> second.take(half) to second.drop(half) } }
            .filter { it.second.first == it.second.second }
            .map { it.first }
    }.sum()

    fun partTwo(listPair: Sequence<LongRange>): Long = listPair.flatMap { range ->
        range.asSequence().map { num -> num to num.toString() }
            .map { (first, second) ->
                (1..second.length.dec()).map { cSize ->
                    first to second.chunked(cSize).let { cList ->
                        cList.all { it == cList.first() }
                    }
                }.firstOrNull { (first, second) -> second }?.first?.also { println(">>>>$it is invalid and passes chunks repeated test") } ?: 0
            }
    }.distinct().sum()
}

fun main() {
    DayTwo.numListPair().let { listPair ->
        DayTwo.partOne(listPair).also { println("Day Two Part One Answer: $it") }
        DayTwo.partTwo(listPair).also { println("Day Two Part Two Answer: $it") }
    }
}