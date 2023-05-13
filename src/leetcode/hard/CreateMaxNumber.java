package leetcode.hard;

import java.util.Stack;

/**
 * 321. Create Maximum Number
 * You are given two integer arrays nums1 and nums2 of lengths m and n respectively. nums1 and nums2 represent the digits of two numbers. You are also given an integer k.
 *
 * Create the maximum number of length k <= m + n from digits of the two numbers. The relative order of the digits from the same array must be preserved.
 *
 * Return an array of the k digits representing the answer.
 *
 * Example 1:
 *
 * Input: nums1 = [3,4,6,5], nums2 = [9,1,2,5,8,3], k = 5
 * Output: [9,8,6,5,3]
 * Example 2:
 *
 * Input: nums1 = [6,7], nums2 = [6,0,4], k = 5
 * Output: [6,7,6,0,4]
 * Example 3:
 *
 * Input: nums1 = [3,9], nums2 = [8,9], k = 3
 * Output: [9,8,9]
 *
 * Constraints:
 *
 * m == nums1.length
 * n == nums2.length
 * 1 <= m, n <= 500
 * 0 <= nums1[i], nums2[i] <= 9
 * 1 <= k <= m + n
 */
public class CreateMaxNumber {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];
        for (int ki = Math.max(0, k - nums2.length); ki <= Math.min(k, nums1.length); ki++) {
            int[] res1 = getMaxSub(nums1, ki);
            int[] res2 = getMaxSub(nums2, k - ki);
            int[] resTemp = new int[k];
            int p1 = 0, p2 = 0, pt = 0;
            while (p1 < res1.length || p2 < res2.length)
                resTemp[pt++] = isGreater(res1, p1, res2, p2) ? res1[p1++] : res2[p2++];
            if (!isGreater(result, 0, resTemp, 0)) result = resTemp;
        }
        return result;
    }

    private int[] getMaxSub(int[] nums, int cnt) {
        Stack<Integer> stack = new Stack<>();
        int rem = cnt;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peek() < nums[i] && nums.length - (i + 1) >= rem) {
                stack.pop();
                rem++;
            }
            if (rem > 0) {
                stack.push(nums[i]);
                rem--;
            }
        }
        int[] maxSub = new int[cnt];
        int mi = maxSub.length - 1;
        while (!stack.isEmpty()) maxSub[mi--] = stack.pop();
        return maxSub;
    }

    private boolean isGreater(int[] nums1, int p1, int[] nums2, int p2) {
        for (; p1 < nums1.length && p2 < nums2.length; p1++, p2++) {
            if (nums1[p1] > nums2[p2]) return true;
            if (nums1[p1] < nums2[p2]) return false;
        }
        return p2 == nums2.length;
    }

}
