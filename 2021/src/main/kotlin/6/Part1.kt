package `6`

import java.io.File
import java.util.concurrent.Callable

fun main() {

    println(File("").absolutePath)
    val input = File("2021/src/main/kotlin/6/input.txt").readLines()

    println(Part1(input).call())

}

class Part1(private val input: List<String>) : Callable<Int> {
    override fun call(): Int {

        var fish = input[0].split(',').map { it.toInt() }

        for (i in 1..80) {
            fish = fish.map { it - 1 }
            val zeros = fish.filter { it == -1 }.size
            fish = fish.map { if (it == -1) 6 else it }
            fish = fish + IntArray(zeros) { 8 }.toList()

        }
        return fish.size
    }
}