@Suppress("unused")
object FileUtil {
    fun readInputFileLine(dayYearPair: Pair<AdventOfCodeDay, AdventOfCodeYear>) =
        "/${dayYearPair.second()}/day${dayYearPair.first()}/input.txt".let {
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
enum class AdventOfCodeYear(val year: Int) {
    YEAR_2024(2024), YEAR_2025(2025), YEAR_2026(2026);
    operator fun invoke() = year
}
enum class AdventOfCodeDay(val day: Int) {
    DAY_ONE(1), DAY_TWO(2), DAY_THREE(3), DAY_FOUR(4);
    operator fun invoke() = day
}