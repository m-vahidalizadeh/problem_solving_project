package leetcode.medium;

import leetcode.base.Utils;

import java.util.*;

/**
 * Find All Duplicates in an Array
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * <p>
 * Find all the elements that appear twice in this array.
 * <p>
 * Could you do it without extra space and in O(n) runtime?
 * <p>
 * Example:
 * <p>
 * Input:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * Output:
 * [2,3]
 */
public class FindAllDuplicatesInAnArray {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int n : nums) {
            int absN = Math.abs(n);
            if (nums[absN - 1] < 0) result.add(absN);
            else nums[absN - 1] *= -1;
        }
        return result;
    }

    public static void main(String[] args) {
        FindAllDuplicatesInAnArray f = new FindAllDuplicatesInAnArray();
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        Utils.printList(f.findDuplicates(nums));
    }

}
