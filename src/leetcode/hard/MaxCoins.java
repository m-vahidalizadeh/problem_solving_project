package leetcode.hard;

/**
 * 312. Burst Balloons
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.
 *
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
 *
 * Return the maximum coins you can collect by bursting the balloons wisely.
 *
 * Example 1:
 *
 * Input: nums = [3,1,5,8]
 * Output: 167
 * Explanation:
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * Example 2:
 *
 * Input: nums = [1,5]
 * Output: 10
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 500
 * 0 <= nums[i] <= 100
 */
public class MaxCoins {

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) // Initialize for window size 1
            dp[i][i] = (i == 0 ? 1 : nums[i - 1]) * nums[i] * (i == n - 1 ? 1 : nums[i + 1]);
        for (int window = 2; window <= n; window++) { // Calculate for window size 2 to n
            for (int left = 0; left <= n - window; left++) { // Last window: left=n-window right=n-1 (n-window+window-1)
                int right = left + window - 1;
                int before = left == 0 ? 1 : nums[left - 1];
                int after = right == n - 1 ? 1 : nums[right + 1];
                for (int i = left; i <= right; i++) { // Assume we burst i last in the window.
                    int dpBefore = i == 0 ? 0 : dp[left][i - 1]; // if out of bound, dp=0
                    int dpAfter = i == n - 1 ? 0 : dp[i + 1][right]; // if out of bound, dp=0
                    dp[left][right] = Math.max(dp[left][right], before * nums[i] * after + dpBefore + dpAfter); // Can we maximize the window score by bursting the ith balloon last?
                }
            }
        }
        return dp[0][n - 1]; // Return the max score we can collect from 0th index to n-1th index
    }

}
