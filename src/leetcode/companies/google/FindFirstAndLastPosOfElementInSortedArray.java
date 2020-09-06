package leetcode.companies.google;

import java.util.Arrays;

/**
 * Find First and Last Position of Element in Sorted Array
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * nums is a non decreasing array.
 * -10^9 <= target <= 10^9
 */
public class FindFirstAndLastPosOfElementInSortedArray {

    int nMinusOne;
    int targetPlusOne;

    public int[] searchRange(int[] nums, int target) {
        nMinusOne = nums.length - 1;
        if (nMinusOne == -1) return new int[]{-1, -1};
        targetPlusOne = target + 1;
        return new int[]{findFirst(nums, 0, nMinusOne, target), findLast(nums, 0, nMinusOne, target)};
    }

    private int findFirst(int[] nums, int s, int e, int target) {
        if (s > e) return -1;
        int mid = (s + e) / 2;
        int curr = nums[mid];
        int prev = mid == 0 ? targetPlusOne : nums[mid - 1];
        if (curr == target && prev != target) return mid;
        if (curr >= target) return findFirst(nums, s, mid - 1, target);
        return findFirst(nums, mid + 1, e, target);
    }

    private int findLast(int[] nums, int s, int e, int target) {
        if (s > e) return -1;
        int mid = (s + e) / 2;
        int curr = nums[mid];
        int next = mid == nMinusOne ? targetPlusOne : nums[mid + 1];
        if (curr == target && next != target) return mid;
        if (curr <= target) return findLast(nums, mid + 1, e, target);
        return findLast(nums, s, mid - 1, target);
    }

    public static void main(String[] args) {
        FindFirstAndLastPosOfElementInSortedArray f = new FindFirstAndLastPosOfElementInSortedArray();
        System.out.println(Arrays.toString(f.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(f.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        System.out.println(Arrays.toString(f.searchRange(new int[]{8, 8, 8, 8, 8, 8}, 8)));
        System.out.println(Arrays.toString(f.searchRange(new int[]{1}, 1)));
        System.out.println(Arrays.toString(f.searchRange(new int[]{1, 3}, 1)));
    }

}
