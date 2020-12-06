package leetcode.companies.random;

import java.util.HashMap;
import java.util.Map;

/**
 * 1314. Matrix Block Sum
 * Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
 *
 * Example 1:
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
 * Output: [[12,21,16],[27,45,33],[24,39,28]]
 * Example 2:
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
 * Output: [[45,45,45],[45,45,45],[45,45,45]]
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n, K <= 100
 * 1 <= mat[i][j] <= 100
 */
public class MatrixBlockSum {

    int n;
    int m;
    int k;
    int[][] mat;
    Map<String, Integer> rowSumCache;
    Map<String, Integer> colSumCache;

    public int[][] matrixBlockSum(int[][] mat, int K) {
        this.n = mat.length;
        this.m = mat[0].length;
        this.k = K;
        this.mat = mat;
        int[][] result = new int[n][m];
        rowSumCache = new HashMap<>();
        colSumCache = new HashMap<>();
        result[0][0] = getBlockSum(0, 0);
        for (int i = 1; i < n; i++) {
            int sum = result[i - 1][0];
            sum -= getRowSum(i - k - 1, 0);
            sum += getRowSum(i + k, 0);
            result[i][0] = sum;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int sum = result[i][j - 1];
                sum -= getColSum(i, j - k - 1);
                sum += getColSum(i, j + k);
                result[i][j] = sum;
            }
        }
        return result;
    }

    private int getRowSum(int row, int col) {
        if (row < 0 || row >= n) return 0;
        String key = row + "-" + col;
        if (rowSumCache.containsKey(key)) return rowSumCache.get(key);
        int sum = 0;
        for (int j = Math.max(0, col - k); j <= Math.min(m - 1, col + k); j++) {
            sum += mat[row][j];
        }
        rowSumCache.put(key, sum);
        return sum;
    }

    private int getColSum(int row, int col) {
        if (col < 0 || col >= m) return 0;
        String key = row + "-" + col;
        if (colSumCache.containsKey(key)) return colSumCache.get(key);
        int sum = 0;
        for (int i = Math.max(0, row - k); i <= Math.min(n - 1, row + k); i++) {
            sum += mat[i][col];
        }
        colSumCache.put(key, sum);
        return sum;
    }

    private int getBlockSum(int x, int y) {
        int sum = 0;
        for (int i = Math.max(x - k, 0); i <= Math.min(n - 1, x + k); i++) {
            for (int j = Math.max(y - k, 0); j <= Math.min(m - 1, y + k); j++) {
                sum += mat[i][j];
            }
        }
        return sum;
    }

}
