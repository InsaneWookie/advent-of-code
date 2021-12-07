package `6`

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import java.math.BigInteger

class Day6Tests {

    @Test
    fun solution() {
        val input = File("src/main/kotlin/6/input.txt").readLines()
       // println(Part1(input).call())

       println(Part2(input).call())
    }

    @Test
    fun `part 1 example`() {

        val input = File("src/test/kotlin/6/example.txt").readLines()

        val actual = Part1(input).call()
        assertEquals(5934, actual)

    }

    @Test
    fun `part 2 example`() {

        val input = File("src/test/kotlin/6/example.txt").readLines()

        val actual = Part2(input).call()
        assertEquals(BigInteger.valueOf(26984457539), actual)
    }


}