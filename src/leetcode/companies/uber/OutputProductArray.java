package leetcode.companies.uber;

/**
 * Product of Array Except Self
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
 * <p>
 * Note: Please solve it without division and in O(n).
 * <p>
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class OutputProductArray {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] right = new int[n];
        int[] left = new int[n];
        left[0] = 1;
        right[n - 1] = 1;
        for (int i = 1; i < n; i++) left[i] = left[i - 1] * nums[i - 1];
        for (int i = n - 2; i >= 0; i--) right[i] = right[i + 1] * nums[i + 1];
        for (int i = 0; i < n; i++) nums[i] = left[i] * right[i];
        return nums;
    }

    public static void main(String[] args) {
        OutputProductArray o = new OutputProductArray();
        int[] nums = {1, 2, 3, 4};
        printArray(o.productExceptSelf(nums));
    }

    private static void printArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) System.out.print(nums[i] + " ");
    }

}
