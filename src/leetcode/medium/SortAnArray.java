package leetcode.medium;

import java.util.Arrays;

/**
 * Sort an Array
 * Given an array of integers nums, sort the array in ascending order.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 * Example 2:
 * <p>Synonymous Sentences
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 50000
 * -50000 <= nums[i] <= 50000
 */
public class SortAnArray {

    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

    public static void main(String[] args) {
        SortAnArray s = new SortAnArray();
        int[] nums = {5, 2, 3, 1};
        int[] sortedArray = s.sortArray(nums);
        for (int i = 0; i < sortedArray.length; i++) {
            System.out.print(sortedArray[i] + " ");
        }
    }

}
