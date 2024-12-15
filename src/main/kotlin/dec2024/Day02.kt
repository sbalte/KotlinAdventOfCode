package dec2024

import AdventOfCodeConstant.ONE
import AdventOfCodeConstant.SPACE_SPLIT_REGEX
import AdventOfCodeConstant.THREE
import AdventOfCodeConstant.TWO
import AdventOfCodeConstant.ZERO
import FileUtil.readInputFileLine
import arrow.core.Option
import arrow.core.firstOrNone
import arrow.core.getOrElse
import arrow.core.toOption
import dec2024.DayTwo.isSafePartOne
import dec2024.DayTwo.isSafePartTwo
import dec2024.DayTwo.numListPair
import dec2024.DayTwo.safeCount
import kotlin.math.abs

object DayTwo {
    fun numListPair(): List<List<Int>> =
        readInputFileLine(AdventOfCodeDay.DAY_TWO to AdventOfCodeYear.YEAR_2024)
            .filter { it.isNotEmpty() }
            .map { line -> line.split(SPACE_SPLIT_REGEX) }
            .map { list -> list.map { it.toInt() } }

    val validDiff = listOf(ONE, TWO, THREE)
    val isValidFilter: (List<Int>) -> List<Int> = { list ->
        list.filterIndexed { index, _ -> index > ZERO && index < list.lastIndex }
            .filterIndexed { index, num ->
                isValidDiff((list[index] - num).toOption(), (num - list[index.inc().inc()]).toOption()).not()
            }
    }

    private fun isValidDiff(currDiff: Option<Int>, nextDiff: Option<Int>): Boolean =
        currDiff.flatMap { cNum -> nextDiff.map { cNum to it } }.filter {
            ((it.first < ZERO && it.second < ZERO) || (it.first > ZERO && it.second > ZERO)) &&
                    ((abs(it.first) in validDiff) && (abs(it.second) in validDiff))
        }.map { true }.getOrElse { nextDiff.map { false }.getOrElse { true } }

    fun isSafePartOne(list: List<Int>) = isValidFilter(list).map { ONE }.sum() == ZERO
    fun isSafePartTwo(list: List<Int>) = (ZERO..list.lastIndex).map { index ->
        list.filterIndexed { ind, _ -> ind != index }.let { fList ->
            if (isValidFilter(fList).map { ONE }.sum() == ZERO) true
            else false
        }
    }.firstOrNone { it }.getOrElse { false }

    fun safeCount(inputList: List<List<Int>>, partLogic: (List<Int>) -> Boolean): Int =
        inputList.filter { it.isNotEmpty() }.map { list -> partLogic(list) }
            .filter { it }.map { ONE }.sum()
}

fun main() {
    numListPair().let { iList ->
        safeCount(iList) { list -> isSafePartOne(list) }
            .let { println("Day Two Part One Answer: $it") }
        safeCount(iList) { list -> isSafePartTwo(list) }
            .let { println("Day Two Part Two Answer: $it") }
    }
}