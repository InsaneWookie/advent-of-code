package `4`

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day4Tests {

    @Test
    fun solution() {
        val input = File("src/main/kotlin/4/input.txt").readLines()
   //     println(Part1(input).call())

       println(Part2(input).call())
    }

    @Test
    fun `part 1 example`() {

        val input = File("src/test/kotlin/4/example.txt").readLines()

        val actual = Part1(input).call()
        assertEquals(4512, actual)

    }

    @Test
    fun `part 2 example`() {

        val input = File("src/test/kotlin/4/example.txt").readLines()

        val actual = Part2(input).call()
        assertEquals(1924, actual)
    }





}