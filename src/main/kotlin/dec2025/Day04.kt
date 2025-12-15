package dec2025

import AdventOfCodeDay
import AdventOfCodeYear
import FileUtil.readInputFileLine
import dec2025.DayFour.MazeChar.EMPTY
import dec2025.DayFour.MazeChar.ROLL

object DayFour {
    private const val ZERO = 0
    private const val ONE = 1

    enum class MazeChar(val ch: Char) {
        EMPTY('.'), ROLL('@');
        operator fun invoke(): Char = ch
    }

    val enumMap: Map<Char, MazeChar> = MazeChar.entries.associateBy { it.ch }
    private fun paperMaze(): Map<Pair<Int, Int>, Char> =
        readInputFileLine(AdventOfCodeDay.DAY_FOUR to AdventOfCodeYear.YEAR_2025)
            .filter { it.isNotEmpty() }
            .let { pMaze ->
                (ZERO..pMaze.size.dec()).flatMap { row ->
                    pMaze[row].toList().mapIndexed { col, ch -> (row to col) to ch }
                }.toMap()
            }

    private fun movePosition(range: IntRange): List<Pair<Int, Int>> =
        range.flatMap { x ->
            range.map { y -> x to y }.filter { xy -> xy != (ZERO to ZERO) }
        }.also { println("Moves coordinates: $it") }

    fun partOne(): Long = paperMaze().let { origMap ->
        lazy { movePosition(-ONE..ONE) }.let { movePosition ->
            origMap.asSequence().filter { e -> enumMap[e.value] == ROLL }.sumOf { (xy, _) ->
                movePosition.value.sumOf { (row, col) ->
                    ((xy.first + row) to (xy.second + col)).let { move ->
                        (origMap.getOrDefault(
                            move,
                            EMPTY()
                        ) == ROLL()).let { flag -> if (flag) ONE.toLong() else ZERO.toLong() }
                    }
                }.let { numRoll -> if (numRoll < 4) ONE.toLong() else ZERO.toLong() }
            }
        }
    }

    fun partTwo(): Long = TODO()
}

fun main() {
    //answer: 1424
    DayFour.partOne().also { println("Day Four Part One Answer: $it") }
//    DayFour.partTwo().also { println("Day Four Part Two Answer: $it") }
}