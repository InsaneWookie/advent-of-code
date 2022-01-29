package `9`

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

class Day9Tests {


    @Test
    fun `part 1 example`() {

        val input = File("src/test/kotlin/9/example.txt").readLines()

        val actual = Part1(input).call()
        assertEquals(15, actual)
    }

//    @Test
//    fun `part 2 example`() {
//        val input = File("src/test/kotlin/9/example.txt").readLines()
//
//        val actual = Part2(input).call()
//        assertEquals(168, actual)
//    }
//
//    @Test
//    fun `part 2 example single line`() {
//        val input = listOf("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf")
//
//        val actual = Part2(input).call()
//        assertEquals(5353, actual)
//    }
//
//    @Test
//    fun `part 1 solution`() {
//
//        val input = File("src/main/kotlin/9/input.txt").readLines()
//
//        val actual = Part1(input).call()
//        assertEquals(367, actual)
//    }
//
//    @Test
//    fun `part 2 solution`() {
//        val input = File("src/main/kotlin/9/input.txt").readLines()
//
//        var actual: Int
//        val time = measureTimeMillis {
//             actual = Part2(input).call()
//        }
//        println(time)
//        assertEquals(94813675, actual)
//    }




}