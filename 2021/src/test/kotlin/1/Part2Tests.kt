package `1`

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Part2Tests {

    @Test
    fun solution() {

        val input = File("src/main/kotlin/1/input.txt").readLines().map { it.toInt() }
        val result = Part2(input).call()
        println(result)
    }

}