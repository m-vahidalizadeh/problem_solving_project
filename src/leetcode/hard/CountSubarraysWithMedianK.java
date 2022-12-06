package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 2488. Count Subarrays With Median K
 * You are given an array nums of size n consisting of distinct integers from 1 to n and a positive integer k.
 *
 * Return the number of non-empty subarrays in nums that have a median equal to k.
 *
 * Note:
 *
 * The median of an array is the middle element after sorting the array in ascending order. If the array is of even length, the median is the left middle element.
 * For example, the median of [2,3,1,4] is 2, and the median of [8,4,3,5,1] is 4.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,4,5], k = 4
 * Output: 3
 * Explanation: The subarrays that have a median equal to 4 are: [4], [4,5] and [1,4,5].
 * Example 2:
 *
 * Input: nums = [2,3,1], k = 3
 * Output: 1
 * Explanation: [3] is the only subarray that has a median equal to 3.
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i], k <= n
 * The integers in nums are distinct.
 */
public class CountSubarraysWithMedianK {

    public int countSubarrays(int[] nums, int k) {
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) {
                idx = i;
                break;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i = idx; i < nums.length; i++) {
            sum += nums[i] == k ? 0 : nums[i] > k ? 1 : -1;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        long result = 0;
        sum = 0;
        for (int i = idx; i >= 0; i--) {
            sum += nums[i] == k ? 0 : nums[i] > k ? 1 : -1;
            result += map.getOrDefault(-sum, 0);
            result += map.getOrDefault(-sum + 1, 0);
        }
        return (int) result;
    }

}
