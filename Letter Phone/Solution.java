public class Solution {
    char[][] map = {{'0'},{'1'},{'a','b','c'},{'d','e','f'},
                    {'g','h','i'},{'j','k','l'},{'m','n','o'},
                    {'p','q','r','s'}, {'t','u','v'},{'w','x','y','z'}};
    
    private void letterCombHelper(int[] input, ArrayList<String> result, String s, int digit, int len) {
        if (digit == len) {
            result.add(s);
            return;
        }
        for (int i = 0; i < map[input[digit]].length; i++) {
            s += map[input[digit]][i];
            letterCombHelper(input, result, s, digit + 1, len);
            s = s.substring(0, s.length() - 1);
        }
    }
    
    public ArrayList<String> letterCombinations(String A) {
        ArrayList<String> result = new ArrayList<>();
        int stringToInt = Integer.parseInt(A);
        int[] input = new int[A.length()];
        int i = A.length() - 1;
        while (stringToInt > 0) {
            int rem = stringToInt % 10;
            stringToInt = stringToInt / 10;
            input[i--] = rem;
        }
        letterCombHelper(input, result, "", 0, A.length());
        return result;
    }
}
