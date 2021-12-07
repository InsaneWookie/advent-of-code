package `4`

import java.util.concurrent.Callable


class Part2(private val input: List<String>) : Callable<Int> {
    override fun call(): Int {

        val draws = input[0].split(',').map { it.toInt() }
        val boards = arrayListOf<Board>()

        for (i in 1 until input.size) {
            val line = input[i]
            if (line != "") {
                boards.last().rows.add(line.split(" ").filter { it != "" }
                    .map { it.toInt() } as java.util.ArrayList<Int>)

            } else {
                boards.add(Board())
            }

        }

        //find the row or col that matches bingo numbers
        val foundBoards = ArrayList<Board>()
        var lastMatchingDraw = 0
        for (i in 4 until draws.size) {
            val set = draws.subList(0, i)

            val matching = boards.filter { board ->
                board.rowCols().find { rowCol ->
                    set.containsAll(rowCol)
                } != null && !foundBoards.contains(board)

            }
            if (matching.isNotEmpty()) {

                foundBoards.addAll(matching)
                println(matching)
                lastMatchingDraw = i-1
                println(draws[i])
            }


        }


        val lastBoard = foundBoards.last()
        val nums = lastBoard.rowCols().flatten().distinct().filter {
            !draws.subList(0, lastMatchingDraw+1).contains(it)
        }
        println(nums)
        return nums.sum() * draws[lastMatchingDraw]

    }
}