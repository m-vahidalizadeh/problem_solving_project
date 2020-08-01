package leetcode.companies.google;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 * Example 2:
 * <p>
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 * Example 3:
 * <p>
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 */
public class LongestSubarray {

    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return 1;
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        int maxSize = 1;
        int start = 0;
        int end = 0;
        while (end < n) {
            minQ.add(nums[end]);
            maxQ.add(nums[end]);
            if (maxQ.peek() - minQ.peek() <= limit) {
                end++;
                maxSize = Math.max(maxSize, end - start);
            } else {
                minQ.remove(nums[start]);
                maxQ.remove(nums[start]);
                start++;
                end++;
            }
        }
        return maxSize;
    }

    public static void main(String[] args) {
        LongestSubarray l = new LongestSubarray();
        int[] nums1 = {8, 2, 4, 7};
        System.out.println(l.longestSubarray(nums1, 4));
        int[] nums2 = {4, 2, 2, 2, 4, 4, 2, 2};
        System.out.println(l.longestSubarray(nums2, 0));
        int[] nums3 = {10, 1, 2, 4, 7, 2};
        System.out.println(l.longestSubarray(nums3, 5));
    }

}
