package `8`

import java.io.File
import java.util.concurrent.Callable
import kotlin.math.abs

fun main() {

    println(File("").absolutePath)
    val input = File("2021/src/main/kotlin/7/input.txt").readLines()

    println(Part1(input).call())

}

class Part1(private val input: List<String>) : Callable<Int> {
    override fun call(): Int {

        var out = 0
        input.forEach {
            val numbers = it.split(" | ")

            out += numbers[1].split(" ").fold(0) { acc, s ->
                acc + when (s.length) {
                    2, 3, 4, 7 -> 1
                    else -> 0
                }
            }
        }

        return out
    }
}