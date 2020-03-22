package io.tree;

import io.queue.Node;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/explore/learn/card/n-ary-tree/130/traversal/925/
class PreOrderTraversalNTree {

  public static final Node INPUT = new Node(1,
      new ArrayList<>(
          Arrays.asList(
              new Node(3, new ArrayList<>(Arrays.asList(new Node(5), new Node(6)))),
              new Node(2),
              new Node((4))
          )
      )
  );

  public List<Integer> executeIterative(Node root) {
    LinkedList<Node> stack = new LinkedList<>();
    List<Integer> result = new ArrayList<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      final Node current = stack.pop();
      if (current != null) {
        result.add(current.val);
        stack.addAll(0, current.neighbors);
      }
    }
    return result;
  }

  public List<Integer> executeRecursive(Node root) {
    if (root == null) {
      return Collections.emptyList();
    }
    List<Integer> result = new ArrayList<>();
    result.add(root.val);
    for (Node child : root.neighbors) {
      result.addAll(executeRecursive(child));
    }
    return result;
  }

  public static void main(String[] args) {
    final PreOrderTraversalNTree preOrderTraversalNTree = new PreOrderTraversalNTree();
    final List<Integer> output = preOrderTraversalNTree.executeRecursive(INPUT);
    System.out.println("output = " + output);
  }
}
