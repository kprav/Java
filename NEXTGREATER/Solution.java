public class Solution {
  public ArrayList<Integer> nextGreater(ArrayList<Integer> a) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    for (int i = 0 ; i < a.size(); i++) {
      int nextLargest = Integer.MAX_VALUE;
      int currentNum = a.get(i);
      for (int j = i + 1; j < a.size(); j++) {
        int nextNum = a.get(j);
        if (nextNum > currentNum) {
          nextLargest = nextNum;
          break;
        }
      }
      if (nextLargest == Integer.MAX_VALUE) {
        nextLargest = -1;
      }
      result.add(nextLargest);
    }
    return result;
  }
}
