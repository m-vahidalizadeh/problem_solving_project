package leetcode.companies.google;

/**
 * Maximum Product Subarray
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 * <p>
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int max = nums[0];
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        for (int i = 1; i < n; i++) {
            int tempMaxSoFar = Math.max(nums[i], Math.max(nums[i] * maxSoFar, nums[i] * minSoFar));
            minSoFar = Math.min(nums[i], Math.min(nums[i] * maxSoFar, nums[i] * minSoFar));
            maxSoFar = tempMaxSoFar;
            max = Math.max(max, maxSoFar);
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumProductSubarray m = new MaximumProductSubarray();
        System.out.println(m.maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(m.maxProduct(new int[]{-2, 0, -1}));
        System.out.println(m.maxProduct(new int[]{-3, -1, -1}));
    }

}
