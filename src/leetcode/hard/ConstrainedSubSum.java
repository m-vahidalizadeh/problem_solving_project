package leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1425. Constrained Subsequence Sum
 * Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
 *
 * A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.
 *
 * Example 1:
 *
 * Input: nums = [10,2,-10,5,20], k = 2
 * Output: 37
 * Explanation: The subsequence is [10, 2, 5, 20].
 * Example 2:
 *
 * Input: nums = [-1,-2,-3], k = 1
 * Output: -1
 * Explanation: The subsequence must be non-empty, so we choose the largest number.
 * Example 3:
 *
 * Input: nums = [10,-2,-10,-5,20], k = 2
 * Output: 23
 * Explanation: The subsequence is [10, -2, -5, 20].
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class ConstrainedSubSum {

    public int constrainedSubsetSum(int[] nums, int k) {
        Deque<Integer> decQ = new ArrayDeque<>();
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > k && decQ.getFirst() == dp[i - k - 1]) decQ.pollFirst();
            dp[i] = Math.max(decQ.isEmpty() ? 0 : decQ.getFirst(), 0) + nums[i];
            while (!decQ.isEmpty() && decQ.getLast() < dp[i]) decQ.pollLast();
            decQ.addLast(dp[i]);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) max = Math.max(max, dp[i]);
        return max;
    }

}
