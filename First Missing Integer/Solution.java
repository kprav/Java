public class Solution {
    
    private int seggregatePosAndNeg(ArrayList<Integer> a) {
        int j = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).intValue() <= 0) {
                int temp = a.get(i).intValue();
                a.set(i, a.get(j).intValue());
                a.set(j, temp);
                j++;
            }
        }
        return j;
    }
    
    private int findFirstMissingPositive(ArrayList<Integer> a, int start, int end) {
        for (int i = start; i < end; i++) {
            if (Math.abs(a.get(i)) - 1 + start < end && a.get(Math.abs(a.get(i)) - 1 + start) > 0) {
                a.set(Math.abs(a.get(i)) - 1 + start, -a.get(Math.abs(a.get(i)) - 1 + start));
            }
        }
        for (int i = start; i < end; i++) {
            if (a.get(i) > 0) {
                return i - start + 1;
            }
        }
        return end - start + 1;
    }
    
    public int firstMissingPositive(ArrayList<Integer> a) {
        int positiveStart = seggregatePosAndNeg(a);
        int firstMissingPositive = findFirstMissingPositive(a, positiveStart, a.size());
        return firstMissingPositive;
    }
}
