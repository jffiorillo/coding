import java.util.*

fun wildcardPatternMatching(string: String, regex1: String, debug: Boolean = false): String {
    if (regex1.isEmpty() || string.isEmpty())
        return (regex1.isEmpty() && string.isEmpty()).toString()
    val regex = regex1.cleanRegex()
    val lookup = Array(string.length + 1) { BooleanArray(regex.length + 1) }
    if (debug) println(lookup.map { it.map { it.toString() }.toString() + "\n" })
    lookup[0][0] = true
    lookup[0][1] = regex.first() == '*'
    for (i in 1 until string.length + 1) {
        for (j in 1 until regex.length + 1) {
            if (regex[j - 1] == '*') {
                lookup[i][j] = lookup[i - 1][j] || lookup[i][j - 1]
            } else if (regex[j - 1] == '?' || string[i - 1] == regex[j - 1]) {
                lookup[i][j] = lookup[i - 1][j - 1]
            }
        }
        if (debug) println(i.toString() + string[i - 1] + (lookup[i].map { it.toString() }).toString() + "\n")
    }

    return lookup.last().last().toString()
}


private fun String.cleanRegex() = this.asIterable().fold("") { acc, c -> if (c != '*' || acc.isEmpty() || acc.last() != '*') acc + c else acc }

fun main(args: Array<String>) {
    val inputs = listOf(
            Triple("1ab23444", "1*2***3*4*4***4", "true == "),
            Triple("baaabab", "*****ba*****ab", "true == "),
            Triple("baaabab", "*****aba*****ab*", "false == "),
            Triple("baaabab", "*****b*a*****ab*", "true == "),
            Triple("12", "1*", "true == ")
    )
    for ((str, pattern, a) in inputs) {
        println("string: $str regex: $pattern " + a + wildcardPatternMatching(str, pattern))
    }
}