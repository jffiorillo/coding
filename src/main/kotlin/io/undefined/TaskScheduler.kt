package io.undefined


// https://leetcode.com/problems/task-scheduler/
class TaskScheduler {

  // https://leetcode.com/problems/task-scheduler/solution/
  fun execute(input: CharArray, interval: Int): Int {
    val counts = IntArray(26)
    input.forEach { counts[it - 'A']++ }
    counts.sortDescending()
    val maxSlots = counts.first() - 1
    var idleSlots = maxSlots * interval
    for (index in 1 until 26) {
      idleSlots -= minOf(maxSlots, counts[index])
    }
    return maxOf(idleSlots,0) + input.size
  }
}