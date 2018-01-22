public class Solution {
    
    private class ArrayListComparator implements Comparator<ArrayList<Integer>> {
        @Override
        public int compare(ArrayList<Integer> x, ArrayList<Integer> y) {
            int xLen = x.size();
            int yLen = y.size();
            int len = xLen < yLen ? xLen : yLen;
            for (int i = 0; i < len; i++) {
                if (x.get(i) < y.get(i)) {
                    return -1;
                } else if (x.get(i) > y.get(i)) {
                    return 1;
                }
            }
            if (xLen == yLen) {
                return 0;
            } else if (xLen < yLen) {
                return -1;
            } else {
                return 1;
            }
        }
    }
    
    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Collections.sort(A);
        int size = A.size();
        for (int i = 0 ; i < 1 << size; i++) {
            ArrayList<Integer> res = new ArrayList<>();
            result.add(res);
            for (int j = 0; j < size; j++) {
                if ((i & (1 << j)) > 0) {
                    res.add(A.get(j));
                }
            }
        }
        Collections.sort(result, new ArrayListComparator());
        return result;
    }
}
