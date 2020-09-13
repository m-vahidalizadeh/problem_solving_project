package leetcode.companies.google;

/**
 * Reverse Integer
 * Given a 32-bit signed integer, reverse digits of an integer.
 * <p>
 * Example 1:
 * <p>
 * Input: 123
 * Output: 321
 * Example 2:
 * <p>
 * Input: -123
 * Output: -321
 * Example 3:
 * <p>
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
 * [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {

    public int reverse(int x) {
        boolean isNegative = x < 0;
        if (isNegative) x *= -1;
        long reversed = 0;
        while (x > 0) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        if (reversed > Integer.MAX_VALUE) return 0;
        return (int) ((isNegative ? -1 : 1) * reversed);
    }

    public static void main(String[] args) {
        ReverseInteger r = new ReverseInteger();
        System.out.println(r.reverse(123));
        System.out.println(r.reverse(-123));
        System.out.println(r.reverse(120));
    }

}
