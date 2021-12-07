package `3`

import java.util.concurrent.Callable

class Part2(private val input: List<String>) : Callable<Int> {
    override fun call(): Int {

        var oxygen = getOxygen(0, input)
        for (i in 1 until input[0].length){
            oxygen = getOxygen(i, oxygen)
            if(oxygen.size == 1) break
        }


        var co2 = getCO2(0, input)
        for (i in 1 until input[0].length){
            co2 = getCO2(i, co2)
            if(co2.size == 1) break
        }

        return oxygen[0].toInt(2) * co2[0].toInt(2)
    }

    fun getOxygen(bitPos: Int, values: List<String>): ArrayList<String> {

        val (count0, count1) = bitCounts(values, bitPos)

        return if(count0.size == count1.size) count1
        else if(count0.size > count1.size) count0 else count1

    }

    fun getCO2(bitPos: Int, values: List<String>): ArrayList<String> {

        val (count0, count1) = bitCounts(values, bitPos)

        return if(count0.size <= count1.size) count0 else count1

    }

    private fun bitCounts(
        values: List<String>,
        bitPos: Int
    ): Pair<ArrayList<String>, ArrayList<String>> {
        val count0 = arrayListOf<String>()
        val count1 = arrayListOf<String>()
        values.forEach {
            when (it[bitPos]) {
                '0' -> count0.add(it)
                '1' -> count1.add(it)
            }

        }
        return Pair(count0, count1)
    }



}

