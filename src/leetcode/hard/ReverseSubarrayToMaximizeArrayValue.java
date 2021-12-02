package leetcode.hard;

/**
 * 1330. Reverse Subarray To Maximize Array Value
 * You are given an integer array nums. The value of this array is defined as the sum of |nums[i] - nums[i + 1]| for all 0 <= i < nums.length - 1.
 *
 * You are allowed to select any subarray of the given array and reverse it. You can perform this operation only once.
 *
 * Find maximum possible value of the final array.
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,5,4]
 * Output: 10
 * Explanation: By reversing the subarray [3,1,5] the array becomes [2,5,1,3,4] whose value is 10.
 * Example 2:
 *
 * Input: nums = [2,4,9,24,2,1,10]
 * Output: 68
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 */
public class ReverseSubarrayToMaximizeArrayValue {

    public int maxValueAfterReverse(int[] nums) {
        int valWithoutReverse = 0;
        int improvementByReversing = 0;
        int secondMin = Integer.MAX_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int currDiff = Math.abs(nums[i] - nums[i + 1]);
            valWithoutReverse += currDiff;
            improvementByReversing = Math.max(improvementByReversing, Math.abs(nums[i + 1] - nums[0]) - currDiff); // Reverse nums[0] to nums[i]
            improvementByReversing = Math.max(improvementByReversing, Math.abs(nums[i] - nums[n - 1]) - currDiff); // Reverse nums[i+1] to nums[n-1]
            secondMin = Math.min(secondMin, Math.max(nums[i], nums[i + 1])); // Keep track of the second min
            secondMax = Math.max(secondMax, Math.min(nums[i], nums[i + 1])); // Keep track of the second max
        } // The last possible improvementByReversing is reversing [secondMin...secondMax] in: ... firstMin [secondMin ... secondMax] firstMax ...
        return valWithoutReverse + Math.max(improvementByReversing, 2 * (secondMax - secondMin)); // 2*(secondMax-secondMin) could be achieved by reversing [secondMin...secondMax]
    }

}
