package `1`

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Part2Tests {

    @Test
    fun solution() {

        val input = File("src/main/kotlin/1/input.txt").readLines().map { it.toInt() }
        val result = Part2(input).call()
        println(result)
    }

    @Test
    fun test1() {

        val input = listOf(1,2,3,4)
        val actual = Part2(input).call()
        assertEquals(2, actual)
    }
}