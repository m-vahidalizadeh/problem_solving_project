package leetcode.companies.google;

/**
 * Median of Two Sorted Arrays
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * Follow up: The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 * <p>
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * Example 3:
 * <p>
 * Input: nums1 = [0,0], nums2 = [0,0]
 * Output: 0.00000
 * Example 4:
 * <p>
 * Input: nums1 = [], nums2 = [1]
 * Output: 1.00000
 * Example 5:
 * <p>
 * Input: nums1 = [2], nums2 = []
 * Output: 2.00000
 * <p>
 * Constraints:
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > n) {
            int[] t1 = nums1;
            nums1 = nums2;
            nums2 = t1;
            int t2 = m;
            m = n;
            n = t2;
        }
        int iMin = 0, iMax = m, half = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = half - i;
            if (i < iMax && nums2[j - 1] > nums1[i]) iMin = i + 1; // Too small
            else if (i > iMin && nums1[i - 1] > nums2[j]) iMax = i - 1; // Too big
            else {// Found
                int leftMax = 0;
                if (i == 0) leftMax = nums2[j - 1];
                else if (j == 0) leftMax = nums1[i - 1];
                else leftMax = Math.max(nums1[i - 1], nums2[j - 1]);
                if ((m + n) % 2 != 0) return leftMax; // m+n is odd
                int rightMax = 0;
                if (i == m) rightMax = nums2[j];
                else if (j == n) rightMax = nums1[i];
                else rightMax = Math.min(nums1[i], nums2[j]);
                return (leftMax + rightMax) / 2.0; // m+n is even
            }
        }
        return 0.0;
    }

}
