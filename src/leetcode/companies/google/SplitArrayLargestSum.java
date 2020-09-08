package leetcode.companies.google;

import java.util.Arrays;

/**
 * Split Array Largest Sum
 * Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
 * <p>
 * Write an algorithm to minimize the largest sum among these m subarrays.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [7,2,5,10,8], m = 2
 * Output: 18
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4,5], m = 2
 * Output: 9
 * Example 3:
 * <p>
 * Input: nums = [1,4,4], m = 3
 * Output: 4
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 106
 * 1 <= m <= min(50, nums.length)
 */
public class SplitArrayLargestSum {

    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];
        int[] sum = new int[n + 1];
        for (int[] d : dp) Arrays.fill(d, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + nums[i];
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sum[i] - sum[k]));
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        SplitArrayLargestSum s = new SplitArrayLargestSum();
        System.out.println(s.splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }

}
