import AdventOfCodeConstant.ONE
import AdventOfCodeConstant.ZERO
import arrow.core.getOrElse
import arrow.core.toOption

@Suppress("unused")
object FileUtil {
    fun readInputFileLine(dayYearPair: Pair<AdventOfCodeDay, AdventOfCodeYear>): List<String> =
        "/${dayYearPair.second()}/day${dayYearPair.first()}/input.txt".let {
        readFileFromClasspath(it)
    }
    private fun readFileFromClasspath(fileName: String): List<String> =
        this::class.java.getResourceAsStream(fileName)!!.bufferedReader().readLines()
}

object AdventOfCodeConstant {
    const val EMPTY_STRING = ""
    const val SPACE = " "
    const val ZERO = 0
    const val ONE = 1
    const val TWO = 2
    const val THREE = 3
    val SPACE_SPLIT_REGEX = "\\s+".toRegex()
    val COMMA_SPLIT_REGEX = ",".toRegex()
}

enum class AdventPart(val displayStr: String) {
    PART_ONE("One"), PART_TWO("Two");
    operator fun invoke() = displayStr
}
enum class AdventOfCodeYear(val year: Int) {
    YEAR_2024(2024), YEAR_2025(2025), YEAR_2026(2026);
    operator fun invoke() = year
}
enum class AdventOfCodeDay(val day: Int) {
    DAY_ONE(1), DAY_TWO(2), DAY_THREE(3), DAY_FOUR(4), DAY_FIVE(5), DAY_SIX(6);
    operator fun invoke() = day
}
fun MutableMap<Pair<Int, Int>, Int>.registerVisit(newPosition: Pair<Int, Int>) =
    compute(newPosition) { key, oldValue ->
        oldValue.toOption().map { it.inc() }.getOrElse { ONE }
    }