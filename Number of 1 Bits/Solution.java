public class Solution {
    
    public int numSetBits(long a) {
        int count = 0;
        while (a > 0) {
            count++;
            a = a & (a - 1);
        }
        return count;
    }
    
    /*
    
    // ALTERNATE SOLUTION:
    
    public int numSetBits(long a) {
        int count = 0;
        while (a > 0) {
            count += a & 1;
            a >>= 1;
        }
        return count;
    }
    
    */
    
}
