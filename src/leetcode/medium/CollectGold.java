package leetcode.medium;

/**
 * Path with Maximum Gold
 * In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
 * <p>
 * Return the maximum amount of gold you can collect under the conditions:
 * <p>
 * Every time you are located in a cell you will collect all the gold in that cell.
 * From your position you can walk one step to the left, right, up or down.
 * You can't visit the same cell more than once.
 * Never visit a cell with 0 gold.
 * You can start and stop collecting gold from any position in the grid that has some gold.
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
 * Output: 24
 * Explanation:
 * [[0,6,0],
 * [5,8,7],
 * [0,9,0]]
 * Path to get the maximum gold, 9 -> 8 -> 7.
 * Example 2:
 * <p>
 * Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * Output: 28
 * Explanation:
 * [[1,0,7],
 * [2,0,6],
 * [3,4,5],
 * [0,3,0],
 * [9,0,20]]
 * Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 * <p>
 * Constraints:
 * <p>
 * 1 <= grid.length, grid[i].length <= 15
 * 0 <= grid[i][j] <= 100
 * There are at most 25 cells containing gold.
 */
public class CollectGold {

    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int getMaximumGold(int[][] grid) {
        int n = grid.length;
        if (n == 0) return 0;
        int m = grid[0].length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, dfs(grid, i, j, n, m, 0));
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int i, int j, int n, int m, int sum) {
        if (i < 0 || i == n || j < 0 || j == m || grid[i][j] == 0) return sum;
        sum += grid[i][j];
        int temp = grid[i][j];
        grid[i][j] = 0;
        int max = 0;
        for (int[] direction : directions) {
            max = Math.max(max, dfs(grid, i + direction[0], j + direction[1], n, m, sum));
        }
        grid[i][j] = temp;
        return max;
    }

    public static void main(String[] args) {
        CollectGold c = new CollectGold();
        int[][] grid1 = {{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
        System.out.println(c.getMaximumGold(grid1));
        int[][] grid2 = {{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}};
        System.out.println(c.getMaximumGold(grid2));
    }

}
