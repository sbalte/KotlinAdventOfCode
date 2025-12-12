package dec2025

import AdventOfCodeConstant.EMPTY_STRING
import AdventOfCodeDay
import AdventOfCodeYear
import FileUtil.readInputFileLine

object DayThree {
    fun numListPair(): Sequence<Sequence<Pair<Int, Int>>> =
        readInputFileLine(AdventOfCodeDay.DAY_THREE to AdventOfCodeYear.YEAR_2025)
            .filter { it.isNotEmpty() }
            .map { it.toList().mapIndexed { index, ch -> index to ch.digitToInt() }.asSequence() }.asSequence()

    fun partOne(listPair: Sequence<Sequence<Pair<Int, Int>>>): Long = listPair.sumOf { bank ->
        bank.maxBy { (_, second) -> second }
            .let { maxPair ->
                (bank.filter { (first, _) -> first > maxPair.first }
                    .maxByOrNull { (_, second) -> second } ?: bank.filter { (first, _) -> first < maxPair.first }
                    .maxBy { (_, second) -> second })
                    .let { sMaxPair ->
                        listOf(maxPair, sMaxPair).sortedBy { (f, _) -> f }
                            .joinToString(EMPTY_STRING) { p -> p.second.toString() }.toLong()
                    }
            }
    }

    fun partTwo(listPair: Sequence<LongRange>): Long = TODO()
}

fun main() {
    DayThree.numListPair().let { listPair ->
        DayThree.partOne(listPair).also { println("Day Two Part One Answer: $it") }
//        DayThree.partTwo(listPair).also { println("Day Two Part Two Answer: $it") }
    }
}