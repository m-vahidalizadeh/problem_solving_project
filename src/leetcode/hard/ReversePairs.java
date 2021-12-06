package leetcode.hard;

import java.util.Arrays;

/**
 * 493. Reverse Pairs
 * Given an integer array nums, return the number of reverse pairs in the array.
 *
 * A reverse pair is a pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].
 *
 * Example 1:
 *
 * Input: nums = [1,3,2,3,1]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [2,4,3,5,1]
 * Output: 3
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 104
 * -231 <= nums[i] <= 231 - 1
 */
public class ReversePairs {

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int s, int e) {
        if (s >= e) return 0;
        int mid = s + (e - s) / 2;
        int count = mergeSort(nums, s, mid) + mergeSort(nums, mid + 1, e);
        for (int i = s, j = mid + 1; i <= mid; i++) {
            while (j <= e && nums[i] / 2.0 > nums[j]) j++;
            count += j - (mid + 1);
        }
        Arrays.sort(nums, s, e + 1);
        return count;
    }

}
