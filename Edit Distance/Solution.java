public class Solution {
    public int minDistance(String A, String B) {
        if (A == null && B == null) {
            return 0;
        } else if (A == null || A.length() == 0) {
            return B.length();
        } else if (B == null || B.length() == 0) {
            return A.length();
        }
        int[][] minDist = new int[A.length() + 1][B.length() + 1];
        for (int i = 0; i <= A.length(); i++) {
            for (int j = 0; j <= B.length(); j++) {
                if (i == 0) {
                    minDist[i][j] = j;
                } else if (j == 0) {
                    minDist[i][j] = i;
                } else {
                    minDist[i][j] = A.charAt(i - 1) == B.charAt(j - 1) ? minDist[i - 1][j - 1] :
                    1 + Math.min(minDist[i - 1][j - 1], Math.min(minDist[i - 1][j], minDist[i][j - 1]));
                }
            }
        }
        return minDist[A.length()][B.length()];
    }
}
