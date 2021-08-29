package leetcode.companies.google;

/**
 * 801. Minimum Swaps To Make Sequences Increasing
 * You are given two integer arrays of the same length nums1 and nums2. In one operation, you are allowed to swap nums1[i] with nums2[i].
 *
 * For example, if nums1 = [1,2,3,8], and nums2 = [5,6,7,4], you can swap the element at i = 3 to obtain nums1 = [1,2,3,4] and nums2 = [5,6,7,8].
 * Return the minimum number of needed operations to make nums1 and nums2 strictly increasing. The test cases are generated so that the given input always makes it possible.
 *
 * An array arr is strictly increasing if and only if arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1].
 *
 * Example 1:
 *
 * Input: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
 * Output: 1
 * Explanation:
 * Swap nums1[3] and nums2[3]. Then the sequences are:
 * nums1 = [1, 3, 5, 7] and nums2 = [1, 2, 3, 4]
 * which are both strictly increasing.
 * Example 2:
 *
 * Input: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
 * Output: 1
 *
 * Constraints:
 *
 * 2 <= nums1.length <= 105
 * nums2.length == nums1.length
 * 0 <= nums1[i], nums2[i] <= 2 * 105
 */
public class MinSwap {

    public int minSwap(int[] nums1, int[] nums2) {
        int swap = 1; // one swap at index 0
        int notSwap = 0; // no swap at index 0
        for (int i = 1; i < nums1.length; i++) {
            int newSwap = Integer.MAX_VALUE;
            int newNotSwap = Integer.MAX_VALUE;
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                newSwap = swap + 1; // if you swap i, you have to swap i-1 as well
                newNotSwap = notSwap; // if you don't swap i, you don't need to swap i-1
            }
            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                newSwap = Math.min(newSwap, notSwap + 1); // just swap the i
                newNotSwap = Math.min(newNotSwap, swap); // just swap i-1
            }
            swap = newSwap;
            notSwap = newNotSwap;
        }
        return Math.min(swap, notSwap);
    }

}
