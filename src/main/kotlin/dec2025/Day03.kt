package dec2025

import AdventOfCodeConstant.EMPTY_STRING
import AdventOfCodeDay
import AdventOfCodeYear
import FileUtil.readInputFileLine

object DayThree {
    private fun seqSeqPair(): Sequence<List<Pair<Int, Int>>> =
        readInputFileLine(AdventOfCodeDay.DAY_THREE to AdventOfCodeYear.YEAR_2025)
            .filter { it.isNotEmpty() }
            .map { it.toList().mapIndexed { index, ch -> index to ch.digitToInt() } }.asSequence()
    private fun maxJoltage(bank: List<Pair<Int, Int>>, batteryChoiceNum: Int): Long =
        ((bank.size - batteryChoiceNum)..bank.size.dec()).fold((0 to EMPTY_STRING)) { acc, mIndex ->
            bank.drop(acc.first).takeWhile { (f, _) -> f <= mIndex }.maxBy { (_, s) -> s }.let { (f, s) -> f.inc() to acc.second + s }
        }.let { (_, s) -> s.toLong() }
            .also { println(">>>>Max Joltage: $it") }

    fun partOne(): Long = seqSeqPair().sumOf { bank -> maxJoltage(bank.toList(), 2) }
    fun partTwo(): Long = seqSeqPair().sumOf { bank -> maxJoltage(bank.toList(), 12) }
}

fun main() {
    //partOne correct answer: 16,842
    DayThree.partOne().also { println("Day Two Part One Answer: $it") }
    //partTwo correct answer: 167523425665348
    DayThree.partTwo().also { println("Day Two Part Two Answer: $it") }
}