package `2`

import java.util.concurrent.Callable

class Part1(private val input: List<String>) : Callable<Int> {
    override fun call(): Int {

        val commands = input.map {
            val command = it.split(" ")
            Pair(command[0], command[1].toInt())
        }

        var forward = 0
        var depth = 0
        commands.forEach {
            when (it.first) {
                "up" -> depth -= it.second
                "down" -> depth += it.second
                "forward" -> {
                    forward += it.second
                }
            }
        }

        return depth * forward

    }


}