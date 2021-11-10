package leetcode.hard;

/**
 * 879. Profitable Schemes
 * There is a group of n members, and a list of various crimes they could commit. The ith crime generates a profit[i] and requires group[i] members to participate in it. If a member participates in one crime, that member can't participate in another crime.
 *
 * Let's call a profitable scheme any subset of these crimes that generates at least minProfit profit, and the total number of members participating in that subset of crimes is at most n.
 *
 * Return the number of schemes that can be chosen. Since the answer may be very large, return it modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: n = 5, minProfit = 3, group = [2,2], profit = [2,3]
 * Output: 2
 * Explanation: To make a profit of at least 3, the group could either commit crimes 0 and 1, or just crime 1.
 * In total, there are 2 schemes.
 * Example 2:
 *
 * Input: n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
 * Output: 7
 * Explanation: To make a profit of at least 5, the group could commit any crimes, as long as they commit one.
 * There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * 0 <= minProfit <= 100
 * 1 <= group.length <= 100
 * 1 <= group[i] <= 100
 * profit.length == group.length
 * 0 <= profit[i] <= 100
 */
public class ProfitableSchemes {

    public int profitableSchemes(int MIN_GROUP, int MAX_PROFIT, int[] group, int[] profit) {
        int MOD = 1_000_000_007;
        int N = group.length;
        int[][][] dp = new int[2][MAX_PROFIT + 1][MIN_GROUP + 1];
        dp[0][0][0] = 1;
        for (int i = 0; i < N; i++) {
            int p0 = profit[i];
            int g0 = group[i];
            int[][] prevRow = dp[i % 2];
            int[][] currRow = dp[(i + 1) % 2];
            for (int jp = 0; jp <= MAX_PROFIT; jp++) {
                for (int jg = 0; jg <= MIN_GROUP; jg++) {
                    currRow[jp][jg] = prevRow[jp][jg];
                }
            }
            for (int p1 = 0; p1 <= MAX_PROFIT; p1++) {
                int p2 = Math.min(MAX_PROFIT, p0 + p1);
                for (int g1 = 0; g1 <= MIN_GROUP - g0; g1++) {
                    int g2 = g0 + g1;
                    currRow[p2][g2] += prevRow[p1][g1];
                    currRow[p2][g2] %= MOD;
                }
            }
        }
        int ans = 0;
        for (int x : dp[N % 2][MAX_PROFIT]) ans = (ans + x) % MOD;
        return ans;
    }

}
