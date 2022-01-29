package `8`

import java.math.BigInteger
import java.util.concurrent.Callable
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.roundToLong


class Part2(private val input: List<String>) : Callable<Int> {



    override fun call(): Int {

        val out = 0
        input.forEach { s ->

            val numbers = s.split(" | ")

            val a = numbers[0].split(" ")
            val one = a.find { it.length == 2 }
            val seven = a.find { it.length == 3 }
            val four = a.find { it.length == 4 }
            val eight = a.find { it.length == 7 }


        }

        return out

    }
}