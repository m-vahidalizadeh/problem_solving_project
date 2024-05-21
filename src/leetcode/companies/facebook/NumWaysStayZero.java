package leetcode.companies.facebook;

/**
 * 1269. Number of Ways to Stay in the Same Place After Some Steps
 * Solved
 * Hard
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array, or stay in the same place (The pointer should not be placed outside the array at any time).
 *
 * Given two integers steps and arrLen, return the number of ways such that your pointer is still at index 0 after exactly steps steps. Since the answer may be too large, return it modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: steps = 3, arrLen = 2
 * Output: 4
 * Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
 * Right, Left, Stay
 * Stay, Right, Left
 * Right, Stay, Left
 * Stay, Stay, Stay
 * Example 2:
 *
 * Input: steps = 2, arrLen = 4
 * Output: 2
 * Explanation: There are 2 differents ways to stay at index 0 after 2 steps
 * Right, Left
 * Stay, Stay
 * Example 3:
 *
 * Input: steps = 4, arrLen = 2
 * Output: 8
 *
 * Constraints:
 *
 * 1 <= steps <= 500
 * 1 <= arrLen <= 10^6
 */
public class NumWaysStayZero {

    public int numWays(int steps, int arrLen) {
        return calWays(steps, 0, new Integer[Math.min(steps / 2 + 1, arrLen)][steps + 1], (int) (1e9 + 7));
    }

    private int calWays(int steps, int pos, Integer[][] dp, int mod) {
        if (steps < pos) return 0;
        if (steps == 0) return pos == 0 ? 1 : 0;
        if (dp[pos][steps] != null) return dp[pos][steps];
        int ways = (calWays(steps - 1, pos, dp, mod)) % mod;
        if (pos < dp.length - 1) ways = (ways + calWays(steps - 1, pos + 1, dp, mod)) % mod;
        if (pos > 0) ways = (ways + calWays(steps - 1, pos - 1, dp, mod)) % mod;
        return dp[pos][steps] = ways;
    }

}
