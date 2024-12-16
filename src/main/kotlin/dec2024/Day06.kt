@file:Suppress("unused")

package dec2024

import AdventOfCodeConstant.ZERO
import FileUtil.readInputFileLine

import AdventOfCodeDay.DAY_SIX
import AdventOfCodeYear.YEAR_2024
import arrow.core.firstOrNone
import arrow.core.getOrElse
import arrow.core.toOption
import dec2024.Day06.GuardDirection.*
import dec2024.Day06.GuardSpace.BLOCK_CHAR
import dec2024.Day06.GuardSpace.CURRENT_POSITION
import dec2024.Day06.partOneSolution
import registerVisit

object Day06 {
    enum class GuardDirection { UP, DOWN, LEFT, RIGHT }
    enum class GuardSpace(val space: Char) {
        BLOCK_CHAR('#'), GO_CHAR('.'), CURRENT_POSITION('^');
        operator fun invoke() = space
    }
    val matrix: List<String> = readInputFileLine(DAY_SIX to YEAR_2024)
    private fun isReachedEnd(currSpaceAndDirection: Pair<GuardDirection, Pair<Int, Int>>): Boolean {
        val (direction, space) = currSpaceAndDirection
        return when(direction) {
            UP -> space.first.dec() < ZERO
            DOWN -> space.first.inc() > matrix[space.first].lastIndex
            LEFT -> space.second.dec() < ZERO
            RIGHT -> space.second.inc() > matrix[space.first].length.dec()
        }
    }
    fun currentSpace(counterMap: MutableMap<Pair<Int, Int>, Int>,): Pair<GuardDirection, Pair<Int, Int>> = matrix.mapIndexed { rIndex, row ->
        row.toCharArray().mapIndexed { cIndex, col ->
            rIndex to (cIndex to col)
        }.filter { colPair -> colPair.second.second == CURRENT_POSITION() }.firstOrNone()
    }.filter { it.isSome() }.firstOrNone().flatMap { it }
        .map { pair -> UP to (pair.first to pair.second.first) }
        .getOrElse { UP to (ZERO to ZERO) }.also { result ->
            counterMap.registerVisit(result.second)
        }
    private fun moveForward(
        counterMap: MutableMap<Pair<Int, Int>, Int>,
        currSpaceAndDirection: Pair<GuardDirection, Pair<Int, Int>>): Pair<GuardDirection, Pair<Int, Int>> {
        val (direction, space) = currSpaceAndDirection
        val blockFilter: (Char) -> Boolean = { gPosition ->
            gPosition == BLOCK_CHAR()
        }
        fun isHitBlock() = when(direction) {
                UP -> blockFilter(matrix[space.first.dec()][space.second])
                DOWN -> blockFilter(matrix[space.first.inc()][space.second])
                LEFT -> blockFilter(matrix[space.first][space.second.dec()])
                RIGHT -> blockFilter(matrix[space.first][space.second.inc()])
            }
        fun moveRight() =
            when(direction) {
                UP -> RIGHT
                DOWN -> LEFT
                LEFT -> UP
                RIGHT -> DOWN
            }
        val nextMoveLogic: (Pair<Int, Int>, Pair<Int, Int>) -> Pair<GuardDirection, Pair<Int, Int>> = { onRightMove, defaultMove ->
            when(isReachedEnd(currSpaceAndDirection)) {
                true -> currSpaceAndDirection
                false -> isHitBlock().toOption().filter { it }.map { moveRight() to (onRightMove.first to onRightMove.second) }
                    .getOrElse { direction to (defaultMove.first to defaultMove.second) }
            }
        }
        return isReachedEnd(currSpaceAndDirection).toOption().filter { !it }.map {
            when (direction) {
                UP -> nextMoveLogic(space.first to space.second.inc(), space.first.dec() to space.second)
                DOWN -> nextMoveLogic(space.first to space.second.dec(), space.first.inc() to space.second)
                LEFT -> nextMoveLogic(space.first.dec() to space.second, space.first to space.second.dec())
                RIGHT -> nextMoveLogic(space.first.inc() to space.second, space.first to space.second.inc())
            }.also { result -> counterMap.registerVisit(result.second) }
        }.getOrElse { currSpaceAndDirection }
    }
    fun partOneSolution(): Int = mutableMapOf<Pair<Int, Int>, Int>().let { counterMap ->
        tailrec fun recursiveMove(move: Pair<GuardDirection, Pair<Int, Int>>): Pair<GuardDirection, Pair<Int, Int>> =
            moveForward(counterMap, move).let {
                if (it == move) return move
                return recursiveMove(it)
            }
        println("End Position: ${recursiveMove(currentSpace(counterMap))}")
        counterMap.keys.size
    }
}

fun main() {
    println(partOneSolution().let { "Day Three Part One Answer: $it" })
}