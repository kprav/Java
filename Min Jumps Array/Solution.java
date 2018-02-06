public class Solution {
    public int jump(ArrayList<Integer> A) {
        
        if (A == null) {
            return 0;
        } else if (A.size() == 0 || A.size() == 1) {
            return 0;
        } else if (A.get(0) == 0) {
            return -1;
        }
        
        int maxPossiblePos = A.get(0);
        int stepsAllowed = A.get(0);
        int jump = 1;
        for (int i = 1; i < A.size(); i++) {
            if (i == A.size() - 1) {
                return jump;
            }
            maxPossiblePos = Math.max(maxPossiblePos, i + A.get(i));
            stepsAllowed--;
            if (stepsAllowed == 0) {
                jump++;
                if (i >= maxPossiblePos) {
                    return -1;
                }
                stepsAllowed = maxPossiblePos - i;
            }
        }
        return -1;
        
        /*
        
        ALTERNATE SOLUTION (DP):
        
        int[] minJump = new int[A.size()];
        int[] actualJump = new int[A.size()];
        minJump[0] = 0;
        for (int i = 1; i < A.size(); i++) {
            minJump[i] = Integer.MAX_VALUE - 1;
        }
        for (int i = 1; i < A.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (j + A.get(j) >= i) {
                    int numJump = minJump[j] + 1;
                    if (numJump < minJump[i]) {
                        minJump[i] = numJump;
                        actualJump[i] = j;
                    }
                }
            }
        }
        if (minJump[A.size() - 1] == Integer.MAX_VALUE - 1) {
            return -1;
        } else {
            return minJump[A.size() - 1];
        }
        */
    }
}
