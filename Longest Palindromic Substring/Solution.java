public class Solution {
    
    private String expand(String str, int l, int r) {
        int n = str.length();
        char[] s = str.toCharArray();
        while (l >= 0 && r <= n-1 && s[l] == s[r]) {
            l--;
            r++;
        }
        return str.substring(l+1, r);
    }
    
    public String longestPalindrome(String a) {
        if (a == null || a.length() == 0) {
            return "";
        }
        String longest = a.substring(0, 1);
        for (int i = 0; i < a.length(); i++) {
            String p1 = expand(a, i, i);
            if (p1.length() > longest.length())
            longest = p1;
            String p2 = expand(a, i, i+1);
            if (p2.length() > longest.length())
            longest = p2;
        }
        return longest;
    }
}
