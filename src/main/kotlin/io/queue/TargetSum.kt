@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package io.queue

import io.tree.BinaryTree
import java.util.*


private const val maxInputSumValue = 1000
private const val offsetMaxInputSumValue = 2001

class TargetSum {

  fun executeRecursive(nums: IntArray, current: Int, index: Int = 0, sum: Int = 0): Int = when (index) {
    nums.size -> if (current == 0) sum + 1 else sum
    else -> {
      executeRecursive(nums, current + nums[index], index + 1, sum) +
          executeRecursive(nums, current - nums[index], index + 1, sum)
    }
  }

  fun executeRecursiveWithMemory(nums: IntArray, target: Int): Int =
      Array(nums.size) { IntArray(offsetMaxInputSumValue) }.let { memo ->
        for (row in memo) Arrays.fill(row, Int.MIN_VALUE)
        return executeRecursiveWithMemory(nums, 0, target, memo)
      }

  fun executeRecursiveWithMemory(
      nums: IntArray,
      current: Int,
      target: Int,
      memory: Array<IntArray>,
      index: Int = 0,
      sum: Int = 0): Int =
      when {
        index == nums.size -> if (current == target) sum + 1 else sum

        memory[index][maxInputSumValue + current] != Int.MIN_VALUE -> memory[index][maxInputSumValue + current]

        else -> {
          (executeRecursiveWithMemory(nums, (current + nums[index]), target, memory, index + 1, sum) +
              executeRecursiveWithMemory(nums, (current - nums[index]), target, memory, index + 1, sum)).also { value ->
            memory[index][maxInputSumValue + current] = value
          }
        }
      }

  private fun executeIterative(nums: IntArray, target: Int): Int {
    val stack = Stack<Pair<Int, Int>>()
    stack.push(Pair(target, 0))
    var numbers = 0
    while (stack.isNotEmpty()) {
      stack.pop().also { (value, index) ->
        when {
          index < nums.size -> {
            stack.push(Pair(value + nums[index], index + 1))
            stack.push(Pair(value - nums[index], index + 1))
          }
          value == 0 -> {
            numbers += 1
          }
        }
      }
    }
    return numbers
  }

  private fun buildTree(nums: IntArray, target: Int): Pair<BinaryTree<Int>, Int> {
    val stack = Stack<Pair<BinaryTree<Int>, Int>>()
    val root = BinaryTree(target)
    stack.push(Pair(root, 0))
    var numbers = 0
    while (stack.isNotEmpty()) {
      stack.pop().also { (tree, index) ->
        when {
          index < nums.size -> {
            val left = BinaryTree(tree.value + nums[index])
            val right = BinaryTree(tree.value - nums[index])
            tree.left = left
            tree.right = right
            stack.push(Pair(left, index + 1))
            stack.push(Pair(right, index + 1))
          }
          tree.value == 0 -> {
            numbers += 1
          }
        }
      }
    }
    return Pair(root, numbers)
  }
}


fun main() {
  val targetSum = TargetSum()
  listOf(
      intArrayOf(1, 1, 1, 1, 1) to 3,
      intArrayOf(1000) to 1000,
      intArrayOf(0,0,0,0,0,0,0,0,1) to 1
  ).map { (array, target) ->
    println("${targetSum.executeRecursiveWithMemory(array, target)}")
  }
}