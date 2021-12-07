package `7`

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import java.math.BigInteger
import kotlin.system.measureTimeMillis

class Day7Tests {

    @Test
    fun solution() {
        val input = File("src/main/kotlin/7/input.txt").readLines()
        println(Part1(input).call())

        println(Part2(input).call())
    }

    @Test
    fun `part 1 example`() {

        val input = File("src/test/kotlin/7/example.txt").readLines()

        val actual = Part1(input).call()
        assertEquals(37, actual)
    }

    @Test
    fun `part 2 example`() {
        val input = File("src/test/kotlin/7/example.txt").readLines()

        val actual = Part2(input).call()
        assertEquals(168, actual)
    }

    @Test
    fun `part 1 solution`() {

        val input = File("src/main/kotlin/7/input.txt").readLines()

        val actual = Part1(input).call()
        assertEquals(336040, actual)
    }

    @Test
    fun `part 2 solution`() {
        val input = File("src/main/kotlin/7/input.txt").readLines()

        var actual: Int
        val time = measureTimeMillis {
             actual = Part2(input).call()
        }
        println(time)
        assertEquals(94813675, actual)
    }




}