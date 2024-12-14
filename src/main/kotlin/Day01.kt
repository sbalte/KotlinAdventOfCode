import AdventOfCodeConstant.ONE
import AdventOfCodeConstant.SPACE_SPLIT_REGEX
import AdventOfCodeConstant.ZERO
import FileUtil.readFileFromClasspath
import arrow.core.getOrElse
import arrow.core.toOption
import kotlin.math.abs

fun numListPair(): Pair<List<Long>, List<Long>> = readFileFromClasspath("/2024/day1/input.txt").filter { it.isNotEmpty() }.map { line ->
    line.split(SPACE_SPLIT_REGEX).let { it[ZERO].toLong() to it[ONE].toLong() }
}.unzip()

fun part1(listPair: Pair<List<Long>, List<Long>>): Long = listPair.let {
    (it.first.sorted() to it.second.sorted()).let { sListPair ->
        sListPair.first.mapIndexed { index, num -> abs(num - sListPair.second[index]) }
            .sum()
    }
}

fun part2(listPair: Pair<List<Long>, List<Long>>): Long = listPair.let { pList ->
    (pList.first.groupingBy { it }.eachCount() to pList.second.groupingBy { it }.eachCount()).let { pMap ->
        pMap.first.entries.map { e -> e.key * e.value * pMap.second[e.key].toOption().getOrElse { 0 } }.sum()
    }
}

fun main() {
    numListPair().let { listPair ->
        part1(listPair).also { println("Part One Answer: $it") }
        part2(listPair).also { println("Part Two Answer: $it") }
    }
}