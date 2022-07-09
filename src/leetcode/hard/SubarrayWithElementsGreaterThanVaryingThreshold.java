package leetcode.hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 2334. Subarray With Elements Greater Than Varying Threshold
 * You are given an integer array nums and an integer threshold.
 *
 * Find any subarray of nums of length k such that every element in the subarray is greater than threshold / k.
 *
 * Return the size of any such subarray. If there is no such subarray, return -1.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,3,1], threshold = 6
 * Output: 3
 * Explanation: The subarray [3,4,3] has a size of 3, and every element is greater than 6 / 3 = 2.
 * Note that this is the only valid subarray.
 * Example 2:
 *
 * Input: nums = [6,5,6,5,8], threshold = 7
 * Output: 1
 * Explanation: The subarray [8] has a size of 1, and 8 > 7 / 1 = 7. So 1 is returned.
 * Note that the subarray [6,5] has a size of 2, and every element is greater than 7 / 2 = 3.5.
 * Similarly, the subarrays [6,5,6], [6,5,6,5], [6,5,6,5,8] also satisfy the given conditions.
 * Therefore, 2, 3, 4, or 5 may also be returned.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i], threshold <= 109
 */
public class SubarrayWithElementsGreaterThanVaryingThreshold {

    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        int[] rightSmall = new int[n]; // An array to keep the index of the first right element <= current
        int[] leftSmall = new int[n]; // An array to keep the index of the first left element <= current
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // We start from index 1. So, index 0 would be the initial left
        Arrays.fill(rightSmall, n); // Assume that all the elements in the right are >= nums[i]
        Arrays.fill(leftSmall, -1); // Assume that all the elements in the left are >= nums[i]
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) stack.pop(); // Go till you find a smaller one
            if (!stack.isEmpty()) leftSmall[i] = stack.peek(); // Save the index of the left small element
            stack.push(i); // Push the current element
        }
        stack.clear();
        stack.push(n - 1); // We start from index n-2. So, index n-1 would be the initial right/
        for (int i = n - 2; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) stack.pop(); // Go till you find a smaller one
            if (!stack.isEmpty()) rightSmall[i] = stack.peek(); // Save the index of the right small element
            stack.push(i); // Push the current element
        }
        for (int i = 0; i < n; i++) { // -2 is because we don't consider the left_small and right_small border elements
            int len = rightSmall[i] - leftSmall[i] - 1; // rightSmall[i] - leftSmall[i] +1 -2
            if (threshold / (double) len < nums[i]) return len;
        }
        return -1;
    }

}
