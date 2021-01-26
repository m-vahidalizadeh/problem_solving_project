package leetcode.hard;

import java.util.*;

/**
 * 1293. Shortest Path in a Grid with Obstacles Elimination
 * Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.
 *
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 *
 * Example 1:
 *
 * Input:
 * grid =
 * [[0,0,0],
 *  [1,1,0],
 *  [0,0,0],
 *  [0,1,1],
 *  [0,0,0]],
 * k = 1
 * Output: 6
 * Explanation:
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 *
 * Example 2:
 *
 * Input:
 * grid =
 * [[0,1,1],
 *  [1,1,1],
 *  [1,0,0]],
 * k = 1
 * Output: -1
 * Explanation:
 * We need to eliminate at least two obstacles to find such a walk.
 *
 * Constraints:
 *
 * grid.length == m
 * grid[0].length == n
 * 1 <= m, n <= 40
 * 1 <= k <= m*n
 * grid[i][j] == 0 or 1
 * grid[0][0] == grid[m-1][n-1] == 0
 */
public class ShortestPath {

    public int shortestPath(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int[][] obstacles = new int[n][m];
        for (int[] row : obstacles) Arrays.fill(row, Integer.MAX_VALUE);
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 0});
        obstacles[0][0] = 0;
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int z = 0; z < size; z++) {
                int[] curr = q.poll();
                int i = curr[0];
                int j = curr[1];
                if (i == n - 1 && j == m - 1) return steps;
                for (int[] dir : directions) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];
                    if (newI < 0 || newI >= n || newJ < 0 || newJ >= m) continue;
                    int o = curr[2] + grid[newI][newJ];
                    if (o >= obstacles[newI][newJ] || o > k) continue;
                    obstacles[newI][newJ] = o;
                    q.add(new int[]{newI, newJ, o});
                }
            }
            steps++;
        }
        return -1;
    }

}
