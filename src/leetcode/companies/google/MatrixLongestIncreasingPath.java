package leetcode.companies.google;

/**
 * 329. Longest Increasing Path in a Matrix
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 * Example 1:
 *
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 *
 * Input: matrix = [[1]]
 * Output: 1
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 */
public class MatrixLongestIncreasingPath {

    int[][] directions;
    Integer[][] lip;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null) return 0;
        int n = matrix.length;
        if (n == 0) return 0;
        int m = matrix[0].length;
        directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        lip = new Integer[n][m];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, dfs(matrix, n, m, i, j, Integer.MIN_VALUE));
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int n, int m, int i, int j, int prev) {
        if (i < 0 || i >= n || j < 0 || j >= m || matrix[i][j] == Integer.MIN_VALUE || matrix[i][j] <= prev) return 0;
        if (lip[i][j] != null) return lip[i][j];
        int max = 0;
        int temp = matrix[i][j];
        matrix[i][j] = Integer.MIN_VALUE;
        for (int[] dir : directions) max = Math.max(max, dfs(matrix, n, m, i + dir[0], j + dir[1], temp));
        matrix[i][j] = temp;
        return lip[i][j] = max + 1;
    }

}
