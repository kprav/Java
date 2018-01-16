public class Solution {
    
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    
    private void swap(ArrayList<Integer> A, int i, int j) {
        int iVal = A.get(i);
        int jVal = A.get(j);
        A.set(i, jVal);
        A.set(j, iVal);
    }
    
    private void permuteHelper(ArrayList<Integer> A, int i, int n) {
        if (i == n) {
            ArrayList<Integer> perm = new ArrayList<>();
            for (Integer integer : A) {
                perm.add(integer.intValue());
            }
            result.add(perm);
        } else {
            for (int j = i; j <= n; j++) {
                swap(A, i, j);
                permuteHelper(A, i + 1, n);
                swap(A, i, j);
            }
        }
    }
    
    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
        permuteHelper(A, 0, A.size() - 1);
        return result;
    }
}
