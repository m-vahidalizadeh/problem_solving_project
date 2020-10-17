package leetcode.companies.amazon;

import java.util.Arrays;

/**
 * 238. Product of Array Except Self
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
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] l = new int[n];
        int[] r = new int[n];
        l[0] = nums[0];
        for (int i = 1; i < n; i++) l[i] = l[i - 1] * nums[i];
        r[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) r[i] = r[i + 1] * nums[i];
        int[] result = new int[n];
        result[0] = r[1];
        result[n - 1] = l[n - 2];
        for (int i = 1; i < n - 1; i++) result[i] = l[i - 1] * r[i + 1];
        return result;
    }

    public static void main(String[] args) {
        ProductOfArrayExceptSelf p = new ProductOfArrayExceptSelf();
        System.out.println(Arrays.toString(p.productExceptSelf(new int[]{1, 2, 3, 4})));
    }

}
