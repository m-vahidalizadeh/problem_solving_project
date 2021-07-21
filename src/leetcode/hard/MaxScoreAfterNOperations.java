package leetcode.hard;

/**
 * 1799. Maximize Score After N Operations
 * You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.
 *
 * In the ith operation (1-indexed), you will:
 *
 * Choose two elements, x and y.
 * Receive a score of i * gcd(x, y).
 * Remove x and y from nums.
 * Return the maximum score you can receive after performing n operations.
 *
 * The function gcd(x, y) is the greatest common divisor of x and y.
 *
 * Example 1:
 *
 * Input: nums = [1,2]
 * Output: 1
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(1, 2)) = 1
 * Example 2:
 *
 * Input: nums = [3,4,6,8]
 * Output: 11
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
 * Example 3:
 *
 * Input: nums = [1,2,3,4,5,6]
 * Output: 14
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
 *
 * Constraints:
 *
 * 1 <= n <= 7
 * nums.length == 2 * n
 * 1 <= nums[i] <= 106
 */
public class MaxScoreAfterNOperations {

    Integer[][] dp;
    int n;
    int allUsed;

    public int maxScore(int[] nums) {
        n = nums.length;
        allUsed = (1 << n) - 1;
        dp = new Integer[n / 2 + 1][allUsed + 1];
        return rec(1, 0, nums);
    }

    private int rec(int step, int mask, int[] nums) {
        if (mask == allUsed) return 0;
        if (dp[step][mask] != null) return dp[step][mask];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) {
                for (int j = i + 1; j < n; j++) {
                    if ((mask & (1 << j)) == 0) {
                        res = Math.max(res, step * gcd(nums[i], nums[j]) + rec(step + 1, mask | (1 << i) | (1 << j), nums));
                    }
                }
            }
        }
        return dp[step][mask] = res;
    }

    private int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
