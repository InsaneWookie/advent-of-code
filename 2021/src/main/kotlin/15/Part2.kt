package `15`

import java.io.File
import java.util.concurrent.Callable

fun main() {

    println(File("").absolutePath)
    val input = File("2021/src/main/kotlin/14/input.txt").readLines()

    println(Part2(input).call())

}

class Part2(private val input: List<String>) : Callable<Int> {


    override fun call(): Int {

        return 0
    }

}