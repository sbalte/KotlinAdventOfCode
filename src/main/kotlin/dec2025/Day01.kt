package dec2025

import AdventOfCodeDay
import AdventOfCodeYear
import FileUtil.readInputFileLine
import arrow.atomic.AtomicLong
import arrow.core.Tuple4
import arrow.core.Tuple5
import kotlin.math.abs

object DayOne {
    const val RIGHT_DIRECTION = "R"
    const val LEFT_DIRECTION = "L"
    const val ONE = 1L
    val range: LongRange = 0L..100L
    fun numListPair(): List<Pair<String, Long>> =
        readInputFileLine(AdventOfCodeDay.DAY_ONE to AdventOfCodeYear.YEAR_2025)
            .filter { it.isNotEmpty() }
            .map { line ->
                line.split("(?<=[^0-9])(?=[0-9])".toRegex())
                    .let { (a, b) -> a to b.toLong().let { (if (a == LEFT_DIRECTION) -ONE else ONE) * it } }
            }

    fun partOne(listPair: List<Pair<String, Long>>): Long = listPair.let {
        AtomicLong(50L).let { zeroCount ->
            listPair.asSequence().map { (_, move) ->
                zeroCount.set(((zeroCount.get() + move) + range.last) % range.last)
                    .run { zeroCount }
            }.count { it.get() == range.first }.toLong()
        }
    }

    @Suppress("unused")
    fun partTwo(listPair: List<Pair<String, Long>>): Long = (listPair
        .map { p -> Tuple4(p.first, p.second, abs(p.second/range.last), p.second%range.last)
        }.asSequence() to AtomicLong(50L))
        .let { (cMove, zeroCount) ->
            fun didCrossZero(direction: String, cPosition: Long, pPosition: Long): Boolean =
                (direction == RIGHT_DIRECTION && cPosition < pPosition) ||
                        (direction == LEFT_DIRECTION && cPosition > pPosition)
            fun numZeroCrossed(cPosition: Long, numRotation: Long, pPosition: Long, direction: String): Long =
                (if (cPosition == range.first) ONE else range.first) + numRotation +
                    if ((cPosition != range.first && pPosition != range.first &&
                                didCrossZero(direction, cPosition, pPosition))
                    ) ONE else range.first

            cMove.map { (direction, _, numRotation, move) ->
                    Tuple5(direction, zeroCount.get(), numRotation,
                        zeroCount.set(((zeroCount.get() + move) + range.last) % range.last).let { zeroCount.get() }, move)
            }.sumOf { (direction, pPosition, numRotation, cPosition, _) ->
                numZeroCrossed(cPosition, numRotation, pPosition, direction)
            }
        }
}

fun main() {
    DayOne.numListPair().let { listPair ->
        //answer: 1043
        DayOne.partOne(listPair).also { println("Day One Part One Answer: $it") }
        //answer: 5963
        DayOne.partTwo(listPair).also { println("Day One Part Two Answer: $it") }
    }
}