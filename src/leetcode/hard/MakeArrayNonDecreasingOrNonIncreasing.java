package leetcode.hard;

/**
 * 2263. Make Array Non-decreasing or Non-increasing
 * You are given a 0-indexed integer array nums. In one operation, you can:
 *
 * Choose an index i in the range 0 <= i < nums.length
 * Set nums[i] to nums[i] + 1 or nums[i] - 1
 * Return the minimum number of operations to make nums non-decreasing or non-increasing.
 *
 * Example 1:
 *
 * Input: nums = [3,2,4,5,0]
 * Output: 4
 * Explanation:
 * One possible way to turn nums into non-increasing order is to:
 * - Add 1 to nums[1] once so that it becomes 3.
 * - Subtract 1 from nums[2] once so it becomes 3.
 * - Subtract 1 from nums[3] twice so it becomes 3.
 * After doing the 4 operations, nums becomes [3,3,3,3,0] which is in non-increasing order.
 * Note that it is also possible to turn nums into [4,4,4,4,0] in 4 operations.
 * It can be proven that 4 is the minimum number of operations needed.
 * Example 2:
 *
 * Input: nums = [2,2,3,4]
 * Output: 0
 * Explanation: nums is already in non-decreasing order, so no operations are needed and we return 0.
 * Example 3:
 *
 * Input: nums = [0]
 * Output: 0
 * Explanation: nums is already in non-decreasing order, so no operations are needed and we return 0.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 */
public class MakeArrayNonDecreasingOrNonIncreasing {

    public int convertArray(int[] a) {
        int n = a.length;
        return Math.min(solve(a, 0, n, 1), solve(a, n - 1, -1, -1));
    }

    private int solve(int[] a, int start, int end, int delta) {
        int n = a.length;
        int max = 0;
        for (int i = 0; i < n; i++) max = Math.max(a[i], max);
        int[][] dp = new int[n][max + 1];
        for (int j = 0; j <= max; j++) dp[start][j] = Math.abs(a[start] - j);
        for (int i = start + delta; i != end; i += delta) {
            int curMin = Integer.MAX_VALUE;
            for (int j = 0; j <= max; j++) {
                curMin = Math.min(curMin, dp[i - delta][j]);
                dp[i][j] = curMin + Math.abs(a[i] - j);
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j <= max; j++) res = Math.min(res, dp[end - delta][j]);
        return res;
    }

}
