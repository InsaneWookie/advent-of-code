package `15`

import java.io.File
import java.util.concurrent.Callable

fun main() {

    println(File("").absolutePath)
    val input = File("2021/src/main/kotlin/15/input.txt").readLines()

    println(Part1(input).call())

}

data class Point(val x: Int, val y: Int, val height : Int)

data class Coordinates(
    val x: Int,
    val y: Int,
    val height: Int
) : Graph.Vertex

data class Route(
    override val a: Coordinates,
    override val b: Coordinates
) : Graph.Edge<Coordinates> {
    val distance: Double
        get() {
            return a.height.toDouble()
//            val dLon = abs(a.lon - b.lon)
//            val dLat = abs(a.lat - b.lat)
//            return sqrt(dLon.pow(2) + dLat.pow(2))
        }
}

class AlgorithmAStarImpl(edges: List<Route>) : AlgorithmAStar<Coordinates, Route>(edges) {
    override fun costToMoveThrough(edge: Route): Double {
        return edge.distance
    }

    override fun createEdge(from: Coordinates, to: Coordinates): Route {
        return Route(from, to)
    }
}


class Part1(private val input: List<String>) : Callable<Int> {

    fun findAdj(points: List<List<Int>>, x: Int, y: Int): MutableList<Point?> {

        val adj = mutableListOf<Point?>(null,null,null,null)

        /*
            0
          3   1
            2
         */

        if(points.getOrNull(y-1) != null && points[y].getOrNull(x) != null){
            adj[0] = Point(x,y-1, (points[y-1][x]))
        }

        if(points.getOrNull(y) != null && points[y].getOrNull(x+1) != null){
            adj[1] = Point(x+1,y,(points[y][x+1]))
        }

        if(points.getOrNull(y+1) != null && points[y].getOrNull(x) != null){
            adj[2] = Point(x, y+1,(points[y+1][x]))
        }

        if(points.getOrNull(y) != null && points[y].getOrNull(x-1) != null){
            adj[3] = Point(x-1,y,(points[y][x-1]))
        }




        return adj
    }

    fun getNextPoints(allPoints: List<List<Int>>, points: List<Point>) : List<Point> {

        if(points.isEmpty()){
            //we are at the end
            val sum = points.sumOf { it.height }
        }

        for (point in points) {
            val adjPoints = findAdj(allPoints, point.x, point.y).filterNotNull()

            var pointsSoFar: List<Point> = emptyList()
            adjPoints.forEach {
                pointsSoFar = points + getNextPoints(allPoints, listOf(it))
            }
//            if(adjPoints[1] != null) {
//                pointsSoFar = points + getNextPoints(allPoints, listOf(adjPoints[1]!!))
//            }
//
//            if(adjPoints[2] != null){
//                pointsSoFar = points + getNextPoints(allPoints, listOf(adjPoints[2]!!))
//            }

//
//            val prevSum = points.sumOf { it.height }
//            val sumSoFar = pointsSoFar.sumOf { it.height }

            return pointsSoFar

        }

        return emptyList()

    }

    override fun call(): Int {

        var out = 0

        val points = input.map {
            it.split("").filter { it.isNotBlank() }.map { it.toInt() }
        }

        val routes = mutableListOf<Route>()
        for (y in points.indices) {
            for (x in points[y].indices){

                val adj = findAdj(points, x, y)
                val startNode = Coordinates(x, y, points[y][x])

                adj.forEachIndexed { index, point ->
                    if(point != null){
                        routes.add(Route(startNode, Coordinates(point.x, point.y, point.height)))
                    }
                }
            }
        }

        val start = routes[0].a
        val end = Coordinates(points[0].size-1, points.size-1,points.last().last())

        val result = AlgorithmAStarImpl(routes)
            .findPath(
                begin = start,
                end = end
            )

        val pathString = result.first.joinToString(separator = ", ") { "[${it.x}, ${it.y}]" }

        println("Result:")
        println("  Path: $pathString")
        println("  Cost: ${result.second}")

        return result.second.toInt() - start.height
    }
}