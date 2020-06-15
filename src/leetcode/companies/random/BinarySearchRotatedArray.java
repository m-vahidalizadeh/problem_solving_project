package leetcode.companies.random;

/**
 * Search in Rotated Sorted Array
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * <p>
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class BinarySearchRotatedArray {

    public int search(int[] nums, int target) {
        int n = nums.length;
        return binarySearch(nums, 0, n - 1, target);
    }

    private int binarySearch(int[] nums, int s, int e, int target) {
        if (e == s) return target == nums[s] ? s : -1;
        if (e < s) return -1;
        int mid = (s + e) / 2;
        if (target == nums[mid]) return mid;
        if (target < nums[mid]) {
            int left = binarySearch(nums, s, mid - 1, target);
            return left != -1 ? left : binarySearch(nums, mid + 1, e, target);
        } else {
            int right = binarySearch(nums, mid + 1, e, target);
            return right != -1 ? right : binarySearch(nums, s, mid - 1, target);
        }
    }

}
