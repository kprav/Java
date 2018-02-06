public class Solution {
    public int climbStairs(int stairs) {
        if (stairs <= 0) {
            return 0;
        } else if (stairs == 1) {
            return 1;
        }
        int[] numSteps = {1, 2};
        for (int i = 3; i <= stairs; i++) {
            int count = numSteps[0] + numSteps[1];
            numSteps[0] = numSteps[1];
            numSteps[1] = count;
        }
        return numSteps[1];
    }
}
