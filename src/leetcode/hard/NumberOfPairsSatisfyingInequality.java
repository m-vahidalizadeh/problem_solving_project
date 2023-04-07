package leetcode.hard;

import java.util.Arrays;

/**
 * 2426. Number of Pairs Satisfying Inequality
 * You are given two 0-indexed integer arrays nums1 and nums2, each of size n, and an integer diff. Find the number of pairs (i, j) such that:
 *
 * 0 <= i < j <= n - 1 and
 * nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
 * Return the number of pairs that satisfy the conditions.
 *
 * Example 1:
 *
 * Input: nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
 * Output: 3
 * Explanation:
 * There are 3 pairs that satisfy the conditions:
 * 1. i = 0, j = 1: 3 - 2 <= 2 - 2 + 1. Since i < j and 1 <= 1, this pair satisfies the conditions.
 * 2. i = 0, j = 2: 3 - 5 <= 2 - 1 + 1. Since i < j and -2 <= 2, this pair satisfies the conditions.
 * 3. i = 1, j = 2: 2 - 5 <= 2 - 1 + 1. Since i < j and -3 <= 2, this pair satisfies the conditions.
 * Therefore, we return 3.
 * Example 2:
 *
 * Input: nums1 = [3,-1], nums2 = [-2,2], diff = -1
 * Output: 0
 * Explanation:
 * Since there does not exist any pair that satisfies the conditions, we return 0.
 *
 * Constraints:
 *
 * n == nums1.length == nums2.length
 * 2 <= n <= 105
 * -104 <= nums1[i], nums2[i] <= 104
 * -104 <= diff <= 104
 */
public class NumberOfPairsSatisfyingInequality {

    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) nums[i] = nums1[i] - nums2[i];
        long[] tmp = Arrays.copyOf(nums, n);
        return sort(nums, tmp, 0, n - 1, diff);
    }

    private long sort(long[] nums, long[] tmp, int lo, int hi, int diff) {
        if (lo == hi) return 0L;
        int mid = lo + (hi - lo) / 2;
        long res = 0L;
        res += sort(nums, tmp, lo, mid, diff);
        res += sort(nums, tmp, mid + 1, hi, diff);
        res += merge(nums, tmp, lo, mid, hi, diff);
        return res;
    }

    private long merge(long[] nums, long[] tmp, int lo, int mid, int hi, int diff) {
        long res = 0L;
        for (int i = lo, j = mid + 1; i <= mid && j <= hi; i++) {
            while (j <= hi && nums[i] > nums[j] + diff) j++;
            res += hi - j + 1;
        }
        for (int i = lo, j = mid + 1, k = lo; i <= mid || j <= hi; k++) {
            if (j > hi || (i <= mid && nums[i] < nums[j])) tmp[k] = nums[i++];
            else tmp[k] = nums[j++];
        }
        System.arraycopy(tmp, lo, nums, lo, hi - lo + 1);
        return res;
    }

}
