public class Solution {
    private int findPivotPoint(List<Integer> a, int low, int high) {
        if (high < low) {
            return -1;
        }
        if (high == low) {
            return low;
        }
        int mid = low + (high - low) / 2;
        if (mid < high && a.get(mid) > a.get(mid + 1)) {
            return mid;
        }
        if (mid > low && a.get(mid) < a.get(mid - 1)) {
            return mid;
        }
        if (a.get(low) >= a.get(mid)) {
            return findPivotPoint(a, low, mid - 1);
        }
        return findPivotPoint(a, mid + 1, high);
    }
    
    private int binarySearch(List<Integer> a, int low, int high, int key) {
        if (high < low) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (key == a.get(mid)) {
            return mid;
        }
        if (key > a.get(mid)) {
            return binarySearch(a, mid + 1, high, key);
        }
        return binarySearch(a, low, mid - 1, key);
    }
      
    // DO NOT MODIFY THE LIST
    public int search(final List<Integer> a, int b) {
        int pivot = findPivotPoint(a, 0, a.size() - 1);
        if (pivot == -1) {
            return binarySearch(a, 0, a.size() - 1, b);
        }
        if (a.get(pivot) == b) {
            return pivot;
        }
        if (a.get(0) <= b) {
            return binarySearch(a, 0, pivot - 1, b);
        }
        return binarySearch(a, pivot + 1, a.size() - 1, b);
    }
}
