package leetcode.medium;

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
public class NextPermutation {

    public boolean nextPermutation(int[] arr) {
        int n = arr.length;
        int p = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                p = i;
                break;
            }
        }
        if (p == -1) {
            reverse(arr, 0, n - 1);
            return false;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (arr[p] < arr[i]) {
                swap(arr, p, i);
                break;
            }
        }
        reverse(arr, p + 1, n - 1);
        return true;
    }

    private void reverse(int[] arr, int s, int e) {
        while (s < e) swap(arr, s++, e--);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
