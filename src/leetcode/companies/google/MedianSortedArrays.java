package leetcode.companies.google;

/**
 * 4. Median of Two Sorted Arrays
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
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
public class MedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int n = n1 + n2;
        double median = 0;
        int count = 0;
        int i1 = 0;
        int i2 = 0;
        if (n % 2 == 0) { // Even
            while (count < n / 2 - 1) {
                int e1 = i1 == n1 ? Integer.MAX_VALUE : nums1[i1];
                int e2 = i2 == n2 ? Integer.MAX_VALUE : nums2[i2];
                if (e1 < e2) i1++;
                else i2++;
                count++;
            }
            int a = 0;
            int b = 0;
            int e1 = i1 == n1 ? Integer.MAX_VALUE : nums1[i1];
            int e2 = i2 == n2 ? Integer.MAX_VALUE : nums2[i2];
            if (e1 < e2) {
                a = e1;
                i1++;
            } else {
                a = e2;
                i2++;
            }
            e1 = i1 == n1 ? Integer.MAX_VALUE : nums1[i1];
            e2 = i2 == n2 ? Integer.MAX_VALUE : nums2[i2];
            if (e1 < e2) {
                b = e1;
                i1++;
            } else {
                b = e2;
                i2++;
            }
            median = (a + b) / 2.0;
        } else { // Odd
            while (count < n / 2) {
                int e1 = i1 == n1 ? Integer.MAX_VALUE : nums1[i1];
                int e2 = i2 == n2 ? Integer.MAX_VALUE : nums2[i2];
                if (e1 < e2) i1++;
                else i2++;
                count++;
            }
            int e1 = i1 == n1 ? Integer.MAX_VALUE : nums1[i1];
            int e2 = i2 == n2 ? Integer.MAX_VALUE : nums2[i2];
            if (e1 < e2) median = nums1[i1];
            else median = nums2[i2];
        }
        return median;
    }

}
