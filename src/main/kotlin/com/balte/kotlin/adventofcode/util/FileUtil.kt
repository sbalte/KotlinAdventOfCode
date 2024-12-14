package com.balte.kotlin.adventofcode.util

@Suppress("unused")
object FileUtil {
    fun readFileFromClasspath(fileName: String): List<String> =
        this::class.java.getResourceAsStream(fileName)!!.bufferedReader().readLines()
}

object AdventOfCodeConstant {
    const val SPACE = " "
    const val ZERO = 0
    const val ONE = 1
    val SPACE_SPLIT_REGEX = "\\s+".toRegex()
}

enum class AdventPart { PART_ONE, PART_TWO }