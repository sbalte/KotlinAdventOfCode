package arrow

import mapSecond
import kotlin.test.Test

class ExampleTest {
    @Test
    fun testMap() {
        buildMap {
            putAll((1..10).map { it to it * 2 })
        }.also { println(it) }
        (1..10).flatMap { item ->
            (item to item * 2)
                .run { listOf(this, this.mapSecond { it + 1 }) }
        }.toList()
            .let { lPair ->
                buildMap {
                    lPair.forEach {
                        compute(it.first) { _, value ->
                            (value ?: mutableListOf()).apply { add(it.second) }
                        }
                    }
                }.apply { println(this) }
            }
    }
}