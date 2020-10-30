package leetcode.companies.amazon;

import java.util.Arrays;

/**
 * 167. Two Sum II - Input array is sorted
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 *
 * Note:
 *
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 *
 * Example 1:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 * Example 2:
 *
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Example 3:
 *
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 *
 * Constraints:
 *
 * 2 <= nums.length <= 3 * 104
 * -1000 <= nums[i] <= 1000
 * nums is sorted in increasing order.
 * -1000 <= target <= 1000
 */
public class TwoSumII {

    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            int num = numbers[i];
            int complement = target - num;
            int index2 = bs(numbers, complement, i + 1, n - 1);
            if (index2 != -1) return new int[]{i + 1, index2 + 1};
            int index3 = bs(numbers, complement, 0, i - 1);
            if (index3 != -1) return new int[]{i + 1, index3 + 1};
        }
        return new int[]{};
    }

    private int bs(int[] numbers, int complement, int s, int e) {
        while (s <= e) {
            int m = s + (e - s) / 2;
            if (numbers[m] == complement) return m;
            else if (numbers[m] < complement) s = m + 1;
            else e = m - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        TwoSumII t = new TwoSumII();
        System.out.println(Arrays.toString(t.twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(t.twoSum(new int[]{2, 3, 4}, 6)));
        System.out.println(Arrays.toString(t.twoSum(new int[]{-1, 0}, -1)));
    }

}
