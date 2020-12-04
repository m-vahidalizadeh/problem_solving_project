package leetcode.hard;

import java.util.Arrays;

/**
 * 1671. Minimum Number of Removals to Make Mountain Array
 * You may recall that an array arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array nums​​​, return the minimum number of elements to remove to make nums​​​ a mountain array.
 *
 * Example 1:
 *
 * Input: nums = [1,3,1]
 * Output: 0
 * Explanation: The array itself is a mountain array so we do not need to remove any elements.
 * Example 2:
 *
 * Input: nums = [2,1,1,5,6,2,3,1]
 * Output: 3
 * Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
 * Example 3:
 *
 * Input: nums = [4,3,2,1,1,2,3,1]
 * Output: 4
 * Example 4:
 *
 * Input: nums = [1,2,3,4,4,3,2,1]
 * Output: 1
 *
 * Constraints:
 *
 * 3 <= nums.length <= 1000
 * 1 <= nums[i] <= 109
 * It is guaranteed that you can make a mountain array out of nums.
 */
public class MinRemoveMountainArray {

    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int max = 0;
        int[] left = new int[n + 1];
        Arrays.fill(left, 1);
        int[] right = new int[n + 1];
        Arrays.fill(right, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) left[i] = Math.max(left[i], left[j] + 1);
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[i] > nums[j]) right[i] = Math.max(right[i], right[j] + 1);
            }
            if (left[i] > 1 && right[i] > 1) max = Math.max(max, left[i] + right[i] - 1);
        }
        return n - max;
    }

    public static void main(String[] args) {
        MinRemoveMountainArray minRemoveMountainArray = new MinRemoveMountainArray();
        System.out.println(minRemoveMountainArray.minimumMountainRemovals(new int[]{1, 3, 1}));
        System.out.println(minRemoveMountainArray.minimumMountainRemovals(new int[]{2, 1, 1, 5, 6, 2, 3, 1}));
        System.out.println(minRemoveMountainArray.minimumMountainRemovals(new int[]{4, 3, 2, 1, 1, 2, 3, 1}));
        System.out.println(minRemoveMountainArray.minimumMountainRemovals(new int[]{1, 2, 3, 4, 4, 3, 2, 1}));
        System.out.println(minRemoveMountainArray.minimumMountainRemovals(new int[]{23, 47, 63, 72, 81, 99, 88, 55, 21, 33, 32}));
        /*
        [9,8,1,7,6,5,4,3,2,1]
         */
        System.out.println(minRemoveMountainArray.minimumMountainRemovals(new int[]{9, 8, 1, 7, 6, 5, 4, 3, 2, 1}));
    }

}
