package fb;

import java.util.logging.Logger;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * <p>
 * Return the quotient after dividing dividend by divisor.
 * <p>
 * The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
 * <p>
 * Example 1:
 * <p>
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = truncate(3.33333..) = 3.
 * Example 2:
 * <p>
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = truncate(-2.33333..) = -2.
 * Note:
 * <p>
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 */
public class CustomDivide {

    public static long divide(long dividend,
                              long divisor) {

// Calculate sign of divisor
// i.e., sign will be negative
// only iff either one of them
// is negative otherwise it
// will be positive
        long sign = ((dividend < 0) ^
                (divisor < 0)) ? -1 : 1;

// remove sign of operands
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

// Initialize the quotient
        long quotient = 0, temp = 0;

// test down from the highest
// bit and accumulate the
// tentative value for
// valid bit
// 1<<31 behaves incorrectly and gives Integer
// Min Value which should not be the case, instead
        // 1L<<31 works correctly.
        for (int i = 31; i >= 0; --i) {

            if (temp + (divisor << i) <= dividend) {
                temp += divisor << i;
                quotient |= 1L << i;
            }
        }

        return (sign * quotient);
    }

    public static void main(String[] args) {
        CustomDivide customDivide = new CustomDivide();
        System.out.println(customDivide.divide(10, 3));
        System.out.println(customDivide.divide(7, -3));
        System.out.println(customDivide.divide(
                -2147483648, -1));
    }

}
