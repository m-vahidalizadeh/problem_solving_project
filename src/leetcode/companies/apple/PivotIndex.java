package leetcode.companies.apple;

/**
 * Find Pivot Index
 * Given an array of integers nums, write a method that returns the "pivot" index of this array.
 * <p>
 * We define the pivot index as the index where the sum of all the numbers to the left of the index is equal to the sum of all the numbers to the right of the index.
 * <p>
 * If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation:
 * The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
 * Also, 3 is the first index where this occurs.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 * <p>
 * Constraints:
 * <p>
 * The length of nums will be in the range [0, 10000].
 * Each element nums[i] will be an integer in the range [-1000, 1000].
 */
public class PivotIndex {

    public int pivotIndex(int[] nums) {
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return nums[0];
        int[] sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) sums[i] = sums[i - 1] + nums[i];
        int sumNMinusOne = sums[n - 1];
        if (0 == sumNMinusOne - sums[0]) return 0;
        for (int i = 1; i < n - 1; i++) {
            if (sums[i - 1] == sumNMinusOne - sums[i]) return i;
        }
        if (sums[n - 2] == 0) return n - 1;
        return -1;
    }

    public static void main(String[] args) {
        PivotIndex p = new PivotIndex();
        int[] nums1 = {1, 7, 3, 6, 5, 6};
        System.out.println(p.pivotIndex(nums1));
        int[] nums2 = {1, 2, 3};
        System.out.println(p.pivotIndex(nums2));
        int[] nums3 = {-1, -1, -1, 0, 1, 1};
        System.out.println(p.pivotIndex(nums3));
        int[] nums4 = {-1, -1, -1, 1, 1, 1};
        System.out.println(p.pivotIndex(nums4));
    }

}
