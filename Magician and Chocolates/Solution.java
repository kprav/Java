public class Solution {
    public int nchoc(int A, ArrayList<Integer> B) {
        int mod = ((int) Math.pow(10, 9)) + 7;
        long totalChocolates = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (Integer i : B) {
            maxHeap.add(i);
        }
        for (int i = 0; i < A; i++) {
            Integer numChocolates = maxHeap.poll();
            totalChocolates += numChocolates;
            numChocolates /= 2;
            maxHeap.offer(numChocolates);
        }
        return ((int) (totalChocolates % mod));
    }
}
