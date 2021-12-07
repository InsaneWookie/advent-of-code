package `5`

import java.io.File
import java.util.concurrent.Callable

fun main() {

    println(File("").absolutePath)
    val input = File("2021/src/main/kotlin/5/input.txt").readLines()

    println(Part1(input).call())

}

class Part1(private val input: List<String>) : Callable<Int> {
    override fun call(): Int {

        val grid = Array(1000) { IntArray(1000) }

        input.forEach { line ->
            val c = line.split(" -> ")
            var start = c[0].split(",")
            var end = c[1].split(",")

            if (start[0] == end[0] || start[1] == end[1]) {

                if (start[0].toInt() > end[0].toInt()) {
                    val a = start[0]
                    start = listOf(end[0], start[1])
                    end = listOf(a, end[1])
                }

                if (start[1].toInt() > end[1].toInt()) {
                    val a = start[1]
                    start = listOf(start[0], end[1])
                    end = listOf(end[0], a)

                }

                for (x in start[0].toInt()..end[0].toInt()) {
                    for (y in start[1].toInt()..end[1].toInt()) {

                        grid[y][x]++
                    }
                }
            }





            println(grid.toCollection(ArrayList()))

        }
        var count = 0
        for (x in grid.indices) {
            for (y in grid[0].indices) {

                if (grid[y][x] >= 2) {
                    count++
                }
            }
        }
        return count
    }
}