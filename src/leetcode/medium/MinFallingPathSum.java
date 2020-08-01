package leetcode.medium;

/**
 * Minimum Falling Path Sum
 * Given a square array of integers A, we want the minimum sum of a falling path through A.
 * <p>
 * A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 12
 * Explanation:
 * The possible falling paths are:
 * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
 * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
 * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
 * The falling path with the smallest sum is [1,4,7], so the answer is 12.
 * <p>
 * Constraints:
 * <p>
 * 1 <= A.length == A[0].length <= 100
 * -100 <= A[i][j] <= 100
 */
public class MinFallingPathSum {

    public int minFallingPathSum(int[][] A) {
        int n = A.length;
        if (n == 0) return 0;
        if (n == 1) return A[0][0];
        int min = Integer.MAX_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                A[i][j] += getMin(A, i + 1, j, n);
                if (i == 0 && A[i][j] < min) min = A[i][j];
            }
        }
        return min;
    }

    private int getMin(int[][] A, int i, int j, int n) {
        int a = j == 0 ? A[i][j] : A[i][j - 1];
        int b = A[i][j];
        int c = j == n - 1 ? A[i][j] : A[i][j + 1];
        return Math.min(a, Math.min(b, c));
    }

}
