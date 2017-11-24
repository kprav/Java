public class Solution {
  public int longestConsecutive(final List<Integer> a) {
    Set<Integer> set = new HashSet<Integer>();
    for (int n : a) {
      set.add(n);
    }
    int longestSequenceLength = 0;
    for (int n : a) {
      if (!set.contains(n - 1)) {
        int currentNum = n;
        int currentSequenceLength = 1;
        while (set.contains(currentNum + 1)) {
          currentNum = currentNum + 1;
          currentSequenceLength++;
        }
        longestSequenceLength = Math.max(currentSequenceLength, longestSequenceLength);
      }
    }
    return longestSequenceLength;
  }
}
