public class Solution {
    public int solve(ArrayList<ArrayList<Integer>> matrix) {
        Integer[][] input = new Integer[matrix.size()][matrix.get(0).size()];
        Integer[][] count = new Integer[matrix.size()][matrix.get(0).size()];
        int i = 0, j = 0;
        for (ArrayList<Integer> list : matrix) {
            j = 0;
            for (Integer l : list) {
                input[i][j++] = l;
            }
            i++;
        }
        for (j = 0; j < input[0].length; j++) {
            count[0][j] = input[0][j];
            for (i = 1; i < input.length; i++) {
                count[i][j] = (input[i][j] == 0) ? 0 : count[i - 1][j] + 1;
            }
        }
        int maxArea = Integer.MIN_VALUE;
        for (i = 0; i < count.length; i++) {
            Arrays.sort(count[i], Collections.reverseOrder());
            for (j = 0; j < count[0].length; j++) {
                int area = (j + 1) * count[i][j];
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }
}
