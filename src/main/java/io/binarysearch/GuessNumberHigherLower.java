package io.binarysearch;

//https://leetcode.com/explore/learn/card/binary-search/125/template-i/951/
public class GuessNumberHigherLower implements GuessGame {

  public int guessNumber(int n) {
    int begin = 1;
    int end = n == Integer.MAX_VALUE ? n : n + 1;
    while (begin <= end) {
      int pivot = ((end-begin)/2) + begin;
      int guess = guess(pivot);
      if (guess == 0) {
        return pivot;
      } else if (guess < 0) {
        end = pivot - 1;
      } else {
        begin = pivot + 1;
      }
    }
    return -1;
  }

  @Override public int guess(int num) {
    return -Integer.compare(num, 6);
  }

  public static void main(String[] args) {
    GuessNumberHigherLower g = new GuessNumberHigherLower();
    int n = g.guessNumber(10);
    System.out.println(n);
  }
}

interface GuessGame {
  int guess(int num);
}