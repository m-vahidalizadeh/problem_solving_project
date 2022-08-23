package leetcode.hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 2386. Find the K-Sum of an Array
 * You are given an integer array nums and a positive integer k. You can choose any subsequence of the array and sum all of its elements together.
 *
 * We define the K-Sum of the array as the kth largest subsequence sum that can be obtained (not necessarily distinct).
 *
 * Return the K-Sum of the array.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 * Note that the empty subsequence is considered to have a sum of 0.
 *
 * Example 1:
 *
 * Input: nums = [2,4,-2], k = 5
 * Output: 2
 * Explanation: All the possible subsequence sums that we can obtain are the following sorted in decreasing order:
 * - 6, 4, 4, 2, 2, 0, 0, -2.
 * The 5-Sum of the array is 2.
 * Example 2:
 *
 * Input: nums = [1,-2,3,4,-10,12], k = 16
 * Output: 10
 * Explanation: The 16-Sum of the array is 10.
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 105
 * -109 <= nums[i] <= 109
 * 1 <= k <= min(2000, 2n)
 */
public class FindTheKSumOfAnArray {

    public long kSum(int[] nums, int k) {
        long minus = 0, all = 0;
        for (int i = 0; i < nums.length; i++) {
            all += Math.max(nums[i], 0);
            nums[i] = Math.abs(nums[i]);
        }
        Arrays.sort(nums);
        Queue<long[]> minheap = new PriorityQueue<>(Comparator.comparingLong(o -> o[0]));
        minheap.add(new long[]{nums[0], 0L});
        for (int i = 0; i < k; i++) {
            long[] top = minheap.poll();
            int index = (int) top[1]++;
            long val = top[0];
            minus = val;
            if (index < nums.length - 1) {
                top[0] += nums[index + 1];
                minheap.add(new long[]{top[0] - nums[index], top[1]});
                minheap.add(top);
            }
        }
        return all - minus;
    }

}
