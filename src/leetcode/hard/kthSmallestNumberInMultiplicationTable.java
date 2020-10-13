package leetcode.hard;

/**
 * 668. Kth Smallest Number in Multiplication Table
 * Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?
 *
 * Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.
 *
 * Example 1:
 *
 * Input: m = 3, n = 3, k = 5
 * Output:
 * Explanation:
 * The Multiplication Table:
 * 1	2	3
 * 2	4	6
 * 3	6	9
 *
 * The 5-th smallest number is 3 (1, 2, 2, 3, 3).
 * Example 2:
 *
 * Input: m = 2, n = 3, k = 6
 * Output:
 * Explanation:
 * The Multiplication Table:
 * 1	2	3
 * 2	4	6
 *
 * The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
 * Note:
 *
 * The m and n will be in the range [1, 30000].
 * The k will be in the range [1, m * n]
 */
public class kthSmallestNumberInMultiplicationTable {

    public int findKthNumber(int m, int n, int k) {
        int l = 1;
        int h = m * n;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int smallerOrEqualsMidCount = getSmallerOrEqualsCount(m, n, mid);
            if (smallerOrEqualsMidCount >= k) h = mid - 1; // Choose left half
            else l = mid + 1; // Choose right half
        }
        return l; // We found the element. h passed it. l is still on the element. We stop when h<l, default h=mid-1.
    }

    private int getSmallerOrEqualsCount(int m, int n, int x) { // Find the number of smaller or equals x in the table.
        int count = 0;
        for (int i = 1; i <= m; i++) {
            int smallerOrEqualsInMidRow = x / i;
            count += Math.min(n, smallerOrEqualsInMidRow); // In the rows which include x, don't add all n columns.
        }
        return count;
    }

}
