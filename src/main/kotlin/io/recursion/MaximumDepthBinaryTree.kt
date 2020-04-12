package io.recursion

import io.models.TreeNode
import kotlin.math.max

//https://leetcode.com/explore/featured/card/recursion-i/256/complexity-analysis/2375/
class MaximumDepthBinaryTree {

  fun execute(tree: TreeNode?): Int = when(tree){
    null -> 0
    else -> max(execute(tree.left),execute(tree.right)) + 1
  }
}