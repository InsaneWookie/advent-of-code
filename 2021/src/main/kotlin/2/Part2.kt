package `2`

import java.util.concurrent.Callable

class Part2(private val input: List<String>) : Callable<Int> {

    override fun call(): Int {

        val commands = input.map {
            val command = it.split(" ")
            Pair(command[0], command[1].toInt())
        }

        var aim = 0
        var forward = 0
        var depth = 0
        commands.forEach {
            when (it.first) {
                "up" -> aim -= it.second
                "down" -> aim += it.second
                "forward" -> {
                    forward += it.second
                    depth += it.second * aim
                }
            }
        }

        return depth * forward

    }


}