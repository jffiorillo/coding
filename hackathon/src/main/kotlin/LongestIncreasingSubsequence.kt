import java.io.Serializable

fun main(args: Array<String>) {
    val input = arrayOf(
            Pair("ABCD", 4),
            Pair(intArrayOf(0, 1, -1, 4, 5, 8), 5)
    )
    for ((i, exp) in input) {
        println("$exp == " + longestIncreasingSubsequence(i))
    }
}

fun longestIncreasingSubsequence(i: Serializable) = if (i is String) longestIncreasingSubsequence(i) else if (i is IntArray) longestIncreasingSubsequence(i) else -1
private fun longestIncreasingSubsequence(input: String) = longestIncreasingSubsequence(input.map { it.toInt() }.toIntArray())
private fun longestIncreasingSubsequence(input: IntArray): Int {
    val lookup = Array(input.size) { 1 }

    for (j in 1 until input.size) {
        for (i in 0 until j) {
            if (input[i] < input[j] && lookup[j] < lookup[i] + 1) {
                lookup[j] = lookup[i] + 1
            }
        }
    }
    return lookup.reduce { acc, i -> if (acc < i) i else acc }
}