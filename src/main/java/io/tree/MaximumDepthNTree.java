package io.tree;

import io.queue.Node;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.util.Pair;

// https://leetcode.com/explore/learn/card/n-ary-tree/131/recursion/919/
class MaximumDepthNTree {

  public int executeDFS(Node root) {
    if (root == null) {
      return 0;
    }
    LinkedList<Pair<Node, Integer>> stack = new LinkedList<>(Collections.singletonList(new Pair<>(root, 1)));
    int deep = 0;
    while (!stack.isEmpty()) {
      final Pair<Node, Integer> current = stack.pop();
      final Integer value = current.getValue();
      if (value > deep) {
        deep = value;
      }
      for (Node child : current.getKey().neighbors) {
        stack.add(new Pair<>(child, value + 1));
      }
    }
    return deep;
  }

  public int executeBFS(Node root) {
    int deep = 0;
    if (root != null) {
      List<Node> stack = Collections.singletonList(root);
      while (stack != null) {
        List<Node> newIteration = stack.stream().flatMap(x -> x.neighbors.stream()).collect(Collectors.toList());
        stack = (newIteration.isEmpty()) ? null : newIteration;
        deep++;
      }
    }
    return deep;
  }
}
