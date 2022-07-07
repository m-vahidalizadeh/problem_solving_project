package leetcode.hard;

/**
 * Submissions
 * 2328. Number of Increasing Paths in a Grid
 * You are given an m x n integer matrix grid, where you can move from a cell to any adjacent cell in all 4 directions.
 *
 * Return the number of strictly increasing paths in the grid such that you can start from any cell and end at any cell. Since the answer may be very large, return it modulo 109 + 7.
 *
 * Two paths are considered different if they do not have exactly the same sequence of visited cells.
 *
 * Example 1:
 *
 * Input: grid = [[1,1],[3,4]]
 * Output: 8
 * Explanation: The strictly increasing paths are:
 * - Paths with length 1: [1], [1], [3], [4].
 * - Paths with length 2: [1 -> 3], [1 -> 4], [3 -> 4].
 * - Paths with length 3: [1 -> 3 -> 4].
 * The total number of paths is 4 + 3 + 1 = 8.
 * Example 2:
 *
 * Input: grid = [[1],[2]]
 * Output: 3
 * Explanation: The strictly increasing paths are:
 * - Paths with length 1: [1], [2].
 * - Paths with length 2: [1 -> 2].
 * The total number of paths is 2 + 1 = 3.
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 1000
 * 1 <= m * n <= 105
 * 1 <= grid[i][j] <= 105
 */
public class NumberOfIncreasingPathsInAGrid {

    public int countPaths(int[][] grid) {
        int m = grid.length, n = grid[0].length, mod = (int) (1e9 + 7), ans = 0;
        Integer[][] path = new Integer[m][n], directions = new Integer[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                ans = (ans + dfs(grid, i, j, -1, path, directions, m, n, mod)) % mod;
        return ans;
    }

    private int dfs(int[][] grid, int i, int j, int pre, Integer[][] path, Integer[][] directions, int m, int n, int mod) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] <= pre) return 0; // Invalid
        if (path[i][j] != null) return path[i][j]; // Already explored
        int numPaths = 0;
        for (Integer[] dir : directions)
            numPaths = (numPaths + dfs(grid, i + dir[0], j + dir[1], grid[i][j], path, directions, m, n, mod)) % mod;
        return path[i][j] = (1 + numPaths) % mod; // 1 for the current cell
    }

}
