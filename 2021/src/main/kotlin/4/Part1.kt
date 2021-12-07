package `4`

import java.util.concurrent.Callable

class Board(
    var rows: ArrayList<ArrayList<Int>> = arrayListOf(),
    var cols: ArrayList<ArrayList<Int>> = arrayListOf()
) {
    override fun toString(): String {
        return rows.toString() + "\n" + cols.toString()
    }

    public fun rowCols() = rows + getAllColumns()

    fun getAllColumns(): ArrayList<ArrayList<Int>> {

        val c = ArrayList<ArrayList<Int>>()
        for(i in 0 until rows[0].size){
            c.add(getColumn(rows, i).toCollection(ArrayList()))
        }
        return c
    }

    fun getColumn(matrix: ArrayList<ArrayList<Int>>, col: Int) = IntArray(matrix.size) { matrix[it][col] }
}

class Part1(private val input: List<String>) : Callable<Int> {
    override fun call(): Int {

        val draws = input[0].split(',').map { it.toInt() }
        val boards = arrayListOf<Board>()

        val rows = ArrayList<List<Int>>()
        val cols = ArrayList<List<Int>>()

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
        for (i in 4 until draws.size) {
            val set = draws.subList(0, i)

            val matching = boards.find { board ->
                board.rowCols().find {
                    set.containsAll(it)
                } != null

            }

            if (matching != null) {
                val nums = matching.rowCols().flatten().distinct().filter { !set.contains(it) }
                println(nums)
                return nums.sum() * draws[i-1]
            }
        }
        return 0
    }
}