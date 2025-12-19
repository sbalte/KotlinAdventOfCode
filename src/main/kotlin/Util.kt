import java.math.BigInteger
import java.security.MessageDigest
import kotlin.math.max
import kotlin.math.min

@Suppress("unused")
object FileUtil {
    fun readInputFileLine(dayYearPair: Pair<AdventOfCodeDay, AdventOfCodeYear>): List<String> =
        "/${dayYearPair.second()}/day${dayYearPair.first()}/input.txt".let {
        readFileFromClasspath(it)
    }
    private fun readFileFromClasspath(fileName: String): List<String> =
        this::class.java.getResourceAsStream(fileName)!!.bufferedReader().readLines()
}

fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

object AdventOfCodeConstant {
    const val EMPTY_STRING = ""
    const val SPACE = " "
    const val ZERO = 0
    const val ONE = 1
    const val TWO = 2
    const val THREE = 3
    val SPACE_SPLIT_REGEX = "\\s+".toRegex()
    val COMMA_SPLIT_REGEX = ",".toRegex()
    val DASH_SPLIT_REGEX = "-".toRegex()
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
    DAY_ONE(1), DAY_TWO(2), DAY_THREE(3), DAY_FOUR(4), DAY_FIVE(5), DAY_SIX(6), DAY_NINETEEN(19);
    operator fun invoke() = day
}
operator fun <U, V, R> R.plus(pair: Pair<U, V>): Pair<R, Pair<U, V>> = this to pair
operator fun <U, V, R> Pair<U, V>.plus(item: R): Pair<Pair<U, V>, R> = this to item
fun <A, B> Pair<A, B>.swap() = second to first
fun <U, R> Pair<U, U>.mapBoth(block: (U) -> R): Pair<R, R> = block(first) to block(second)
fun <U, V, R> Pair<U, V>.map(block: (U) -> R): Pair<R, V> = block(first) to second
fun <U, V, R> Pair<U, V>.mapSecond(block: (V) -> R): Pair<U, R> = first to block(second)

//List
inline fun <T> MutableList<T>.mapInPlace(transform: (T) -> T) = forEachIndexed { idx, t -> this[idx] = transform(t) }
inline fun <T> MutableList<T>.mapInPlaceIndexed(transform: (idx: Int, T) -> T) =
    forEachIndexed { idx, t -> this[idx] = transform(idx, t) }

//LongRange
fun LongRange.rangeSize(): Long = (this.last - this.first).inc()
fun LongRange.mergeWith(other: LongRange): LongRange = min(this.first, other.first)..max(this.last, other.last)
fun LongRange.overlapsWith(other: LongRange): Boolean = this.first in other || this.last in other || other.first in this || other.last in this
fun LongRange.isRangeOverlap(nRange: LongRange): Triple<LongRange, LongRange, Boolean> =
    if (this.overlapsWith(nRange))
        Triple(nRange, nRange.mergeWith(this), true)
    else Triple(nRange, nRange, false)