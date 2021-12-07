package `7`

import java.math.BigInteger
import java.util.concurrent.Callable
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.roundToLong


class Part2(private val input: List<String>) : Callable<Int> {

    fun sumNums(end: Int): BigInteger {
        var factorial = BigInteger.ZERO
        for (i in 0 .. end) {
            factorial = factorial.add(BigInteger.valueOf(i.toLong()))
        }

        return factorial
    }

    /**
     * Formula for summing consecutive integers
     */
    fun sumNumsFast(end: Int): Long {
        return ((end.toDouble()/2) * (1 + end)).roundToLong()
    }

    override fun call(): Int {

        val crabs = input[0].split(',').map { it.toInt() }

        var minFuel = Int.MAX_VALUE

        var fuelCost: Long

        for(i in 0 ..  crabs.maxOf { it }){
            fuelCost = 0
            for(j in crabs.indices){
                val distance = abs(i - crabs[j])
                val b = sumNumsFast(distance)
                fuelCost += b
            }
            if(fuelCost < minFuel){
                minFuel = fuelCost.toInt()
            }
        }

        return minFuel
    }
}