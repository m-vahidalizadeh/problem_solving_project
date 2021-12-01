package leetcode.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 1755. Closest Subsequence Sum
 * You are given an integer array nums and an integer goal.
 *
 * You want to choose a subsequence of nums such that the sum of its elements is the closest possible to goal. That is, if the sum of the subsequence's elements is sum, then you want to minimize the absolute difference abs(sum - goal).
 *
 * Return the minimum possible value of abs(sum - goal).
 *
 * Note that a subsequence of an array is an array formed by removing some elements (possibly all or none) of the original array.
 *
 * Example 1:
 *
 * Input: nums = [5,-7,3,5], goal = 6
 * Output: 0
 * Explanation: Choose the whole array as a subsequence, with a sum of 6.
 * This is equal to the goal, so the absolute difference is 0.
 * Example 2:
 *
 * Input: nums = [7,-9,15,-2], goal = -5
 * Output: 1
 * Explanation: Choose the subsequence [7,-9,-2], with a sum of -4.
 * The absolute difference is abs(-4 - (-5)) = abs(1) = 1, which is the minimum.
 * Example 3:
 *
 * Input: nums = [1,2,3], goal = -7
 * Output: 7
 *
 * Constraints:
 *
 * 1 <= nums.length <= 40
 * -107 <= nums[i] <= 107
 * -109 <= goal <= 109
 */
public class ClosestSubsequenceSum {

    public int minAbsDifference(int[] nums, int goal) {
        Set<Integer> m1 = new HashSet<>();
        TreeSet<Integer> m2 = new TreeSet<>();
        int n = nums.length;
        dfs(Arrays.copyOfRange(nums, 0, n / 2), 0, 0, m1);
        dfs(Arrays.copyOfRange(nums, n / 2, n), 0, 0, m2);
        int res = Integer.MAX_VALUE;
        for (int x : m1) {
            int y = goal - x;
            Integer larger = m2.ceiling(y);
            Integer smaller = m2.floor(y);
            if (larger != null) res = Math.min(res, larger - y);
            if (smaller != null) res = Math.min(res, y - smaller);
        }
        return res;
    }

    private void dfs(int[] nums, int pos, int currS, Set<Integer> memo) {
        if (pos == nums.length) {
            memo.add(currS);
            return;
        }
        dfs(nums, pos + 1, currS + nums[pos], memo);
        dfs(nums, pos + 1, currS, memo);
    }

}
