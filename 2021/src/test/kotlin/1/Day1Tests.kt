package `1`

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.io.File

class Day1Tests {

    protected var input: List<String> = emptyList()
    @BeforeAll
    fun beforeAll(){
        input = File("src/main/kotlin/1/input.txt").readLines()
    }

    @Test
    fun `part 1 solution`() {

      //  val input = File("src/main/kotlin/1/input.txt").readLines().map { it.toInt() }
        val result = Part1(input).call()
        println(result)
    }

    @Test
    fun `part 2 solution`() {

       // val input = File("src/main/kotlin/1/input.txt").readLines().map { it.toInt() }
        val result = Part2(input).call()
        println(result)
    }

}