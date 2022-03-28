package io.queue;

class CloneGraphJava {
  public Node cloneGraph(Node node) {
    return node == null ? null : clone(node, new Node[100]);
  }

  public Node clone(Node node, Node[] visited) {
    if (visited[node.val - 1] != null) {
      return visited[node.val - 1];
    }
    Node newNode = new Node(node.val);
    visited[newNode.val - 1] = newNode;
    for (Node neighbor : node.neighbors) {
      newNode.neighbors.add(clone(neighbor, visited));
    }
    return newNode;
  }

  public static void main(String[] args) {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);

    node1.neighbors.add(node2);
    node1.neighbors.add(node4);
    node2.neighbors.add(node1);
    node2.neighbors.add(node3);
    node3.neighbors.add(node2);
    node3.neighbors.add(node4);
    node4.neighbors.add(node1);
    node4.neighbors.add(node3);

    final CloneGraphJava cloneGraphJava = new CloneGraphJava();

    final Node newNode = cloneGraphJava.cloneGraph(node1);

    System.out.println();
  }
}
