package dec2025

import AdventOfCodeDay
import AdventOfCodeYear
import FileUtil.readInputFileLine
import arrow.atomic.AtomicLong
import kotlin.math.absoluteValue

object DayOne {
    private val range: LongRange = 0L..100L
    fun numListPair(): List<Pair<String, Long>> =
        readInputFileLine(AdventOfCodeDay.DAY_ONE to AdventOfCodeYear.YEAR_2025)
            .filter { it.isNotEmpty() }
            .map { line ->
                line.split("(?<=[^0-9])(?=[0-9])".toRegex())
                    .let { (a, b) -> a to b.toLong().let { (if (a == "L") -1 else 1) * it } }
            }

    fun partOne(listPair: List<Pair<String, Long>>): Long = listPair.let {
        AtomicLong(50L).let { zeroCount ->
            listPair.asSequence().map { (direction, move) ->
                zeroCount.set(((zeroCount.get() + move) + range.last()).mod(range.last()))
                    .run {
                        println(">>>>Dial rotated $direction for ${move.absoluteValue} position and currently pointing at ${zeroCount.get()}")
                        zeroCount
                    }
            }.count { it.get() == 0L }.toLong()
        }
    }

    @Suppress("unused")
    fun partTwo(listPair: List<Pair<String, Long>>): List<LongRange> = "11-22,95-115".split(",").map {
        it.split("-").run { LongRange(this.first().toLong(), this.last().toLong()) }
    }
}

fun main() {
    DayOne.numListPair().let { listPair ->
        DayOne.partOne(listPair).also { println("Day One Part One Answer: $it") }
//        DayOne.partTwo(listPair).also { println("Day One Part Two Answer: $it") }
    }
}