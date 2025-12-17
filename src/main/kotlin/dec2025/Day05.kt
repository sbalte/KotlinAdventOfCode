package dec2025

import AdventOfCodeConstant.DASH_SPLIT_REGEX
import AdventOfCodeDay
import AdventOfCodeYear
import FileUtil.readInputFileLine
import arrow.atomic.AtomicLong
import dec2025.DayFive.readIngredientFromFile
import kotlin.math.max
import kotlin.math.min

object DayFive {
    internal fun readIngredientFromFile(): Pair<MutableList<LongRange>, List<Long>> =
        readInputFileLine(AdventOfCodeDay.DAY_FIVE to AdventOfCodeYear.YEAR_2025)
            .let { ingredientsList ->
                ingredientsList.takeWhile { line -> line.isNotEmpty() }
                    .filter { line -> line.isNotEmpty() }
                    .map { line -> line.split(DASH_SPLIT_REGEX).let { (low, high) -> (low.toLong()..high.toLong()) } }.toMutableList() to
                        ingredientsList.dropWhile { line -> line.isNotEmpty() }
                            .filter { line -> line.isNotEmpty() }
                            .map { line -> line.toLong() }
            }

    fun partOne(ingredientsList: Pair<List<LongRange>, List<Long>>): Long = ingredientsList.let { (ranges, ingredients) ->
        ingredients.filter { ingredient -> ranges.any { range -> ingredient in range } }
            .map { 1L }.sumOf { it }
    }

    fun partTwo(ingredientsList: Pair<MutableList<LongRange>, List<Long>>) = ingredientsList.let { (ranges, _) ->
        fun getRangeSize(range: LongRange): Long = (range.last - range.first).inc()
        fun isRangeOverlap(pRange: LongRange, nRange: LongRange,): Triple<LongRange, LongRange, Boolean> =
            if (pRange.first in nRange || pRange.last in nRange || nRange.first in pRange || nRange.last in pRange)
                Triple(nRange, min(pRange.first, nRange.first)..max(pRange.last, nRange.last), true)
            else Triple(nRange, nRange, false)
        val result: AtomicLong = AtomicLong(0L)
        repeat(ranges.size) { _ ->
            ranges.removeFirst().let { pRange ->
                ranges.firstOrNull { nRange ->
                    isRangeOverlap(pRange, nRange).let { triple ->
                        if (triple.third) {
                            ranges.remove(triple.first)
                            ranges.add(triple.second)
                        }
                        triple.third
                    }
                }?: result.addAndGet(getRangeSize(pRange))
            }
        }
        result.get()
    }
}

fun main(): Unit = readIngredientFromFile().let { ingredients ->
    //answer: 811
    DayFive.partOne(ingredients).also { println("Day Five Part One Answer: $it") }
    //answer: 338189277144473
    DayFive.partTwo(ingredients).also { println("Day Five Part Two Answer: $it") }
}