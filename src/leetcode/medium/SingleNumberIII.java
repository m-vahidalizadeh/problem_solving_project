package leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Single Number III
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.
 * <p>
 * Follow up: Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,1,3,2,5]
 * Output: [3,5]
 * Explanation:  [5, 3] is also a valid answer.
 * Example 2:
 * <p>
 * Input: nums = [-1,0]
 * Output: [-1,0]
 * Example 3:
 * <p>
 * Input: nums = [0,1]
 * Output: [1,0]
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 30000
 * Each integer in nums will appear twice, only two integers will appear once.
 */
public class SingleNumberIII {

    public int[] singleNumber(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int n : nums) {
            if (numSet.contains(n)) numSet.remove(n);
            else numSet.add(n);
        }
        int[] result = new int[2];
        int i = 0;
        for (int n : numSet) result[i++] = n;
        return result;
    }

    public static void main(String[] args) {
        SingleNumberIII s = new SingleNumberIII();
        System.out.println(Arrays.toString(s.singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
        System.out.println(Arrays.toString(s.singleNumber(new int[]{-1, 0})));
        System.out.println(Arrays.toString(s.singleNumber(new int[]{0, 1})));
    }

}
