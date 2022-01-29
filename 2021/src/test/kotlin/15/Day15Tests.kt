package `15`

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day15Tests {


    @Test
    fun `part 1 example`() {

        val input = File("src/test/kotlin/15/example.txt").readLines()

        val actual = Part1(input).call()
        assertEquals(40, actual)
    }

    @Test
    fun `part 2 example`() {
        val input = File("src/test/kotlin/14/example.txt").readLines()

        val actual = Part2(input).call()
        assertEquals(0, actual)
    }

    @Test
    fun `part 1 solution`() {

        val input = File("src/main/kotlin/15/input.txt").readLines()

        val actual = Part1(input).call()
        assertEquals(0, actual)
    }

    @Test
    fun `part 2 solution`() {
        val input = File("src/main/kotlin/14/input.txt").readLines()

        val actual = Part2(input).call()
        assertEquals(0, actual)
    }


}