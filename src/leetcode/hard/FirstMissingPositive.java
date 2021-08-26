package leetcode.hard;

/**
 * 41. First Missing Positive
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 *
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 *
 * Example 1:
 *
 * Input: nums = [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num <= 0 || num > n)
                nums[i] = n + 1; // eliminate this number res is between 1 and n we use indexes 0 to n-1
        }
        for (int num : nums) {
            num = Math.abs(num);
            if (num <= n) {
                int index = num - 1; // indexes are zero based
                if (nums[index] > 0) nums[index] = -nums[index];
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) return i + 1; // numbers are 1 based
        }
        return n + 1;
    }

}
