package `5`

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day5Tests {

    @Test
    fun solution() {
        val input = File("src/main/kotlin/5/input.txt").readLines()
       // println(Part1(input).call())

       println(Part2(input).call())
    }

    @Test
    fun `part 1 example`() {

        val input = File("src/test/kotlin/5/example.txt").readLines()

        val actual = Part1(input).call()
        assertEquals(5, actual)

    }

    @Test
    fun `part 2 example`() {

        val input = File("src/test/kotlin/5/example.txt").readLines()

        val actual = Part2(input).call()
        assertEquals(12, actual)
    }


}