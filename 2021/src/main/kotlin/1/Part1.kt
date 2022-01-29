package `1`

import java.io.File
import java.util.concurrent.Callable


fun main() {

    println(File("").absolutePath)
    val input = File("2021/src/main/kotlin/1/input.txt").readLines()

    println(Part1(input).call())

}

class Part1(private val input: List<String>) : Callable<Int>{

    override fun call(): Int {
        var prev = 999999
        var count = 0
        val data = input.map { it.toInt() }
        data.forEach {
            if(it > prev){
                count++
            }
            prev = it
        }

        return count
    }

}

