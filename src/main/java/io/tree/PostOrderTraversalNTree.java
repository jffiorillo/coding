package io.tree;

import io.queue.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/explore/learn/card/n-ary-tree/130/traversal/926/
class PostOrderTraversalNTree {

  List<Integer> executeIterative(Node root) {
    LinkedList<Node> stack = new LinkedList<>();
    LinkedList<Integer> inverseStack = new LinkedList<>();
    stack.add(root);
    while (!stack.isEmpty()) {
      final Node current = stack.pop();
      if (current != null) {
        for (Node child : current.neighbors) {
          stack.push(child);
        }
        inverseStack.add(current.val);
      }
    }
    Collections.reverse(inverseStack);
    return inverseStack;
  }

  List<Integer> executeRecursive(Node root) {
    if (root == null) {
      return Collections.emptyList();
    }
    List<Integer> result = new ArrayList<>();
    for (Node child : root.neighbors) {
      result.addAll(executeRecursive(child));
    }
    result.add(root.val);
    return result;
  }

  public static void main(String[] args) {
    final PostOrderTraversalNTree postOrderTraversalNTree = new PostOrderTraversalNTree();

    final List<Integer> output = postOrderTraversalNTree.executeIterative(PreOrderTraversalNTree.INPUT);

    System.out.println("output = " + output);
  }
}
