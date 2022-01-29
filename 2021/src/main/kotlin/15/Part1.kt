package `14`

import java.io.File
import java.util.concurrent.Callable

fun main() {

    println(File("").absolutePath)
    val input = File("2021/src/main/kotlin/14/input.txt").readLines()

    println(Part1(input).call())

}

class Part1(private val input: List<String>) : Callable<Int> {

    fun indexPairs(pairs: List<String>): Map<String, String> {

        val map = mutableMapOf<String, String>()

        pairs.forEach {
            val p = it.split(" -> ")

            map.put(p[0], p[1])
        }

        return map.toMap()

    }

    fun updatePolly(polly: List<String>, pairs: Map<String, String>): MutableList<String> {

        val newPolly = mutableListOf<String>()

        for(i in 0 until polly.size-1){
            val key = polly[i] + polly[i+1]
            val insert = pairs[key]!!

            newPolly.add(polly[i])
            newPolly.add(insert)
        }

        newPolly.add(polly.last())
        return newPolly

    }

    override fun call(): Int {

        var out = 0


        val startPoly = input[0].split("").filter { it.isNotBlank() }.toMutableList()

        val pairs = indexPairs(input.slice(2 until input.size))



        var nextPolly = startPoly;
        for (step in 1..40) {
            nextPolly = updatePolly(nextPolly, pairs)

        }

        val counts = nextPolly.groupBy { it }.map { it.value.size }

        val min = counts.minOf { it }
        val max = counts.maxOf { it }

//        val min = counts.minOf { it.value }

        return max - min
    }
}