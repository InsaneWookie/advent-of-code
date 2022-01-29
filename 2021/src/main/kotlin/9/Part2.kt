package `9`

import java.math.BigInteger
import java.util.concurrent.Callable
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.min
import kotlin.math.roundToLong

data class Point(val x: Int, val y: Int, val height : Int)


class Part2(private val input: List<String>) : Callable<Int> {


    fun findAdj(points: List<List<Int>>, x: Int, y: Int): MutableList<Point?> {

        val adj = mutableListOf<Point?>(null,null,null,null)

        /*
            0
          3   1
            2
         */

        if(points.getOrNull(y-1) != null && points[y].getOrNull(x) != null){
            adj[0] = Point(x,y, (points[y-1][x]))
        }

        if(points.getOrNull(y) != null && points[y].getOrNull(x+1) != null){
            adj[1] = Point(x,y,(points[y][x+1]))
        }

        if(points.getOrNull(y+1) != null && points[y].getOrNull(x) != null){
            adj[2] = Point(x, y,(points[y+1][x]))
        }

        if(points.getOrNull(y) != null && points[y].getOrNull(x-1) != null){
            adj[3] = Point(x,y,(points[y][x-1]))
        }




        return adj
    }

//    fun getAdjPoints()

    override fun call(): Int {

        val points = input.map {
            it.split("").filter { it.isNotBlank() }.map { it.toInt() }
        }

        val minPoints = mutableListOf<Point>()

        val basins = mutableListOf<Int>()

        for(y in points.indices){
            for(x in points[y].indices){
                val point = points[y][x]
                val adj = findAdj(points, x, y)

                if(adj.none { it != null && it.height <= point }){
                    minPoints.add(Point(x,y, point))
                }

            }
        }



        for(i in minPoints.indices){
            var searching = true
            val basinPoints = getNextPoints(points, listOf(minPoints[i]))

            println(basinPoints)

        }

        println(minPoints)



        var out = basins.size


        return out
    }

    fun getNextPoints(allPoints: List<List<Int>>, points: List<Point>) : List<Point> {

        for (point in points) {
            val adjPoints = findAdj(allPoints, point.x, point.y).filter {
                it?.height == it!!.height + 1 && it.height < 9
            }.filterNotNull()

            return points + getNextPoints(allPoints, adjPoints)
        }

        return emptyList()

    }
}