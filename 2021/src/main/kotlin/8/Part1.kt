package `7`

import java.io.File
import java.util.concurrent.Callable
import kotlin.math.abs

fun main() {

    println(File("").absolutePath)
    val input = File("2021/src/main/kotlin/7/input.txt").readLines()

    println(Part1(input).call())

}

class Part1(private val input: List<String>) : Callable<Int> {
    override fun call(): Int {

        val crabs = input[0].split(',').map { it.toInt() }

        var minFuel = Int.MAX_VALUE

        var fuelCost = 0

        for(i in crabs.indices){
            fuelCost = 0
            for(j in crabs.indices){
                fuelCost += abs(crabs[i] - crabs[j])
            }
            if(fuelCost < minFuel){
                minFuel = fuelCost
            }
        }

        return minFuel
    }
}