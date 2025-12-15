package dec2025

import AdventOfCodeDay
import AdventOfCodeYear
import FileUtil.readInputFileLine
import dec2025.DayFour.MazeChar.EMPTY
import dec2025.DayFour.MazeChar.ROLL
import dec2025.DayFour.paperMaze
import java.util.concurrent.atomic.AtomicLong

object DayFour {
    private const val ZERO = 0
    private const val ONE = 1

    enum class MazeChar(val ch: Char) {
        EMPTY('.'), ROLL('@');

        operator fun invoke(): Char = ch
    }

    val enumMap: Map<Char, MazeChar> = MazeChar.entries.associateBy { it.ch }
    internal fun paperMaze(): MutableMap<Pair<Int, Int>, Char> =
        readInputFileLine(AdventOfCodeDay.DAY_FOUR to AdventOfCodeYear.YEAR_2025)
            .filter { it.isNotEmpty() }
            .let { pMaze ->
                (ZERO..pMaze.size.dec()).flatMap { row ->
                    pMaze[row].toList().mapIndexed { col, ch -> (row to col) to ch }
                }.toMap().toMutableMap()
            }

    private fun movePosition(range: IntRange): List<Pair<Int, Int>> =
        range.flatMap { x ->
            range.map { y -> x to y }.filter { xy -> xy != (ZERO to ZERO) }
        }.also { println("Moves coordinates: $it") }

    private val movePosition by lazy { movePosition(-ONE..ONE) }

    fun partOne(origMap: MutableMap<Pair<Int, Int>, Char>): Pair<Long, List<Pair<Int, Int>>> = (mutableListOf<Pair<Int, Int>>() to origMap.asSequence()).let { (cRolls, mSeq) ->
        mSeq.filter { e -> enumMap[e.value] == ROLL }.sumOf { (xy, _) ->
            movePosition.sumOf { (row, col) ->
                ((xy.first + row) to (xy.second + col)).let { move ->
                    (enumMap[origMap.getOrDefault(
                        move,
                        EMPTY()
                    )] == ROLL).let { flag -> if (flag) ONE.toLong() else ZERO.toLong() }
                }
            }.let { numRoll ->
                if (numRoll < 4) {
                    cRolls.add(xy)
                    ONE.toLong()
                } else ZERO.toLong()
            }
        }.let { counter -> counter to cRolls }
    }

    fun partTwo(origMap: MutableMap<Pair<Int, Int>, Char>): Long = AtomicLong(0L).also { counter ->
        while(partOne(origMap)
            .let { (result, cRolls) ->
                cRolls.forEach { xy -> origMap[xy] = EMPTY() }
                counter.addAndGet(result); result } > 0
        );
    }.get()
}

fun main(): Unit = paperMaze().let { origMap ->
    //answer: 1424
    DayFour.partOne(origMap).also { println("Day Four Part One Answer: ${it.first}") }
    //answer: 8727
    DayFour.partTwo(origMap).also { println("Day Four Part Two Answer: $it") }
}