package `3`

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day3Tests {

    @Test
    fun solution() {
        val input = File("src/main/kotlin/3/input.txt").readLines()
        println(Part1(input).call())

        println(Part2(input).call())
    }

    @Test
    fun `part 1 example`() {

        val input = """00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010""".split("\n")

        val actual = Part1(input).call()
        assertEquals(198, actual)

    }

    @Test
    fun `part 2 example`() {

        val input = """00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010""".split("\n")

        val actual = Part2(input).call()
        assertEquals(230, actual)

    }





}