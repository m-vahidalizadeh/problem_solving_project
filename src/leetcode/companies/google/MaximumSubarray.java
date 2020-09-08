package leetcode.companies.google;

/**
 * Maximum Subarray
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Example 3:
 * <p>
 * Input: nums = [0]
 * Output: 0
 * Example 4:
 * <p>
 * Input: nums = [-1]
 * Output: -1
 * Example 5:
 * <p>
 * Input: nums = [-2147483647]
 * Output: -2147483647
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > 0) nums[i] += nums[i - 1];
            max = Math.max(max, nums[i]);
        }
        return max;
    }

}
