package `6`

import java.math.BigInteger
import java.util.concurrent.Callable


class Part2(private val input: List<String>) : Callable<BigInteger> {
    override fun call(): BigInteger {
        val fish = input[0].split(',').map { it.toInt() }

        //convert to counts
        val f = IntArray(9) { 0 }.toList().map { BigInteger.valueOf(it.toLong()) }.toTypedArray()
        fish.forEach {
            f[it]++
        }

        for (i in 1..256) {
            val zeros = f[0]
            f[0] = BigInteger.ZERO

            //minus 1 off everything
            for (x in 1 until f.size){
                f[x-1] = f[x]
            }

            f[8] = BigInteger.ZERO


            if(zeros > BigInteger.ZERO){
                f[6] += zeros
                f[8] = zeros
            }
            
        }
        return f.sumOf { it }
    }
}