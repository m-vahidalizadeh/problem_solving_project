package leetcode.hard;

/**
 * 1793. Maximum Score of a Good Subarray
 * You are given an array of integers nums (0-indexed) and an integer k.
 *
 * The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.
 *
 * Return the maximum possible score of a good subarray.
 *
 * Example 1:
 *
 * Input: nums = [1,4,3,7,4,5], k = 3
 * Output: 15
 * Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15.
 * Example 2:
 *
 * Input: nums = [5,5,4,5,4,1,1,1], k = 0
 * Output: 20
 * Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 2 * 104
 * 0 <= k < nums.length
 */
public class MaxScoreGoodSub {

    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int l = k;
        int r = k;
        int min = nums[k];
        int res = nums[k];
        while (0 < l || r < n - 1) {
            int lElement = l > 0 ? nums[l - 1] : Integer.MIN_VALUE;
            int rElement = r < n - 1 ? nums[r + 1] : Integer.MIN_VALUE;
            if (lElement > rElement) {
                l--;
                min = Math.min(min, nums[l]);
                res = Math.max(res, min * (r - l + 1));
            } else {
                r++;
                min = Math.min(min, nums[r]);
                res = Math.max(res, min * (r - l + 1));
            }
        }
        return res;
    }

}
