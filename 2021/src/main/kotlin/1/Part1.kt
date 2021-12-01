package `1`

import java.io.File
import java.util.concurrent.Callable


fun main() {

    println(File("").absolutePath)
    val input = File("2021/src/main/kotlin/1/input.txt").readLines().map { it.toInt() }

    println(Part1(input).call())

}

class Part1(private val input: List<Int>) : Callable<Int>{

    override fun call(): Int {
        var prev = 999999
        var count = 0
        input.forEach {
            if(it > prev){
                count++
            }
            prev = it
        }

        return count
    }

}

