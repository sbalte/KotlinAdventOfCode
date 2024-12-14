package dec2024

import AdventOfCodeConstant.ONE
import AdventOfCodeConstant.SPACE_SPLIT_REGEX
import AdventOfCodeConstant.ZERO
import FileUtil.readInputFileLine
import arrow.core.getOrElse
import arrow.core.toOption
import kotlin.math.abs

object DayOne {
    fun numListPair(): Pair<List<Long>, List<Long>> =
        readInputFileLine(AdventOfCodeDay.DAY_ONE to AdventOfCodeYear.YEAR_2024)
            .filter { it.isNotEmpty() }
            .map { line -> line.split(SPACE_SPLIT_REGEX).let { it[ZERO].toLong() to it[ONE].toLong() }
            }.unzip()
    fun partOne(listPair: Pair<List<Long>, List<Long>>): Long = listPair.let {
        (it.first.sorted() to it.second.sorted()).let { sListPair ->
            sListPair.first.mapIndexed { index, num -> abs(num - sListPair.second[index]) }
                .sum()
        }
    }
    fun partTwo(listPair: Pair<List<Long>, List<Long>>): Long = listPair.let { pList ->
        (pList.first.groupingBy { it }.eachCount() to pList.second.groupingBy { it }.eachCount()).let { pMap ->
            pMap.first.entries.map { e -> e.key * e.value * pMap.second[e.key].toOption().getOrElse { 0 } }.sum()
        }
    }
}

fun main() {
    DayOne.numListPair().let { listPair ->
        DayOne.partOne(listPair).also { println("Part One Answer: $it") }
        DayOne.partTwo(listPair).also { println("Part Two Answer: $it") }
    }
}