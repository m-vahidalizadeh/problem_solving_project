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
public class GetPivot {

    public int pivotIndex(int[] nums) {
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return nums[0];
        int[] accumulativeSums = new int[n];
        accumulativeSums[0] = nums[0];
        for (int i = 1; i < n; i++) accumulativeSums[i] = accumulativeSums[i - 1] + nums[i];
        for (int i = 0; i < n; i++) {
            int leftSum = i == 0 ? 0 : accumulativeSums[i - 1];
            int rightSum = i == n - 1 ? 0 : accumulativeSums[n - 1] - accumulativeSums[i];
            if (rightSum == leftSum) return i;
        }
        return -1;
    }

}
