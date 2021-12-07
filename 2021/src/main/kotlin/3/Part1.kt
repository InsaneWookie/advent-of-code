package `3`

import java.util.concurrent.Callable

class Part1(private val input: List<String>) : Callable<Int> {
    override fun call(): Int {

        val counts = IntArray(input[0].length)
        input.forEach {
            it.forEachIndexed { index, s ->
                counts[index] += s.toString().toInt()
            }
        }

        val bin = counts.map {
            if (it > input.size / 2) 1 else 0
        }

        val invertBin = bin.map {
            if (it == 1) 0 else 1
        }.joinToString("").toInt(2)

        return bin.joinToString("").toInt(2) * invertBin
    }
}