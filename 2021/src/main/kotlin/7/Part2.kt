package `7`

import java.math.BigInteger
import java.util.concurrent.Callable
import kotlin.math.abs


class Part2(private val input: List<String>) : Callable<Int> {

    fun sumNums(end: Int): BigInteger {
        var factorial = BigInteger.ZERO
        for (i in 0 .. end) {
            factorial = factorial.add(BigInteger.valueOf(i.toLong()))
        }

        return factorial
    }

    override fun call(): Int {

        val crabs = input[0].split(',').map { it.toInt() }

        var minFuel = Int.MAX_VALUE

        var fuelCost: BigInteger

        for(i in 0 ..  crabs.maxOf { it }){
            fuelCost = BigInteger.ZERO
            for(j in crabs.indices){
                val distance = abs(i - crabs[j])
                fuelCost += sumNums(distance)
            }
            if(fuelCost.toLong() < minFuel){
                minFuel = fuelCost.toInt()
            }
        }

        return minFuel
    }
}