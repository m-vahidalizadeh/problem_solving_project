package leetcode.hard;

import java.util.TreeSet;

/**
 * 363. Max Sum of Rectangle No Larger Than K
 * Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix such that its sum is no larger than k.
 *
 * It is guaranteed that there will be a rectangle with a sum no larger than k.
 *
 * Example 1:
 *
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2
 * Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
 * Example 2:
 *
 * Input: matrix = [[2,2,-1]], k = 3
 * Output: 3
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -105 <= k <= 105
 *
 * Follow up: What if the number of rows is much larger than the number of columns?
 */
public class MaxSumOfRectangleNoLargerThanK {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int[] accumulate = new int[m];
            for (int j = i; j < n; j++) {
                sum(accumulate, matrix[j]);
                max = Math.max(max, helper(accumulate, new TreeSet<>(), k));
                if (max == k) return max;
            }
        }
        return max == Integer.MIN_VALUE ? -1 : max;
    }

    private int helper(int[] add, TreeSet<Integer> treeSet, int k) {
        treeSet.add(0);
        int prefixSum = 0;
        int currMax = Integer.MIN_VALUE;
        for (int ele : add) {
            prefixSum += ele;
            Integer ceil = treeSet.ceiling(prefixSum - k);
            if (ceil != null) {
                if (prefixSum - ceil == k) return k;
                else currMax = Math.max(currMax, prefixSum - ceil);
            }
            treeSet.add(prefixSum);
        }
        return currMax;
    }

    private void sum(int[] add, int[] cols) {
        for (int i = 0; i < cols.length; i++) {
            add[i] += cols[i];
        }
    }

}
