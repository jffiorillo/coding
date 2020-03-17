package io.binarysearch;

// https://leetcode.com/explore/learn/card/binary-search/126/template-ii/947/
public class BadVersion {
  public int firstBadVersion(int n) {
    int first = 1, last = n;

    while(first < last){
      int mid = first + (last-first)/2;
      if(isBadVersion(mid)) {
        last = mid;
      } else {
        first = mid+1;
      }
    }

    return first;
  }

  public boolean isBadVersion(int version) {
    return version > 3;
  }

  public static void main(String[] args) {
    final BadVersion badVersion = new BadVersion();
    final int firstBadVersion = badVersion.firstBadVersion(5);
    System.out.println("firstBadVersion = " + firstBadVersion);
  }
}
