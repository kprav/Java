public class Solution {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int longestSubsequenceLength(final List<Integer> A) {
        if (A == null || A.size() == 0) {
            return 0;
        }
        
        int i, j;
        int[] longestIncSubsequence = new int[A.size()];
        int[] longestDecSubsequence = new int[A.size()];
        
        for (i = 0; i < A.size(); i++) {
            longestIncSubsequence[i] = 1;
            longestDecSubsequence[i] = 1;
        }
            
        for (i = 1; i < A.size(); i++) {
            for (j = 0; j < i; j++) {
                if (A.get(i)> A.get(j) && longestIncSubsequence[i] < longestIncSubsequence[j] + 1) {
                    longestIncSubsequence[i] = longestIncSubsequence[j] + 1;
                }
            }
        }
                    
        for (i = A.size() - 2; i >= 0; i--) {
            for (j = A.size()-1; j > i; j--) {
                if (A.get(i) > A.get(j) && longestDecSubsequence[i] < longestDecSubsequence[j] + 1) {
                    longestDecSubsequence[i] = longestDecSubsequence[j] + 1;
                }
            }
        }
 
        int maxLength = longestIncSubsequence[0] + longestDecSubsequence[0] - 1;
        
        for (i = 1; i < A.size(); i++) {
            if (longestIncSubsequence[i] + longestDecSubsequence[i] - 1 > maxLength) {
                maxLength = longestIncSubsequence[i] + longestDecSubsequence[i] - 1;
            }
        }
 
        return maxLength;
    }
}
