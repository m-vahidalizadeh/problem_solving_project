package leetcode.companies.amazon;

/**
 * 200. Number of Islands
 * Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 */
public class IslandNumbers {

    int[][] directions;

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ('1' == grid[i][j]) {
                    count++;
                    dfs(grid, i, j, n, m);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j, int n, int m) {
        if (i < 0 || i >= n || j < 0 || j >= m || '1' != grid[i][j]) return;
        grid[i][j] = '0';
        for (int[] dir : directions) {
            dfs(grid, i + dir[0], j + dir[1], n, m);
        }
    }

}
