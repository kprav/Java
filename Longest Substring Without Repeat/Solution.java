public class Solution {
    public int lengthOfLongestSubstring(String a) {
        if (a == null || a.length() == 0) {
            return 0;
        } else if (a.length() == 1) {
            return 1;
        }
        HashSet<Character> set = new HashSet<Character>();
        char[] input = a.toCharArray();
        int maxLength = 1;
        int currLength = 1;
        int start = 0;
        int end = 1;
        set.add(input[start]);
        while (start < input.length && end < input.length) {
            if (!set.contains(input[end])) {
                set.add(input[end]);
                currLength++;
                end++;
            } else {
                while(start < input.length && input[start] != input[end]) {
                    set.remove(input[start]);
                    start++;
                }
                start++;
                currLength = end - start + 1;
                end++;
            }
            maxLength = currLength > maxLength ? currLength : maxLength;
        }
        return maxLength;
    }
}
