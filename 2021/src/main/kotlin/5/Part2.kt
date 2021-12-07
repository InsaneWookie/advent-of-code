package `5`

import java.util.concurrent.Callable


class Part2(private val input: List<String>) : Callable<Int> {
    override fun call(): Int {
        val grid = Array(1000) { IntArray(1000) }

        input.forEach { line ->
            val c = line.split(" -> ")
            val start = c[0].split(",")
            val end = c[1].split(",")

            var x = start[0].toInt()
            var y = start[1].toInt()

            val xDir = if (x < end[0].toInt()) 1 else if (x > end[0].toInt()) -1 else 0
            val yDir = if (y < end[1].toInt()) 1 else if (y > end[1].toInt()) -1 else 0

            val maxX = Math.max(start[0].toInt(), end[0].toInt())
            val minX = Math.min(start[0].toInt(), end[0].toInt())
            val maxY = Math.max(start[1].toInt(), end[1].toInt())
            val minY = Math.min(start[1].toInt(), end[1].toInt())

            while (x in minX..maxX && y in minY..maxY) {
                grid[y][x]++
                x += xDir
                y += yDir

            }
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