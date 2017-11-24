public class Solution {
  public int kthsmallest(final List<Integer> a, int k) {
    int kThSmallest = 0;
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(a.size());
    for (Integer i : a) {
      minHeap.add(i);
    }
    for (int i = 0; i < k; i++) {
      kThSmallest = minHeap.poll();
    }
    return kThSmallest;
  }
}
