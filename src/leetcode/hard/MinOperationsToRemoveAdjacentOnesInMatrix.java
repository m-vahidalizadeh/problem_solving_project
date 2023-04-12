package leetcode.hard;

import java.util.Arrays;

/**
 * 2123. Minimum Operations to Remove Adjacent Ones in Matrix
 * You are given a 0-indexed binary matrix grid. In one operation, you can flip any 1 in grid to be 0.
 *
 * A binary matrix is well-isolated if there is no 1 in the matrix that is 4-directionally connected (i.e., horizontal and vertical) to another 1.
 *
 * Return the minimum number of operations to make grid well-isolated.
 *
 * Example 1:
 *
 * Input: grid = [[1,1,0],[0,1,1],[1,1,1]]
 * Output: 3
 * Explanation: Use 3 operations to change grid[0][1], grid[1][2], and grid[2][1] to 0.
 * After, no more 1's are 4-directionally connected and grid is well-isolated.
 * Example 2:
 *
 * Input: grid = [[0,0,0],[0,0,0],[0,0,0]]
 * Output: 0
 * Explanation: There are no 1's in grid and it is well-isolated.
 * No operations were done so return 0.
 * Example 3:
 *
 * Input: grid = [[0,1],[1,0]]
 * Output: 0
 * Explanation: None of the 1's are 4-directionally connected and grid is well-isolated.
 * No operations were done so return 0.
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is either 0 or 1.
 */
public class MinOperationsToRemoveAdjacentOnesInMatrix {

    int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int m, n;

    public int minimumOperations(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int ans = 0, count = 0;
        int[][] match = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(match[i], -1);
        int[][] seen = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && dfs(i, j, ++count, grid, match, seen)) ans++;
            }
        }
        return ans / 2;
    }

    private boolean dfs(int i, int j, int visited, int[][] grid, int[][] match, int[][] seen) {
        if (seen[i][j] == visited) return false;
        seen[i][j] = visited;
        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0 || seen[x][y] == visited) continue;
            if (match[x][y] == -1 || dfs(match[x][y] / n, match[x][y] % n, visited, grid, match, seen)) {
                match[x][y] = i * n + j;
                return true;
            }
        }
        return false;
    }

}
