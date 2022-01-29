package `8`

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

class Day8Tests {

    @Test
    fun solution() {
        val input = File("src/main/kotlin/7/input.txt").readLines()
        println(Part1(input).call())

        println(Part2(input).call())
    }

    @Test
    fun `part 1 example`() {

        val input = File("src/test/kotlin/8/example.txt").readLines()

        val actual = Part1(input).call()
        assertEquals(26, actual)
    }

    @Test
    fun `part 2 example`() {
        val input = File("src/test/kotlin/7/example.txt").readLines()

        val actual = Part2(input).call()
        assertEquals(168, actual)
    }

    @Test
    fun `part 2 example single line`() {
        val input = listOf("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf")

        val actual = Part2(input).call()
        assertEquals(5353, actual)
    }

    @Test
    fun `part 1 solution`() {

        val input = File("src/main/kotlin/8/input.txt").readLines()

        val actual = Part1(input).call()
        assertEquals(367, actual)
    }

    @Test
    fun `part 2 solution`() {
        val input = File("src/main/kotlin/8/input.txt").readLines()

        var actual: Int
        val time = measureTimeMillis {
             actual = Part2(input).call()
        }
        println(time)
        assertEquals(94813675, actual)
    }




}