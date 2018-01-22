public class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        boolean negDividend = dividend < 0 ? true : false;
        boolean negDivisor = divisor < 0 ? true : false;
        boolean negResult = true;
        boolean minValDividend = false;
        boolean minValDivisor = false;
        if ((negDividend && negDivisor) || (!negDividend && !negDivisor)) {
            negResult = false;
        }
        if (dividend == Integer.MIN_VALUE) {
            dividend = Integer.MAX_VALUE;
            minValDividend = true;
        }
        if (divisor == Integer.MIN_VALUE) {
            divisor = Integer.MAX_VALUE;
            minValDivisor = true;
        }
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int quotient = 0;
        while (dividend >= divisor) {
            if (quotient == Integer.MAX_VALUE) {
                break;
            }
            quotient++;
            dividend = dividend - divisor;
            if (minValDividend) {
                dividend++;
                minValDividend = false;
            }
            if (minValDivisor) {
                dividend--;
            }
        }
        if (negResult) {
            quotient = 0 - quotient;
            if (dividend == 1) {
                quotient--;
            }
        }
        return quotient;
    }
}
