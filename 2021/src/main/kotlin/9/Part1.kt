package `9`

import java.io.File
import java.util.concurrent.Callable
import kotlin.math.abs

fun main() {

    println(File("").absolutePath)
    val input = File("2021/src/main/kotlin/7/input.txt").readLines()

    println(Part1(input).call())

}

class Part1(private val input: List<String>) : Callable<Int> {

    fun findAdj(points: List<List<Int>>, x: Int, y: Int): MutableList<Int?> {

        val adj = mutableListOf<Int?>(null,null,null,null)

        /*
            0
          3   1
            2
         */

        if(points.getOrNull(y-1) != null && points[y].getOrNull(x) != null){
            adj[0] = (points[y-1][x])
        }

        if(points.getOrNull(y) != null && points[y].getOrNull(x+1) != null){
            adj[1] = (points[y][x+1])
        }

        if(points.getOrNull(y+1) != null && points[y].getOrNull(x) != null){
            adj[2] = (points[y+1][x])
        }

        if(points.getOrNull(y) != null && points[y].getOrNull(x-1) != null){
            adj[3] = (points[y][x-1])
        }




        return adj
    }

    override fun call(): Int {

        val points = input.map {
            it.split("").filter { it.isNotBlank() }.map { it.toInt() }
        }

        val minPoints = mutableListOf<Int>()

        for(y in points.indices){
            for(x in points[y].indices){
                val point = points[y][x]
                val adj = findAdj(points, x, y)


                if(adj.none { it != null && it <= point }){
                    minPoints.add(point)
                }

            }
        }
        println(minPoints)



        var out = minPoints.map { it+1 }.sum()


        return out
    }
}