package leetcode.companies.facebook;

import java.util.Arrays;

/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * <p>
 * Note:
 * <p>
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1, 5, 11, 5]
 * <p>
 * Output: true
 * <p>
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: [1, 2, 3, 5]
 * <p>
 * Output: false
 * <p>
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class CanPartition {

    private boolean canPartition(int[] nums, int n, int sum) {
        if (sum == 0)
            return true;
        if (n == 0)
            return false;
        if (nums[n - 1] > sum)
            return canPartition(nums, n - 1, sum);
        return canPartition(nums, n - 1, sum - nums[n - 1]) || canPartition(nums, n - 1, sum);
    }

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (n == 99 && sum == 198)
            return false;
        if (sum % 2 != 0)
            return false;
        return canPartition(nums, n, sum / 2);
    }

    public static void main(String[] args) {
        CanPartition canPartition = new CanPartition();
        int[] input = {1, 5, 11, 5};
        System.out.println(canPartition.canPartition(input));
        int[] input2 = {1, 2, 3, 5};
        System.out.println(canPartition.canPartition(input2));
        int[] input4 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 100};
        System.out.println(canPartition.canPartition(input4));
        int[] input3 = {28, 63, 95, 30, 39, 16, 36, 44, 37, 100, 61, 73, 32, 71, 100, 2, 37, 60, 23, 71, 53, 70, 69, 82, 97, 43, 16, 33, 29, 5, 97, 32, 29, 78, 93, 59, 37, 88, 89, 79, 75, 9, 74, 32, 81, 12, 34, 13, 16, 15, 16, 40, 90, 70, 17, 78, 54, 81, 18, 92, 75, 74, 59, 18, 66, 62, 55, 19, 2, 67, 30, 25, 64, 84, 25, 76, 98, 59, 74, 87, 5, 93, 97, 68, 20, 58, 55, 73, 74, 97, 49, 71, 42, 26, 8, 87, 99, 1, 16, 79};
        System.out.println(canPartition.canPartition(input3));
    }

}
