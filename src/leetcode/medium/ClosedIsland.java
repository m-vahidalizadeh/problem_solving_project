package leetcode.medium;

/**
 * 1254. Number of Closed Islands
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 *
 * Return the number of closed islands.
 *
 * Example 1:
 *
 * Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation:
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 * Example 2:
 *
 * Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * Output: 1
 * Example 3:
 *
 * Input: grid = [[1,1,1,1,1,1,1],
 *                [1,0,0,0,0,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,1,0,1,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,0,0,0,0,1],
 *                [1,1,1,1,1,1,1]]
 * Output: 2
 *
 * Constraints:
 *
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 */
public class ClosedIsland {

    int[][] grid;
    int n;
    int m;
    int[][] directions;

    public int closedIsland(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length;
        this.directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (this.grid[i][j] == 0 && isClosed(i, j)) counter++;
            }
        }
        return counter;
    }

    private boolean isClosed(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= m) return false;
        if (grid[i][j] == 1 || grid[i][j] == -1) return true;
        grid[i][j] = -1;
        boolean res = true;
        for (int[] dir : directions) {
            res &= isClosed(i + dir[0], j + dir[1]);
        }
        return res;
    }

}
