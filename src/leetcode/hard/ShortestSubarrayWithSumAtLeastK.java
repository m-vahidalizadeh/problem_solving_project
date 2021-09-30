package leetcode.hard;

import java.util.LinkedList;

/**
 * 862. Shortest Subarray with Sum at Least K
 * Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.
 *
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 *
 * Input: nums = [1], k = 1
 * Output: 1
 * Example 2:
 *
 * Input: nums = [1,2], k = 4
 * Output: -1
 * Example 3:
 *
 * Input: nums = [2,-1,2], k = 3
 * Output: 3
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -105 <= nums[i] <= 105
 * 1 <= k <= 109
 */
public class ShortestSubarrayWithSumAtLeastK {

    public int shortestSubarray(int[] nums, int k) {
        int[] preSums = new int[nums.length + 1];
        int i = 1;
        for (int num : nums) {
            preSums[i] = preSums[i - 1] + num;
            i++;
        }
        LinkedList<Integer> incQ = new LinkedList<>();
        int res = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length + 1; right++) {
            int s = preSums[right];
            while (!incQ.isEmpty() && (s - preSums[incQ.getFirst()]) >= k)
                res = Math.min(res, right - incQ.removeFirst());
            while (!incQ.isEmpty() && s <= preSums[incQ.getLast()])
                incQ.removeLast();
            incQ.addLast(right);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
