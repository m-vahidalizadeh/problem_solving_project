package leetcode.companies.google;

/**
 * 221. Maximal Square
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * Example 1:
 *
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 * Example 2:
 *
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 * Example 3:
 *
 * Input: matrix = [["0"]]
 * Output: 0
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 */
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        boolean hasOne = false;
        int l = 1;
        int[][] directions = new int[][]{{-1, 0}, {0, -1}, {-1, -1}};
        int[][] scores = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    scores[i][j] = 1;
                    hasOne = true;
                }
            }
        }
        if (!hasOne) return 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    Integer tempL = null;
                    for (int[] dir : directions) {
                        int newI = dir[0] + i;
                        int newJ = dir[1] + j;
                        if (newI < 0 || newJ < 0) tempL = 0;
                        else {
                            if (tempL == null) tempL = scores[newI][newJ];
                            else tempL = Math.min(tempL, scores[newI][newJ]);
                        }
                    }
                    scores[i][j] = Math.max(scores[i][j], tempL + 1);
                    l = Math.max(l, scores[i][j]);
                }
            }
        }
        return l * l;
    }

}
