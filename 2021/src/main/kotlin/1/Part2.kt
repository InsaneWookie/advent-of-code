package `1`

import java.util.concurrent.Callable


class Part2(private val input: List<Int>) : Callable<Int> {
    override fun call(): Int {
        val threeSum = ArrayList<Int>()

        for (i in 2 until input.size) {
            val sum = input[i - 2] + input[i - 1] + input[i]
            threeSum.add(sum)
        }

        var prev = 999999
        var count = 0
        threeSum.forEach {
            if (it > prev) {
                count++
            }
            prev = it
        }

        return count
    }

}