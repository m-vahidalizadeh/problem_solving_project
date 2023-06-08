package leetcode.hard;

/**
 * 644. Maximum Average Subarray II
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous subarray whose length is greater than or equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.
 *
 * Example 1:
 *
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation:
 * - When the length is 4, averages are [0.5, 12.75, 10.5] and the maximum average is 12.75
 * - When the length is 5, averages are [10.4, 10.8] and the maximum average is 10.8
 * - When the length is 6, averages are [9.16667] and the maximum average is 9.16667
 * The maximum average is when we choose a subarray of length 4 (i.e., the sub array [12, -5, -6, 50]) which has the max average 12.75, so we return 12.75
 * Note that we do not consider the subarrays of length < 4.
 * Example 2:
 *
 * Input: nums = [5], k = 1
 * Output: 5.00000
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= k <= n <= 104
 * -104 <= nums[i] <= 104
 */
public class MaxAverageSubarrayII {

    public double findMaxAverage(int[] nums, int k) {
        double l = -1e4, r = 1e4;
        while (r - l > 0.000004) {
            double mid = (l + r) / 2;
            if (check(nums, k, mid)) l = mid;
            else r = mid;
        }
        return r;
    }

    private boolean check(int[] nums, int k, double average) {
        int n = nums.length;
        double curr = 0, last = 0, a[] = new double[n];
        for (int i = 0; i < n; i++) a[i] = nums[i] - average;
        for (int i = 0; i < k; i++) curr += a[i];
        if (curr >= 0) return true;
        for (int i = k; i < n; i++) {
            curr += a[i];
            last += a[i - k];
            if (last < 0) {
                curr -= last;
                last = 0;
            }
            if (curr >= 0) return true;
        }
        return false;
    }

}
