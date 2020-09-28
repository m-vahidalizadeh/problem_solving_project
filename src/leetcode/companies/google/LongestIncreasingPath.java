package leetcode.companies.google;

/**
 * Longest Increasing Path in a Matrix
 * Given an integer matrix, find the length of the longest increasing path.
 * <p>
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * <p>
 * Example 1:
 * <p>
 * Input: nums =
 * [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 * <p>
 * Input: nums =
 * [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPath {

    int[][] directions;
    Integer[][] cache;

    public int longestIncreasingPath(int[][] matrix) {
        directions = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        int max = 0;
        int n = matrix.length;
        if (n == 0) return 0;
        int m = matrix[0].length;
        cache = new Integer[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, dfs(matrix, i, j, n, m, Integer.MIN_VALUE, cache));
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int i, int j, int n, int m, int prev, Integer[][] cache) {
        if (i < 0 || i >= n || j < 0 || j >= m || matrix[i][j] == Integer.MIN_VALUE || matrix[i][j] <= prev) return 0;
        if (cache[i][j] != null) return cache[i][j];
        int max = 0;
        int temp = matrix[i][j];
        matrix[i][j] = Integer.MIN_VALUE;
        for (int[] direction : directions)
            max = Math.max(max, dfs(matrix, i + direction[0], j + direction[1], n, m, temp, cache));
        matrix[i][j] = temp;
        int result = max + 1;
        cache[i][j] = result;
        return result;
    }

    public static void main(String[] args) {
        LongestIncreasingPath l = new LongestIncreasingPath();
        System.out.println(l.longestIncreasingPath(new int[][]{
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        }));
        System.out.println(l.longestIncreasingPath(new int[][]{
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        }));
    }

}
