package leetcode.hard;

import java.util.*;

/**
 * 2617. Minimum Number of Visited Cells in a Grid
 * You are given a 0-indexed m x n integer matrix grid. Your initial position is at the top-left cell (0, 0).
 *
 * Starting from the cell (i, j), you can move to one of the following cells:
 *
 * Cells (i, k) with j < k <= grid[i][j] + j (rightward movement), or
 * Cells (k, j) with i < k <= grid[i][j] + i (downward movement).
 * Return the minimum number of cells you need to visit to reach the bottom-right cell (m - 1, n - 1). If there is no valid path, return -1.
 *
 * Example 1:
 *
 * Input: grid = [[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]
 * Output: 4
 * Explanation: The image above shows one of the paths that visits exactly 4 cells.
 * Example 2:
 *
 * Input: grid = [[3,4,2,1],[4,2,1,1],[2,1,1,0],[3,4,1,0]]
 * Output: 3
 * Explanation: The image above shows one of the paths that visits exactly 3 cells.
 * Example 3:
 *
 * Input: grid = [[2,1,0],[1,0,0]]
 * Output: -1
 * Explanation: It can be proven that no path exists.
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 105
 * 0 <= grid[i][j] < m * n
 * grid[m - 1][n - 1] == 0
 */
public class MinNumberOfVisitedCellsInAGrid {

    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        TreeSet<Integer>[] s0 = new TreeSet[m], s1 = new TreeSet[n];
        for (int i = 0; i < m; i++) {
            s0[i] = new TreeSet<>();
            for (int j = 0; j < n; j++) s0[i].add(j);
        }
        for (int j = 0; j < n; j++) {
            s1[j] = new TreeSet<>();
            for (int i = 0; i < m; i++) s1[j].add(i);
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 1});
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int i = cell[0], j = cell[1], d = cell[2];
            if (i == m - 1 && j == n - 1) return d;
            Integer k = s0[i].ceiling(j + 1);
            while (k != null && k <= j + grid[i][j]) {
                q.offer(new int[]{i, k.intValue(), d + 1});
                s0[i].remove(k);
                s1[k.intValue()].remove(i);
                k = s0[i].ceiling(j + 1);
            }
            k = s1[j].ceiling(i + 1);
            while (k != null && k <= i + grid[i][j]) {
                q.offer(new int[]{k.intValue(), j, d + 1});
                s1[j].remove(k);
                s0[k.intValue()].remove(j);
                k = s1[j].ceiling(i + 1);
            }
        }
        return -1;
    }

}
