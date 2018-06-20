import java.util.*

//https://leetcode.com/problems/word-ladder-ii/description/

class WordLadderII {
    fun findLadders(beginWord: String, endWord: String, wordList: MutableCollection<String>): List<List<String>> {
        if (!wordList.contains(endWord))
            return emptyList()
        val list = arrayListOf<List<String>>()
        val queue = LinkedList<LinkedList<String>>()
        queue.add(LinkedList(listOf(beginWord)))
        while (!queue.isEmpty()) {
            val currentList = queue.pop()
            val lastElement = currentList.last
            val different = different(endWord, lastElement)
            if (different < 2) {
                list.add(LinkedList(currentList).also { l -> l.add(endWord) })
            }
            val childOf = childOf(lastElement, wordList)
            wordList.removeAll(childOf)
            queue.addAll(childOf.filter { it != endWord }.map { LinkedList(currentList).also { l -> l.add(it) } })

        }
        return list
    }

    private fun different(word1: String, word2: String) = word1.zip(word2).fold(word1.length) { acc, pair -> acc - (pair.first == pair.second).toInt() }

    private fun childOf(word: String, wordList: MutableCollection<String>) = wordList.filter { different(word, it) < 2 }

}

fun Boolean.toInt() = if (this) 1 else 0

fun main(args: Array<String>) {
    println(WordLadderII().findLadders("hit", "cog", mutableListOf("hot", "dot", "dog", "lot", "log")))
}