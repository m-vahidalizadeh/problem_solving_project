package leetcode.hard;

/**
 * 1458. Max Dot Product of Two Subsequences
 * Given two arrays nums1 and nums2.
 *
 * Return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.
 *
 * A subsequence of a array is a new array which is formed from the original array by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, [2,3,5] is a subsequence of [1,2,3,4,5] while [1,5,3] is not).
 *
 * Example 1:
 *
 * Input: nums1 = [2,1,-2,5], nums2 = [3,0,-6]
 * Output: 18
 * Explanation: Take subsequence [2,-2] from nums1 and subsequence [3,-6] from nums2.
 * Their dot product is (2*3 + (-2)*(-6)) = 18.
 * Example 2:
 *
 * Input: nums1 = [3,-2], nums2 = [2,-6,7]
 * Output: 21
 * Explanation: Take subsequence [3] from nums1 and subsequence [7] from nums2.
 * Their dot product is (3*7) = 21.
 * Example 3:
 *
 * Input: nums1 = [-1,-1], nums2 = [1,1]
 * Output: -1
 * Explanation: Take subsequence [-1] from nums1 and subsequence [1] from nums2.
 * Their dot product is -1.
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 500
 * -1000 <= nums1[i], nums2[i] <= 1000
 */
public class MaxDotProductOfTwoSubs {

    int l1;
    int l2;
    Integer[][] cache;
    int[] nums1;
    int[] nums2;

    public int maxDotProduct(int[] nums1, int[] nums2) {
        l1 = nums1.length;
        l2 = nums2.length;
        cache = new Integer[l1][l2];
        this.nums1 = nums1;
        this.nums2 = nums2;
        return rec(0, 0);
    }

    private int rec(int i, int j) {
        if (i == l1 || j == l2) return Integer.MIN_VALUE;
        if (cache[i][j] != null) return cache[i][j];
        int skipI = rec(i + 1, j);
        int skipJ = rec(i, j + 1);
        int skipBothIAndJ = rec(i + 1, j + 1);
        int useBothIAndJ = nums1[i] * nums2[j] + Math.max(0, skipBothIAndJ);
        cache[i][j] = Math.max(Math.max(skipI, skipJ), Math.max(skipBothIAndJ, useBothIAndJ));
        return cache[i][j];
    }

}
