public class Solution {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int maxProduct(final List<Integer> A) {
        int[] max = new int[A.size()];
        int[] min = new int[A.size()];
     
        max[0] = min[0] = A.get(0);
        int result = A.get(0);
     
        for (int i = 1; i < A.size(); i++) {
            if (A.get(i) > 0) {
                max[i] = Math.max(A.get(i), max[i-1] * A.get(i));
                min[i] = Math.min(A.get(i), min[i-1] * A.get(i));
            } else {
                max[i] = Math.max(A.get(i), min[i-1] * A.get(i));
                min[i] = Math.min(A.get(i), max[i-1] * A.get(i));
            }
            
            result = Math.max(result, max[i]);
        }
        
        return result;
    }
}
