package leetcode.companies.google;

/**
 * 31. Next Permutation
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 *
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 *
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * Example 4:
 *
 * Input: nums = [1]
 * Output: [1]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class NextPerm {

    public boolean nextPermutation(int[] arr) {
        int n = arr.length;
        int i;
        for (i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) break;
        }
        if (i == -1) {
            reverse(arr, 0, n - 1);
            return false;
        }
        int j = n - 1;
        while (arr[j] <= arr[i]) j--;
        swap(arr, i, j);
        reverse(arr, i + 1, n - 1);
        return true;
    }

    private void reverse(int[] arr, int start, int end) {
        while (start < end) swap(arr, start++, end--);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

}
