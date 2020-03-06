package io;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/995/
// BinaryTreeSerializer
public class Codec {

  private final String separator = "&";
  private final String itemSeparator = ",";
  private final String nullValue = "^";

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    Stack<List<TreeNode>> stack = new Stack<>();
    List<List<Integer>> result = new ArrayList<>();
    List<TreeNode> ini = new ArrayList<>();
    ini.add(root);
    stack.push(ini);
    while (!stack.empty()) {
      List<TreeNode> news = new ArrayList<>();
      boolean anyValue = false;
      List<Integer> values = new ArrayList<>();
      for (TreeNode current : stack.pop()) {
        values.add(current != null ? current.val : null);
        news.add(current == null ? null : current.left);
        news.add(current == null ? null : current.right);
        anyValue = anyValue || (current != null && (current.left != null || current.right != null));
      }
      if (!values.isEmpty()) {
        result.add(values);
      }
      if (anyValue) {
        stack.push(news);
      }
    }
    StringBuilder value = new StringBuilder();
    for (List<Integer> row : result) {
      for (Integer item : row) {
        value.append(item != null ? item.toString() : nullValue).append(itemSeparator);
      }
      value = new StringBuilder(value.substring(0, value.length() - 1) + separator);
    }
    return value.toString();
  }

  // Decodes your encoded data to tree.
  @SuppressWarnings("UnusedReturnValue") public TreeNode deserialize(String data) {
    if (data == null || data.isEmpty()) {
      return null;
    }
    String[] matrix = data.split(separator);
    List<TreeNode> values = null;
    for (int i = matrix.length - 1; i >= 0; i--) {
      values = transformCharacters(matrix[i], values != null ? values.iterator() : null);
    }
    return values != null && !values.isEmpty() ? values.get(0) : null;
  }

  private List<TreeNode> transformCharacters(String value, Iterator<TreeNode> children) {
    List<TreeNode> result = new ArrayList<>();
    for (String item : value.split(itemSeparator)) {
      if (nullValue.equals(item)) {
        result.add(null);
        if (children != null) {
          children.next();
          children.next();
        }
      } else {
        TreeNode treeNode = new TreeNode(Integer.parseInt(item));
        if (children != null) {
          treeNode.left = children.next();
          treeNode.right = children.next();
        }
        result.add(treeNode);
      }
    }
    return result;
  }

  public static void main(String[] args) {
    Codec codec = new Codec();
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(4);
    root.right.right = new TreeNode(5);
    codec.deserialize(codec.serialize(root));
  }
}