package `2`

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day2Tests {

    @Test
    fun solution() {
        val input = File("src/main/kotlin/2/input.txt").readLines()
        val result = Part1(input).call()
        println(result)
        println(Part2(input).call())
    }

    @Test
    fun `folds both correctly`() {

        val result = Part1(
            listOf("up 1", "down 2", "forward 2", "forward 3")
        ).call()

        assertEquals(5, result)
    }

    @Test
    fun `part 2 calculates correct`() {
        val input =
            """forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2""".split("\n").map{ it.trim() }
        val result = Part2(input).call()

        assertEquals(900, result)
    }




}