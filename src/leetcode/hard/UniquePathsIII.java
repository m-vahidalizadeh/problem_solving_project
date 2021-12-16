package leetcode.hard;

import java.util.Set;

/**
 * 980. Unique Paths III
 * You are given an m x n integer array grid where grid[i][j] could be:
 *
 * 1 representing the starting square. There is exactly one starting square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 *
 * Example 1:
 *
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * Example 2:
 *
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * Example 3:
 *
 * Input: grid = [[0,1],[2,0]]
 * Output: 0
 * Explanation: There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 20
 * 1 <= m * n <= 20
 * -1 <= grid[i][j] <= 2
 * There is exactly one starting cell and one ending cell.
 */
public class UniquePathsIII {

    public int uniquePathsIII(int[][] grid) {
        int empty = 1; // Include the start
        int[] s = new int[]{-1, -1};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) empty++;
                else if (grid[i][j] == 1) s = new int[]{i, j};
            }
        }
        return dfs(grid, s, empty, Set.of(new int[]{0, 1}, new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, -1}));
    }

    private int dfs(int[][] grid, int[] curr, int empty, Set<int[]> directions) { // curr=[x,y]
        if (curr[0] < 0 || curr[0] >= grid.length || curr[1] < 0 || curr[1] >= grid[0].length || grid[curr[0]][curr[1]] < 0)
            return 0;
        if (grid[curr[0]][curr[1]] == 2) {
            if (empty == 0) return 1;
            return 0;
        }
        int ans = 0;
        grid[curr[0]][curr[1]] = -2; // Visited
        empty--;
        for (int[] dir : directions)
            ans += dfs(grid, new int[]{curr[0] + dir[0], curr[1] + dir[1]}, empty, directions);
        grid[curr[0]][curr[1]] = 0;
        empty++;
        return ans;
    }

}
