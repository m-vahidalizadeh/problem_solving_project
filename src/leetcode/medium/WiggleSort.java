package leetcode.medium;

/**
 * Wiggle Sort
 * Add to List
 * <p>
 * Share
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * <p>
 * Example:
 * <p>
 * Input: nums = [3,5,2,1,6,4]
 * Output: One possible answer is [3,5,1,6,2,4]
 */
public class WiggleSort {

    public void wiggleSort(int[] nums) {
        boolean lessThanOrEquals = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (lessThanOrEquals) {
                if (nums[i] > nums[i + 1]) swap(nums, i, i + 1);
            } else {
                if (nums[i] < nums[i + 1]) swap(nums, i, i + 1);
            }
            lessThanOrEquals = !lessThanOrEquals;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
