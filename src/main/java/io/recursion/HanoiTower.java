package io.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HanoiTower {

  public static class Tower {
    @Override public String toString() {
      return "Tower{" +
          "numbers=" + numbers +
          ", index='" + index + '\'' +
          '}';
    }

    private final Stack<Integer> numbers = new Stack<>();
    final String index;

    Tower(String index) {
      this.index = index;
    }

    void moveToTop(Tower destination, List<String> result) {
      int value = numbers.pop();
      destination.add(value);
      result.add("from " + index + " to " + destination.index);
    }

    void add(int value) {
      if (!numbers.isEmpty() && numbers.peek() <= value) {
        throw new IllegalArgumentException("Can't add " + value + " to Tower " + index + " with peek " + numbers.peek());
      } else {
        numbers.push(value);
      }
    }

    void moveDisks(int value,
        Tower buffer,
        Tower destination,
        List<String> result) {
      if (value < 1) {
        return;
      }

      moveDisks(value - 1, destination,buffer, result);
      moveToTop(destination, result);
      buffer.moveDisks(value - 1, this, destination, result);
    }
  }

  /**
   * @param n: the number of disks
   * @return: the order of moves
   */
  public List<String> towerOfHanoi(int n) {
    Tower[] towers = { new Tower("A"), new Tower("B"), new Tower("C") };

    List<String> result = new ArrayList<>();

    for (int i = n; i > 0; i--) {
      towers[0].add(i);
    }
    towers[0].moveDisks(n, towers[1], towers[2], result);

    System.out.println(towers[0].numbers);
    System.out.println(towers[1].numbers);
    System.out.println(towers[2].numbers);
    return result;
  }

  public static void main(String[] args) {
    final List<String> results = new HanoiTower().towerOfHanoi(3);
    for (String i : results) {
      System.out.println(i);
    }
  }
}
