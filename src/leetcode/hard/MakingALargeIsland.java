package leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 827. Making A Large Island
 * In a 2D grid of 0s and 1s, we change at most one 0 to a 1.
 *
 * After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).
 *
 * Example 1:
 *
 * Input: [[1, 0], [0, 1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: [[1, 1], [1, 0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 *
 * Input: [[1, 1], [1, 1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 *
 * Notes:
 *
 * 1 <= grid.length = grid[0].length <= 50.
 * 0 <= grid[i][j] <= 1.
 */
public class MakingALargeIsland {

    int n;
    int m;
    int[][] grid;
    int[][] directions;

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length;
        this.directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int color = 2;
        Map<Integer, Integer> islandSize = new HashMap<>();
        int max = 0;
        for (int i = 0; i < n; i++) { // coloring- Pass one
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int size = paintAndGetSize(i, j, color);
                    max = Math.max(max, size);
                    islandSize.put(color++, size);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) { // Convert a zero to one and count- pass two
                if (grid[i][j] == 0) {
                    Set<Integer> neighborColors = new HashSet<>();
                    for (int[] dir : directions) { // We gather the color of their neighborColors
                        int newI = i + dir[0];
                        int newJ = j + dir[1];
                        if (newI >= 0 && newI < n && newJ >= 0 && newJ < m && grid[newI][newJ] > 0)
                            neighborColors.add(grid[newI][newJ]);
                    }
                    int sum = 1;
                    for (int island : neighborColors) { // Count the size of neighbor islands + 1 (0 converted to 1)
                        sum += islandSize.get(island);
                    }
                    max = Math.max(max, sum);
                }
            }
        }
        return max;
    }

    private int paintAndGetSize(int i, int j, int color) { // Paint the island and return its size
        if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] == 0 || grid[i][j] > 1) return 0;
        grid[i][j] = color;
        int sum = 1;
        for (int[] dir : directions) sum += paintAndGetSize(i + dir[0], j + dir[1], color);
        return sum;
    }

    public static void main(String[] args) {
        MakingALargeIsland m = new MakingALargeIsland();
        System.out.println(m.largestIsland(new int[][]{{0, 0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 1, 0, 0}, {1, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0}}));
    }

}
