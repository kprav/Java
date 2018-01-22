public class Solution {
    
    private String reverse(char[] s) {
        int i, j;
        for (i = 0, j = 31; i < j; i++, j--) {
            if ((((int) s[i]) ^ ((int) s[j])) != 0) {
                s[i] = (char) (((int) s[i]) ^ 1);
                s[j] = (char) (((int) s[j]) ^ 1);
            }
        }
        return String.valueOf(s);
    }
    
    public long reverse(long a) {
        String binaryLong = Long.toBinaryString(a);
	    StringBuilder binaryLong32bit = new StringBuilder();
	    for (int i = 0; i < 32 - binaryLong.length(); i++) {
	        binaryLong32bit.append("0");
	    }
	    binaryLong32bit.append(new StringBuilder(binaryLong));
	    return Long.parseLong(reverse(binaryLong32bit.toString().toCharArray()), 2);
    }
    
    
    /*
    
    ALTERNATE SOLUTION:
    
    public long reverse(long a) {
        String binaryLong = Long.toBinaryString(a);
        StringBuilder binaryLong32bit = new StringBuilder();
        for (int i = 0; i < 32 - binaryLong.length(); i++) {
            binaryLong32bit.append("0");
        }
        binaryLong32bit.append(new StringBuilder(binaryLong));
        String binaryLongReverse = binaryLong32bit.reverse().toString();
        return Long.parseLong(binaryLongReverse, 2);
    }

    */
}
