public class Solution {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int singleNumber(final List<Integer> A) {
        int num = 0;
        for (Integer i : A) {
            num = num ^ i;
        }
        return num;
    }
}
