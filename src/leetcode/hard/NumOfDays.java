package leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1568. Minimum Number of Days to Disconnect Island
 * Given a 2D grid consisting of 1s (land) and 0s (water).  An island is a maximal 4-directionally (horizontal or vertical) connected group of 1s.
 *
 * The grid is said to be connected if we have exactly one island, otherwise is said disconnected.
 *
 * In one day, we are allowed to change any single land cell (1) into a water cell (0).
 *
 * Return the minimum number of days to disconnect the grid.
 *
 * Example 1:
 *
 * Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 2
 * Explanation: We need at least 2 days to get a disconnected grid.
 * Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.
 * Example 2:
 *
 * Input: grid = [[1,1]]
 * Output: 2
 * Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.
 * Example 3:
 *
 * Input: grid = [[1,0,1,0]]
 * Output: 0
 * Example 4:
 *
 * Input: grid = [[1,1,0,1,1],
 *                [1,1,1,1,1],
 *                [1,1,0,1,1],
 *                [1,1,0,1,1]]
 * Output: 1
 * Example 5:
 *
 * Input: grid = [[1,1,0,1,1],
 *                [1,1,1,1,1],
 *                [1,1,0,1,1],
 *                [1,1,1,1,1]]
 * Output: 2
 *
 * Constraints:
 *
 * 1 <= grid.length, grid[i].length <= 30
 * grid[i][j] is 0 or 1.
 */
public class NumOfDays {

    public int minDays(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if (isDisconnected(grid, n, m)) return 0; // If it is originally disconnected
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (isDisconnected(grid, n, m)) return 1; // If we can make it disconnected by flipping one cell
                    grid[i][j] = 1;
                }
            }
        }
        return 2; // At most we need to flip two cells to separate of one of the corners of the rectangle island
    }

    private boolean isDisconnected(int[][] grid, int n, int m) {
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int islandCount = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    islandCount++;
                    if (islandCount > 1) return true;
                    Deque<int[]> q = new ArrayDeque<>();
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                    while (!q.isEmpty()) {
                        int[] curr = q.poll();
                        int x = curr[0];
                        int y = curr[1];
                        for (int[] dir : directions) {
                            int newX = x + dir[0];
                            int newY = y + dir[1];
                            if (newX < 0 || newX >= n || newY < 0 || newY >= m || visited[newX][newY] || grid[newX][newY] == 0) continue;
                            q.add(new int[]{newX, newY});
                            visited[newX][newY] = true;
                        }
                    }
                }
            }
        }
        return islandCount == 0;
    }

}
