package dec2024

import AdventOfCodeConstant.ONE
import AdventOfCodeConstant.TWO
import AdventOfCodeConstant.ZERO
import AdventOfCodeDay.DAY_THREE
import AdventOfCodeYear.YEAR_2024
import FileUtil.readInputFileLine
import arrow.core.getOrElse
import arrow.core.toOption
import dec2024.Day03.inputString
import dec2024.Day03.partOneSolution
import dec2024.Day03.partTwoSolution
import kotlin.io.println
import kotlin.text.toRegex

object Day03 {
    private const val multiplyRegex = """mul\((?<fNum>\d{1,3}),(?<sNum>\d{1,3})\)"""
    private const val doDontRegex = """do(n't)?\(\)"""
    val inputString by lazy { readInputFileLine(DAY_THREE to YEAR_2024) }
    val multiplyLogic: (MatchResult) -> Long = { match -> match.groupValues[ONE].toLong() * match.groupValues[TWO].toLong() }
    fun partOneSolution(input: String) = multiplyRegex.toRegex().findAll(input).sumOf { multiplyLogic(it) }
    fun partTwoSolution(input: String): Long = "$multiplyRegex|$doDontRegex".toRegex().findAll(input)
        .fold(true to ZERO.toLong()) { prev, match ->
            when(match.value) {
                "do()" -> true to prev.second
                "don't()" -> false to prev.second
                else -> prev.first to prev.first.toOption().filter { it }.map { prev.second + multiplyLogic(match) }.getOrElse { prev.second }
            }
        }.second
}

fun main() {
    inputString.map { partOneSolution(it) }.sum().let {
        println("Day Three Part One Answer: $it")
    }
    inputString.map { partTwoSolution(it) }.sum().let {
        println("Day Three Part Two Answer: $it")
    }
}