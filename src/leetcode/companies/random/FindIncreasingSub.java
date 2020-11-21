package leetcode.companies.random;

/**
 * 674. Longest Continuous Increasing Subsequence
 * Given an unsorted array of integers nums, return the length of the longest continuous increasing subsequence (i.e. subarray). The subsequence must be strictly increasing.
 *
 * A continuous increasing subsequence is defined by two indices l and r (l < r) such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and for each l <= i < r, nums[i] < nums[i + 1].
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
 * Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
 * 4.
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2]
 * Output: 1
 * Explanation: The longest continuous increasing subsequence is [2] with length 1. Note that it must be strictly
 * increasing.
 *
 * Constraints:
 *
 * 0 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 */
public class FindIncreasingSub {

    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return 1;
        int l = 0;
        int r = 0;
        int max = 0;
        while (r < n) {
            int prev = r == 0 ? Integer.MIN_VALUE : nums[r - 1];
            if (prev >= nums[r]) {
                max = Math.max(max, r - l);
                l = r;
            }
            r++;
            if (r == n) max = Math.max(max, r - l);
        }
        return max;
    }

    public static void main(String[] args) {
        FindIncreasingSub f = new FindIncreasingSub();
        System.out.println(f.findLengthOfLCIS(new int[]{1, 3, 5, 4, 7}));
        System.out.println(f.findLengthOfLCIS(new int[]{2, 2, 2, 2, 2}));
    }

}
