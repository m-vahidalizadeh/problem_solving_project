package leetcode.hard;

/**
 * 629. K Inverse Pairs Array
 * For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length and nums[i] > nums[j].
 *
 * Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs. Since the answer can be huge, return it modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: n = 3, k = 0
 * Output: 1
 * Explanation: Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pairs.
 * Example 2:
 *
 * Input: n = 3, k = 1
 * Output: 2
 * Explanation: The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 * 0 <= k <= 1000
 */
public class KInversePairsArray {

    public int kInversePairs(int n, int k) {
        int mod = 1_000_000_007;
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = 1; // we could satisfy the k inverse pairs
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (((dp[i][j - 1] + dp[i - 1][j]) % mod + mod) - (j >= i ? dp[i - 1][j - i] : 0)) % mod;
            }
        }
        return (int) (dp[n][k] % mod);
    }

}
