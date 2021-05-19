package leetcode.hard;

/**
 * 1866. Number of Ways to Rearrange Sticks With K Sticks Visible
 * There are n uniquely-sized sticks whose lengths are integers from 1 to n. You want to arrange the sticks such that exactly k sticks are visible from the left. A stick is visible from the left if there are no longer sticks to the left of it.
 *
 * For example, if the sticks are arranged [1,3,2,5,4], then the sticks with lengths 1, 3, and 5 are visible from the left.
 * Given n and k, return the number of such arrangements. Since the answer may be large, return it modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: n = 3, k = 2
 * Output: 3
 * Explanation: [1,3,2], [2,3,1], and [2,1,3] are the only arrangements such that exactly 2 sticks are visible.
 * The visible sticks are underlined.
 * Example 2:
 *
 * Input: n = 5, k = 5
 * Output: 1
 * Explanation: [1,2,3,4,5] is the only arrangement such that all 5 sticks are visible.
 * The visible sticks are underlined.
 * Example 3:
 *
 * Input: n = 20, k = 11
 * Output: 647427950
 * Explanation: There are 647427950 (mod 109 + 7) ways to rearrange the sticks such that exactly 11 sticks are visible.
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 * 1 <= k <= n
 */
public class RearrangeSticksKVisible {

    public static final int MOD = 1_000_000_007;
    Integer[][] dp;

    public int rearrangeSticks(int n, int k) {
        dp = new Integer[n + 1][k + 1];
        return rec(n, k) % MOD;
    }

    private int rec(int n, int k) {
        if (n == k) return 1;
        if (k == 0) return 0;
        if (dp[n][k] != null) return dp[n][k];
        int res = 0;
        res += rec(n - 1, k - 1) % MOD; // Place the largest in the rightmost place.
        res += (rec(n - 1, k) * (long) (n - 1)) % MOD; // Place each one of the non-largest in the rightmost place.
        return dp[n][k] = res % MOD;
    }

}
