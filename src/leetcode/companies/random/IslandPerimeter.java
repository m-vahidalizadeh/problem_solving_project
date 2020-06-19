package leetcode.companies.random;

/**
 * Island Perimeter
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 * <p>
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 * <p>
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 * <p>
 * Example:
 * <p>
 * Input:
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * <p>
 * Output: 16
 * <p>
 * Explanation: The perimeter is the 16 yellow stripes in the image below:
 */
public class IslandPerimeter {

    public int islandPerimeter(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean found = false;
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (found) break;
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    p = findPerimeter(grid, i, j, n, m);
                    found = true;
                    break;
                }
            }
        }
        return p;
    }

    private int findPerimeter(int[][] grid, int i, int j, int n, int m) {
        if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] != 1) return 0;
        int num = findNumber(grid, i, j, n, m);
        grid[i][j]++;
        return num
                + findPerimeter(grid, i - 1, j, n, m)
                + findPerimeter(grid, i + 1, j, n, m)
                + findPerimeter(grid, i, j - 1, n, m)
                + findPerimeter(grid, i, j + 1, n, m);

    }

    private int findNumber(int[][] grid, int i, int j, int n, int m) {
        int up = i - 1 < 0 || grid[i - 1][j] == 0 ? 1 : 0;
        int down = i + 1 >= n || grid[i + 1][j] == 0 ? 1 : 0;
        int left = j - 1 < 0 || grid[i][j - 1] == 0 ? 1 : 0;
        int right = j + 1 >= m || grid[i][j + 1] == 0 ? 1 : 0;
        return up + down + left + right;
    }

    public static void main(String[] args) {
        IslandPerimeter i = new IslandPerimeter();
        int[][] grid = {{0, 1}};
        System.out.println(i.islandPerimeter(grid));
    }

}
