public class Solution {
    private int binarySearch(ArrayList<Integer> list, int start, int end, int num) {
        if (start > end) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        int listNum = list.get(mid);
        if (num == listNum) {
            return 1;
        } else if (num < listNum) {
            return binarySearch(list, start, mid - 1, num);
        } else {
            return binarySearch(list, mid + 1, end, num);
        }
    }
    
    public int searchMatrix(ArrayList<ArrayList<Integer>> a, int b) {
        if (a == null || a.size() == 0) {
            return 0;
        }
        if (a.size() == 1 && a.get(0) == null) {
            return 0;
        }
        for (ArrayList<Integer> list : a) {
            if (b <= list.get(list.size() - 1)) {
                return binarySearch(list, 0, list.size() - 1, b);
            }
        }
        return 0;
    }
}
