package leetcode.companies.amazon;

import java.util.Arrays;

/**
 * 16. 3Sum Closest
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * <p>
 * Constraints:
 * <p>
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int nMinusOne = n - 1;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int l = i + 1;
            int r = nMinusOne;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(target - sum) < Math.abs(diff))
                    diff = target - sum;
                if (diff == 0) return target;
                if (sum < target) l++;
                else r--;
            }
        }
        return target - diff;
    }

    public static void main(String[] args) {
        ThreeSumClosest t = new ThreeSumClosest();
        System.out.println(t.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }

}
