package leetcode.hard;

import java.util.Arrays;

/**
 * 891. Sum of Subsequence Widths
 * The width of a sequence is the difference between the maximum and minimum elements in the sequence.
 *
 * Given an array of integers nums, return the sum of the widths of all the non-empty subsequences of nums. Since the answer may be very large, return it modulo 109 + 7.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 * Example 1:
 *
 * Input: nums = [2,1,3]
 * Output: 6
 * Explanation: The subsequences are [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3].
 * The corresponding widths are 0, 0, 0, 1, 1, 2, 2.
 * The sum of these widths is 6.
 * Example 2:
 *
 * Input: nums = [2]
 * Output: 0
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class SumOfSubsequenceWidths {

    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        long c = 1;
        int n = nums.length;
        long mod = (long) 1e9 + 7;
        long res = 0;
        for (int i = 0; i < n; i++, c = c * 2 % mod) {
            res = (res + nums[i] * c - nums[n - i - 1] * c) % mod;
        }
        return (int) ((res + mod) % mod);
    }

}
