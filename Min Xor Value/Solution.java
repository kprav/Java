public class Solution {
    public int findMinXor(ArrayList<Integer> A) {
        Collections.sort(A);
        Integer[] array = A.toArray(new Integer[A.size()]);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length - 1; i++) {
            min = Math.min(min, array[i] ^ array[i + 1]);
        }
        return min;
    }
}
