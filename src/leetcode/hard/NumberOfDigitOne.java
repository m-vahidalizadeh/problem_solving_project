package leetcode.hard;

/**
 * 233. Number of Digit One
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 *
 * Example 1:
 *
 * Input: n = 13
 * Output: 6
 * Example 2:
 *
 * Input: n = 0
 * Output: 0
 *
 * Constraints:
 *
 * 0 <= n <= 109
 */
public class NumberOfDigitOne {

    public int countDigitOne(int n) {
        int ones = 0;
        for (int m = 1; m <= n; m *= 10) {
            ones += (n / m + 8) / 10 * m + (n / m % 10 == 1 ? n % m + 1 : 0);
        }
        return ones;
    }

}
