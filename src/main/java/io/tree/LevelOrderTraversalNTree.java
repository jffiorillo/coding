package io.tree;

import io.queue.Node;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/explore/learn/card/n-ary-tree/130/traversal/915/
class LevelOrderTraversalNTree {

  public List<List<Integer>> executeIterative(Node root) {
    if (root == null) {
      return Collections.emptyList();
    }
    List<Node> stack = Collections.singletonList(root);
    List<List<Integer>> result = new ArrayList<>();
    while (stack != null) {
      result.add(stack.stream().map(x -> x.val).collect(Collectors.toList()));
      final List<Node> newIteration = stack.stream().flatMap(x -> x.neighbors.stream()).collect(Collectors.toList());
      stack = (newIteration.isEmpty()) ? null : newIteration;
    }
    return result;
  }

  public List<List<Integer>> executeRecursive(List<Node> root, List<List<Integer>> result) {
    if (root == null || root.isEmpty()) {
      return result;
    }
    result.add(root.stream().map(x -> x.val).collect(Collectors.toList()));
    return executeRecursive(root.stream().flatMap(x -> x.neighbors.stream()).collect(Collectors.toList()), result);
  }

  public static void main(String[] args) {

    final LevelOrderTraversalNTree levelOrderTraversalNTree = new LevelOrderTraversalNTree();
    final List<List<Integer>> output = levelOrderTraversalNTree.executeRecursive(Arrays.asList(PreOrderTraversalNTree.INPUT), new ArrayList<>());
    System.out.println("output = " + output);
  }
}
