public class Solution {
    public int minPathSum(ArrayList<ArrayList<Integer>> A) {
        if (A == null || A.size() == 0)
            return 0;
     
        int m = A.size();
        int n = A.get(0).size();
     
        int[][] pathSum = new int[m][n];
        pathSum[0][0] = A.get(0).get(0);    
     
        // initialize top row
        for (int i = 1; i < n; i++) {
            pathSum[0][i] = pathSum[0][i-1] + A.get(0).get(i);
        }
     
        // initialize left column
        for (int j = 1; j < m; j++) {
            pathSum[j][0] = pathSum[j-1][0] + A.get(j).get(0);
        }
     
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (pathSum[i-1][j] > pathSum[i][j-1]) {
                    pathSum[i][j] = pathSum[i][j-1] + A.get(i).get(j);
                } else {
                    pathSum[i][j] = pathSum[i-1][j] + A.get(i).get(j);
                }
            }
        }
     
        return pathSum[m-1][n-1];
    }
}
