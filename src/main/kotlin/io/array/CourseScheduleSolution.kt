package io.array

import java.util.*


// https://leetcode.com/problems/course-schedule-iii/solution/
class CourseScheduleSolution {

  fun execute(courses: Array<IntArray>): Int {

    fun tryToChangePreviousLongerCourse(courses: Array<IntArray>, index: Int, duration: Int): Int? {
      var longestCourse = index
      (0 until index).forEach { j ->
        if (courses[j].duration() > courses[longestCourse].duration())
          longestCourse = j
      }
      return courses[longestCourse].duration().let { longestDuration ->
        courses[longestCourse][0] = -1
        if (longestDuration > duration) (duration - longestDuration) else null
      }
    }

    courses.sortBy { it.endDay() }
    var count = 0
    var time = 0
    courses.map { it.duration() to it.endDay() }.forEachIndexed { index, (duration, endDay) ->
      if (duration + time <= endDay) {
        count++
        time += duration
      } else {
        tryToChangePreviousLongerCourse(courses, index, duration)?.let { time += it }
      }
    }
    return count
  }

  private fun IntArray.duration() = this.first()
  private fun IntArray.endDay() = this[1]


  fun executeWithPriorityQueue(courses: Array<IntArray>): Int {
    courses.sortBy { it.endDay() }
    val queue = PriorityQueue<Int>(Comparator { a, b -> b - a })
    var time = 0
    courses.map { course ->
      if (time + course.duration() <= course.endDay()) {
        queue.offer(course.duration())
        time += course.duration()
      } else if (queue.isNotEmpty() && queue.peek() > course.duration()) {
        time += course.duration() - queue.poll()
        queue.offer(course.duration())
      }
    }
    return queue.size
  }
}