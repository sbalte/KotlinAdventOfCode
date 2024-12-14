@Suppress("unused")
object FileUtil {
    fun readInputFileLine(day: AdventOfCodeDay) = "/2024/day${day()}/input.txt".let {
        readFileFromClasspath(it)
    }
    private fun readFileFromClasspath(fileName: String): List<String> =
        this::class.java.getResourceAsStream(fileName)!!.bufferedReader().readLines()
}

object AdventOfCodeConstant {
    const val SPACE = " "
    const val ZERO = 0
    const val ONE = 1
    val SPACE_SPLIT_REGEX = "\\s+".toRegex()
}

enum class AdventPart { PART_ONE, PART_TWO }
enum class AdventOfCodeDay(val day: Int) {
    DAY_ONE(1), DAY_TWO(2), DAY_THREE(3), DAY_FOUR(4);
    operator fun invoke() = day
}