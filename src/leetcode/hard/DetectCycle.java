package leetcode.hard;

/**
 * 1559. Detect Cycles in 2D Grid
 * Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.
 *
 * A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.
 *
 * Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
 *
 * Return true if any cycle of the same value exists in grid, otherwise, return false.
 *
 * Example 1:
 *
 * Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
 * Output: true
 * Explanation: There are two valid cycles shown in different colors in the image below:
 *
 * Example 2:
 *
 * Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
 * Output: true
 * Explanation: There is only one valid cycle highlighted in the image below:
 *
 * Example 3:
 *
 * Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
 * Output: false
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 500
 * 1 <= n <= 500
 * grid consists only of lowercase English letters.
 */
public class DetectCycle {

    int n;
    int m;
    char[][] grid;
    int[][] directions;
    boolean[][] visited;

    public boolean containsCycle(char[][] grid) {
        this.n = grid.length;
        this.m = grid[0].length;
        this.grid = grid;
        this.directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        this.visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && dfs(i, j, -1, -1)) return true;
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int pI, int pJ) {
        visited[i][j] = true;
        for (int[] dir : directions) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (newI < 0 || newI >= n || newJ < 0 || newJ >= m || grid[i][j] != grid[newI][newJ] || (newI == pI && newJ == pJ))
                continue;
            if (visited[newI][newJ]) return true;
            if (dfs(newI, newJ, i, j)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        DetectCycle d = new DetectCycle();
        System.out.println(d.containsCycle(new char[][]{{'a', 'b', 'b'}, {'b', 'z', 'b'}, {'b', 'b', 'a'}}));
    }

}