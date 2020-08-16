package leetcode.companies.google;

import java.util.Arrays;

/**
 * Next Permutation
 * <p>
 * Solution
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * <p>
 * The replacement must be in-place and use only constant extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * <p>
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int d = n - 1;
        while (d >= 1 && nums[d - 1] >= nums[d]) d--;
        if (d > 0) {
            int swapIndex = n - 1;
            while (swapIndex >= 0 && nums[swapIndex] <= nums[d - 1]) swapIndex--;
            int temp = nums[d - 1];
            nums[d - 1] = nums[swapIndex];
            nums[swapIndex] = temp;
        }
        Arrays.sort(nums, d, n);
    }

    public static void main(String[] args) {
        NextPermutation n = new NextPermutation();
        int[] nums1 = {1, 2, 3};
        n.nextPermutation(nums1);
        int[] nums2 = {3, 2, 1};
        n.nextPermutation(nums2);
        int[] nums3 = {1, 1, 5};
        n.nextPermutation(nums3);
        int[] nums4 = {1, 3, 2};
        n.nextPermutation(nums4);
        int[] nums5 = {2, 3, 1};
        n.nextPermutation(nums5);
    }

}
