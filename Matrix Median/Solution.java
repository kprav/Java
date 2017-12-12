public class Solution {
    public int findMedian(ArrayList<ArrayList<Integer>> A) {
        int posOfMedian = (A.size() * A.get(0).size() + 1) / 2;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            ArrayList<Integer> list = A.get(i);
            if (list.get(0) < min) {
                min = list.get(0);
            }
            if (list.get(list.size() - 1) > max) {
                max = list.get(list.size() - 1);
            }
        }
        while (min < max) {
            int mid = min + (max - min) / 2;
            int numElementsBeforeMid = 0;
            int totalNumElementsBeforeMid = 0;
            for (int i = 0; i < A.size(); i++) {
                int indexOfMid = Collections.binarySearch(A.get(i), mid);
                if (indexOfMid < 0) {
                    numElementsBeforeMid = Math.abs(indexOfMid) - 1;
                } else {
                    while (indexOfMid < A.get(i).size() && A.get(i).get(indexOfMid) == mid) {
                        indexOfMid++;
                    }
                    numElementsBeforeMid = indexOfMid;
                }
                totalNumElementsBeforeMid += numElementsBeforeMid;
            }
            if (totalNumElementsBeforeMid < posOfMedian) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        return min;
    }
}
