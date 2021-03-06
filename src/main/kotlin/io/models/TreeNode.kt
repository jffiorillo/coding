package io.models

data class TreeNode(var `val`: Int, var left: TreeNode? = null, var right: TreeNode? = null)

typealias Node = TreeNode