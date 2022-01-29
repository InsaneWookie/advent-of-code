package `10`

import java.io.File
import java.util.concurrent.Callable

fun main() {

    println(File("").absolutePath)
    val input = File("2021/src/main/kotlin/10/input.txt").readLines()

    println(Part1(input).call())

}

class Part1(private val input: List<String>) : Callable<Int> {


    override fun call(): Int {

        var out = 0

        for (line in input){
            var count = 0
            val bracket = 0
            val square = 0
            val curly = 0
            val angle = 0

            val tokens = line.split("").filter { it.isNotBlank() }

            for (t in tokens){
                when(t){
                    "(", "[", "{", "<" -> count++
                    ")", "]", "}", ">" -> count--
                }

                if(count < 0){
                    out += when(t){
                        ")" -> 3
                        "]" -> 57
                        "}" -> 1197
                        ">" -> 25137
                        else -> 0
                    }
                }
            }
        }

        return out
    }
}