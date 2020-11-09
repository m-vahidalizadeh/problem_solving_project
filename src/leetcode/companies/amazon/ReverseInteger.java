package leetcode.companies.amazon;

/**
 * 7. Reverse Integer
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Note:
 * Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 *
 * Example 1:
 *
 * Input: x = 123
 * Output: 321
 * Example 2:
 *
 * Input: x = -123
 * Output: -321
 * Example 3:
 *
 * Input: x = 120
 * Output: 21
 * Example 4:
 *
 * Input: x = 0
 * Output: 0
 *
 * Constraints:
 *
 * -231 <= x <= 231 - 1
 */
public class ReverseInteger {

    public int reverse(int x) {
        String xS = String.valueOf(x);
        boolean isNegative = false;
        if (xS.charAt(0) == '-') {
            isNegative = true;
            xS = xS.substring(1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < xS.length(); i++) sb.insert(0, xS.charAt(i));
        Double resD = Double.valueOf(sb.toString()) * (isNegative ? -1 : 1);
        return resD < Integer.MIN_VALUE || resD > Integer.MAX_VALUE ? 0 : resD.intValue();
    }

    public static void main(String[] args) {
        ReverseInteger r = new ReverseInteger();
        System.out.println(r.reverse(-123));
    }

}
