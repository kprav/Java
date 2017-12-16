public class Solution {
    private int binarySearch(ArrayList<Integer> list, int start, int end, int num) {
        if (start > end) {
            return start;
        }
        int mid = start + (end - start) / 2;
        int listNum = list.get(mid);
        if (num == listNum) {
            return mid;
        } else if (num < listNum) {
            return binarySearch(list, start, mid -1, num);
        } else {
            return binarySearch(list, mid + 1, end, num);
        }
    }
    
    public int searchInsert(ArrayList<Integer> a, int b) {
        if (a == null || a.size() == 0) {
            return 0;
        }
        return binarySearch(a, 0, a.size() - 1, b);
    }
}
