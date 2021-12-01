package `1`

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Part1Tests {

    @Test
    fun solution () {
        val input = File("src/main/kotlin/1/input.txt").readLines().map { it.toInt() }
        val result = Part1(input).call()
        println(result)
    }

    @Test
    fun test1() {
        val input = emptyList<Int>()
        val actual = Part1(input).call()

        assertEquals(0, actual)
    }

    @Test
    fun test2() {
        val input = listOf(1,2,3)
        val actual = Part1(input).call()

        assertEquals(2, actual)
    }
}