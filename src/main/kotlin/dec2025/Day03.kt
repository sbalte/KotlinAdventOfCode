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

    fun numListLine(): Sequence<String> =
        readInputFileLine(AdventOfCodeDay.DAY_THREE to AdventOfCodeYear.YEAR_2025)
            .filter { it.isNotEmpty() }.asSequence()

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
    fun partOne2(listPair: Sequence<String>): Long = listPair.sumOf { bank -> findMaxJoltage(bank) }

    fun findMaxJoltage(bank: String): Long {
        for(i in 99 downTo 11) {
            val tensDigit = (i / 10).digitToChar()
            val onesDigit = (i % 10).digitToChar()
            val tensIdx = bank.indexOf(tensDigit)
            if(tensIdx == -1) continue
            val onesIdx = bank.indexOf(onesDigit, startIndex = tensIdx.inc())
            if(onesIdx == -1) continue
            return (bank[tensIdx].digitToInt() * 10 + bank[onesIdx].digitToInt()).toLong().also { println(it) }
        }
        error("failed")
    }

    fun partTwo(listPair: Sequence<LongRange>): Long = TODO()
}

fun main() {
    DayThree.numListPair().let { listPair ->
        DayThree.partOne(listPair).also { println("Day Two Part One Answer: $it") }
//        DayThree.partTwo(listPair).also { println("Day Two Part Two Answer: $it") }
    }

//    DayThree.numListLine().let { listPair ->
//        DayThree.partOne2(listPair).also { println("Day Two Part One Answer Alternate: $it") }
//    }
}