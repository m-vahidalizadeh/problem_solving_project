package leetcode.companies.facebook;

/**
 *

 Code

 Testcase
 Testcase

 Test Result
 29. Divide Two Integers
 Solved
 Medium

 Topics

 Companies
 Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

 The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.

 Return the quotient after dividing dividend by divisor.

 Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.

 Example 1:

 Input: dividend = 10, divisor = 3
 Output: 3
 Explanation: 10/3 = 3.33333.. which is truncated to 3.
 Example 2:

 Input: dividend = 7, divisor = -3
 Output: -2
 Explanation: 7/-3 = -2.33333.. which is truncated to -2.

 Constraints:

 -231 <= dividend, divisor <= 231 - 1
 divisor != 0
 */
public class Divide {

    public int divide(int dividend, int divisor) {
        int sign = ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) ? -1 : 1;
        long ldividend = Math.abs((long) dividend), ldivisor = Math.abs((long) divisor);
        if (ldividend == 0 || ldividend < ldivisor) return 0;
        long lans = ldivide(ldividend, ldivisor);
        if (lans > Integer.MAX_VALUE) return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        return (int) (sign * lans);
    }

    private long ldivide(long ldividend, long ldivisor) {
        if (ldividend < ldivisor) return 0;
        long sum = ldivisor, multiple = 1;
        while ((sum + sum) < ldividend) {
            multiple += multiple;
            sum += sum;
        }
        return multiple + ldivide(ldividend - sum, ldivisor);
    }

}
