package leetcode.companies.amazon;

/**
 * 4. Median of Two Sorted Arrays
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * Follow up: The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * Example 3:
 *
 * Input: nums1 = [0,0], nums2 = [0,0]
 * Output: 0.00000
 * Example 4:
 *
 * Input: nums1 = [], nums2 = [1]
 * Output: 1.00000
 * Example 5:
 *
 * Input: nums1 = [2], nums2 = []
 * Output: 2.00000
 *
 * Constraints:
 *
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            m = nums1.length;
            n = nums2.length;
        }
        int iMin = 0;
        int iMax = m;
        int half = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = half - i;
            if (i < iMax && nums2[j - 1] > nums1[i]) iMin = i + 1;
            else if (i > iMin && nums1[i - 1] > nums2[j]) iMax = i - 1;
            else {
                int maxL = 0;
                if (i == 0) maxL = nums2[j - 1];
                else if (j == 0) maxL = nums1[i - 1];
                else maxL = Math.max(nums1[i - 1], nums2[j - 1]);
                if ((n + m) % 2 == 1) return maxL; // Odd

                int minR = 0;
                if (i == m) minR = nums2[j];
                else if (j == n) minR = nums1[i];
                else minR = Math.min(nums1[i], nums2[j]);
                return (maxL + minR) / 2.0;
            }
        }
        return 0.0;
    }

}
