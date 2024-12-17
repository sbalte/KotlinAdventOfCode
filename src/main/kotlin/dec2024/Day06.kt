@file:Suppress("unused")

package dec2024

import AdventOfCodeConstant.ONE
import AdventOfCodeConstant.ZERO
import AdventOfCodeDay.DAY_SIX
import AdventOfCodeYear.YEAR_2024
import AdventPart
import AdventPart.PART_ONE
import AdventPart.PART_TWO
import FileUtil.readInputFileLine
import arrow.core.firstOrNone
import arrow.core.getOrElse
import arrow.core.toOption
import arrow.fx.coroutines.parMap
import dec2024.Day06.GuardDirection.*
import dec2024.Day06.GuardSpace.BLOCK_CHAR
import dec2024.Day06.GuardSpace.CURRENT_POSITION
import dec2024.Day06.partOneSolution
import dec2024.Day06.partTwoSolution
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import map
import mapSecond
import kotlin.time.measureTimedValue
import java.lang.Runtime

object Day06 {
    enum class GuardDirection { UP, DOWN, LEFT, RIGHT }
    enum class GuardSpace(val space: Char) {
        BLOCK_CHAR('#'), GO_CHAR('.'), CURRENT_POSITION('^');
        operator fun invoke() = space
    }
    val concurrency = Runtime.getRuntime().availableProcessors()
    val matrix: List<String> = readInputFileLine(DAY_SIX to YEAR_2024)
    private fun isReachedEnd(
        currSpaceAndDirection: Pair<GuardDirection, Pair<Int, Int>>,
    ): Boolean = currSpaceAndDirection.let { (direction, space) ->
        when (direction) {
            UP -> space.first.dec() < ZERO
            DOWN -> space.first.inc() > matrix[space.first].lastIndex
            LEFT -> space.second.dec() < ZERO
            RIGHT -> space.second.inc() > matrix[space.first].length.dec()
        }
    }
    fun currentSpace(counterMap: MutableMap<Pair<GuardDirection, Pair<Int, Int>>, Int>):
            Pair<GuardDirection, Pair<Int, Int>> = matrix.mapIndexed { rIndex, row ->
        row.toCharArray().mapIndexed { cIndex, col ->
            rIndex to (cIndex to col)
        }.filter { colPair -> colPair.second.second == CURRENT_POSITION() }.firstOrNone()
    }.filter { it.isSome() }.firstOrNone().flatMap { it }
        .map { pair -> UP to (pair.first to pair.second.first) }
        .getOrElse { UP to (ZERO to ZERO) }.also { result ->
            counterMap.registerVisit(UP to result.second)
        }
    fun MutableMap<Pair<GuardDirection, Pair<Int, Int>>, Int>.registerVisit(newPosition: Pair<GuardDirection, Pair<Int, Int>>) =
        compute(newPosition) { key, oldValue ->
            oldValue.toOption().map { it.inc() }.getOrElse { ONE }
        }
    private fun moveForward(
        counterMap: MutableMap<Pair<GuardDirection, Pair<Int, Int>>, Int>,
        currSpaceAndDirection: Pair<GuardDirection, Pair<Int, Int>>,
        directionLogic: (GuardDirection) -> GuardDirection,
        adhocObstacleLogic: (Pair<Int, Int>) -> Boolean,
        inLoopLogic: (currPosition: Pair<GuardDirection, Pair<Int, Int>>) -> Boolean,
    ): Pair<Pair<GuardDirection, Pair<Int, Int>>, Boolean> = currSpaceAndDirection.let { (direction, space) ->
        val blockFilter: (Pair<Int, Int>) -> Boolean = { point ->
            matrix[point.first][point.second] == BLOCK_CHAR() || adhocObstacleLogic(point)
        }
        fun isHitBlock() = when(direction) {
                UP -> blockFilter(space.map { it.dec() })
                DOWN -> blockFilter(space.map { it.inc() })
                LEFT -> blockFilter(space.mapSecond { it.dec() })
                RIGHT -> blockFilter(space.mapSecond { it.inc() })
            }
        fun moveRight() =
            when(direction) {
                UP -> RIGHT
                DOWN -> LEFT
                LEFT -> UP
                RIGHT -> DOWN
            }
        val nextMoveLogic: (Pair<Int, Int>, Pair<Int, Int>) -> Pair<Pair<GuardDirection, Pair<Int, Int>>, Boolean> =
            { onRightMove, defaultMove ->
                isReachedEnd(currSpaceAndDirection).let { isEnd ->
                    when (isEnd) {
                        true -> currSpaceAndDirection to inLoopLogic(currSpaceAndDirection)
                        false -> isHitBlock().toOption().filter { it }
                            .map { moveRight() to onRightMove }
                            .getOrElse { direction to defaultMove }.let { it to inLoopLogic(it) }
                    }
                }
            }
        isReachedEnd(currSpaceAndDirection).toOption().filter { !it }.map {
            when (direction) {
                UP -> nextMoveLogic(space.mapSecond { it.inc() }, space.map { it.dec() })
                DOWN -> nextMoveLogic(space.mapSecond { it.dec() }, space.map { it.inc() })
                LEFT -> nextMoveLogic(space.map { it.dec() }, space.mapSecond { it.dec() })
                RIGHT -> nextMoveLogic(space.map { it.inc() }, space.mapSecond { it.inc() })
            }.also { result -> counterMap.registerVisit(directionLogic(result.first.first) to result.first.second) }
        }.getOrElse { currSpaceAndDirection to false }
    }
    fun findSolution(
        adventPart: AdventPart,
        adhocObstacle: Pair<Int, Int>,
    ): Pair<MutableMap<Pair<GuardDirection, Pair<Int, Int>>, Int>, Boolean> = mutableMapOf<Pair<GuardDirection, Pair<Int, Int>>, Int>().let { counterMap ->
        tailrec fun recursiveMove(
            move: Pair<GuardDirection, Pair<Int, Int>>,
            startPosition: Pair<GuardDirection, Pair<Int, Int>>
        ): Pair<Pair<GuardDirection, Pair<Int, Int>>, Boolean> =
            moveForward(counterMap, move, when(adventPart) {
                PART_ONE -> { _ -> UP }
                PART_TWO -> { direction -> direction }
            }, when(adventPart) {
                PART_ONE -> { _ -> false }
                PART_TWO -> { currPosition -> currPosition == adhocObstacle }
            }, when(adventPart) {
                PART_ONE -> { _ -> false }
                PART_TWO -> { currPosition -> counterMap.contains(currPosition) }
            }).let {
                if (it.first == move || it.second) return move to it.second
                return recursiveMove(it.first, startPosition)
            }
        currentSpace(counterMap).let { recursiveMove(it, it) }.run {
            this.second.toOption().filter { it }.map { println("End Infinite Loop State: $this") }
            counterMap to this.second
        }
    }
    fun partOneSolution() = findSolution(PART_ONE, ZERO to ZERO).first.keys.size
    fun partTwoSolution(): Int = runBlocking(Dispatchers.Default) {
        currentSpace(mutableMapOf()).let { startPosition ->
            (ZERO..matrix.size.dec()).flatMap { rIndex ->
                (ZERO..matrix[rIndex].length.dec()).map { cIndex -> rIndex to cIndex }
            }.filter {
                val (rIndex, cIndex) = it
                matrix[rIndex][cIndex] != BLOCK_CHAR() && startPosition.second != it
            }.toOption().map {
                it.parMap(concurrency = concurrency) { obstacle ->
//                    println("${Thread.currentThread().name} - running findSolution() with obstacle: $obstacle}")
                    findSolution(PART_TWO, obstacle)
                }.filter { it.second }.map { ONE }.sum()
            }.getOrElse { ZERO }
        }
    }
}
fun main() {
    fun benchmarkRun(logic: () -> String) = measureTimedValue { logic() }.let { tv ->
        println(tv.value)
        println("Time Taken: ${tv.duration.inWholeMilliseconds} ms")
    }
    "Day Six Part %s Answer:".also { msg ->
        repeat(1) { counter ->
            println("Run $counter:")
            benchmarkRun { partOneSolution().let { "${msg.format(PART_ONE())} $it" } }
            benchmarkRun { partTwoSolution().let { "${msg.format(PART_TWO())} $it" } }
        }
    }
}