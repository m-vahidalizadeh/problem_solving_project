package leetcode.hard;

/**
 * 600. Non-negative Integers without Consecutive Ones
 * Given a positive integer n, return the number of the integers in the range [0, n] whose binary representations do not contain consecutive ones.
 *
 * Example 1:
 *
 * Input: n = 5
 * Output: 5
 * Explanation:
 * Here are the non-negative integers <= 5 with their corresponding binary representations:
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
 * Example 2:
 *
 * Input: n = 1
 * Output: 2
 * Example 3:
 *
 * Input: n = 2
 * Output: 3
 *
 * Constraints:
 *
 * 1 <= n <= 109
 */
public class NonNegativeIntegersWithoutConsecutiveOnes {

    public int findIntegers(int n) {
        int[] dp = new int[32];
        dp[0] = 1; // To make sure that dp[2]=3
        dp[1] = 2; // If n=1, we have 2 cases 0 and 1
        for (int i = 2; i < 32; i++)
            dp[i] = dp[i - 1] + dp[i - 2]; // if you fix the ith bit 0, the rest is anything (dp[i-1]). If you fix it 1, the next bit should be 0 and the rest anything (dp[i-2]).
        int k = 31;
        int pre = 0;
        int res = 0;
        while (k >= 0) {
            if ((n & (1 << k)) != 0) { // if the ith bit from left is one
                res += dp[k]; // since k is 0-indexed, k is the length of the right side of fixed 0 in ith bit
                if (pre == 1) return res; // the n itself has consecutive ones, so, no point in continuing
                pre = 1;
            } else pre = 0;
            k--;
        }
        return res + 1; // since there were no consecutive ones in the n itself, consider n as one case
    }

}
