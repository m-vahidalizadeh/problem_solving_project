package leetcode.companies.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * 1074. Number of Submatrices That Sum to Target
 * Given a matrix and a target, return the number of non-empty submatrices that sum to target.
 *
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
 *
 * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.
 *
 * Example 1:
 *
 * Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * Output: 4
 * Explanation: The four 1x1 submatrices that only contain 0.
 * Example 2:
 *
 * Input: matrix = [[1,-1],[-1,1]], target = 0
 * Output: 5
 * Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
 * Example 3:
 *
 * Input: matrix = [[904]], target = 0
 * Output: 0
 *
 * Constraints:
 *
 * 1 <= matrix.length <= 100
 * 1 <= matrix[0].length <= 100
 * -1000 <= matrix[i] <= 1000
 * -10^8 <= target <= 10^8
 */
public class SubMatrixSum {

    public int numSubMatrixSumTarget(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] colSum = new int[n][m];
        for (int j = 0; j < m; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += matrix[i][j];
                colSum[i][j] = sum;
            }
        }
        int res = 0;
        for (int i1 = 0; i1 < n; i1++) {
            for (int i2 = i1; i2 < n; i2++) {
                int sum = 0;
                Map<Integer, Integer> map = new HashMap<>();
                for (int j = 0; j < m; j++) {
                    int prev = i1 == 0 ? 0 : colSum[i1 - 1][j];
                    int add = colSum[i2][j] - prev;
                    sum = sum + add;
                    if (sum == target) res++;
                    int offset = sum - target;
                    res = res + map.getOrDefault(offset, 0);
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SubMatrixSum s = new SubMatrixSum();
        System.out.println(s.numSubMatrixSumTarget(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0));
        System.out.println(s.numSubMatrixSumTarget(new int[][]{{1, -1}, {-1, 1}}, 0));
        System.out.println(s.numSubMatrixSumTarget(new int[][]{{904}}, 0));
    }

}
