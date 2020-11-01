package leetcode.companies.amazon;

import java.util.Arrays;

/**
 * 503. Next Greater Element II
 * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
 *
 * Example 1:
 *
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 * Note: The length of given array won't exceed 10000.
 */
public class NextGreaterNumber {

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        if (n == 0) return new int[]{};
        int[] lMax = new int[n];
        int[] rMax = new int[n];
        lMax[0] = nums[0];
        rMax[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) lMax[i] = Math.max(lMax[i - 1], nums[i]);
        for (int i = n - 2; i >= 0; i--) rMax[i] = Math.max(rMax[i + 1], nums[i]);
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            Integer maxL = i == 0 ? null : lMax[i - 1];
            Integer maxR = i == n - 1 ? null : rMax[i + 1];
            if (maxR == null || maxR <= nums[i]) {
                if (maxL == null || maxL <= nums[i]) {
                    result[i] = -1;
                } else {
                    // Search left
                    result[i] = findGreater(nums, 0, i - 1, nums[i]);
                }
            } else {
                // Search right
                result[i] = findGreater(nums, i + 1, n - 1, nums[i]);
            }
        }
        return result;
    }

    private int findGreater(int[] nums, int s, int e, int num) {
        for (int i = s; i <= e; i++) {
            if (nums[i] > num) return nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        NextGreaterNumber n = new NextGreaterNumber();
        System.out.println(Arrays.toString(n.nextGreaterElements(new int[]{1, 2, 1})));
    }

}
