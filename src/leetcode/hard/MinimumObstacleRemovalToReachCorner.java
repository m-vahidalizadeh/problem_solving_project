package leetcode.hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 2290. Minimum Obstacle Removal to Reach Corner
 * You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:
 *
 * 0 represents an empty cell,
 * 1 represents an obstacle that may be removed.
 * You can move up, down, left, or right from and to an empty cell.
 *
 * Return the minimum number of obstacles to remove so you can move from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1).
 *
 * Example 1:
 *
 * Input: grid = [[0,1,1],[1,1,0],[1,1,0]]
 * Output: 2
 * Explanation: We can remove the obstacles at (0, 1) and (0, 2) to create a path from (0, 0) to (2, 2).
 * It can be shown that we need to remove at least 2 obstacles, so we return 2.
 * Note that there may be other ways to remove 2 obstacles to create a path.
 * Example 2:
 *
 * Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
 * Output: 0
 * Explanation: We can move from (0, 0) to (2, 4) without removing any obstacles, so we return 0.
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 105
 * 2 <= m * n <= 105
 * grid[i][j] is either 0 or 1.
 * grid[0][0] == grid[m - 1][n - 1] == 0
 */
public class MinimumObstacleRemovalToReachCorner {

    public int minimumObstacles(int[][] grid) {
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int M = Integer.MAX_VALUE;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int[] di : dist) Arrays.fill(di, M);
        dist[0][0] = 0;
        Deque<int[]> dq = new ArrayDeque<>();
        dq.addLast(new int[]{dist[0][0], 0, 0});
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int o = cur[0];
            int r = cur[1];
            int c = cur[2];
            for (int[] dir : directions) {
                int i = r + dir[0];
                int j = c + dir[1];
                if (i >= 0 && i < m && j >= 0 && j < n && dist[i][j] == M) {
                    if (grid[i][j] == 1) {
                        dist[i][j] = o + 1;
                        dq.addLast(new int[]{o + 1, i, j});
                    } else {
                        dist[i][j] = o;
                        dq.offerFirst(new int[]{o, i, j});
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }

}
