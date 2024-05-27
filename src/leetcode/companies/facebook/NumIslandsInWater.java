package leetcode.companies.facebook;

/**
 * 200. Number of Islands
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
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
public class NumIslandsInWater {

    public int numIslands(char[][] grid) {
        int islandCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    islandCount++;
                    dfs(i, j, grid, new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}});
                }
            }
        }
        return islandCount;
    }

    private void dfs(int i, int j, char[][] grid, int[][] directions) {
        grid[i][j] = '0';
        for (int[] dir : directions) {
            int newI = i + dir[0], newJ = j + dir[1];
            if (newI < 0 || newI >= grid.length || newJ < 0 || newJ >= grid[0].length || grid[newI][newJ] == '0')
                continue;
            dfs(newI, newJ, grid, directions);
        }
    }

}
