package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 1001. Grid Illumination
 * There is a 2D grid of size n x n where each cell of this grid has a lamp that is initially turned off.
 *
 * You are given a 2D array of lamp positions lamps, where lamps[i] = [rowi, coli] indicates that the lamp at grid[rowi][coli] is turned on. Even if the same lamp is listed more than once, it is turned on.
 *
 * When a lamp is turned on, it illuminates its cell and all other cells in the same row, column, or diagonal.
 *
 * You are also given another 2D array queries, where queries[j] = [rowj, colj]. For the jth query, determine whether grid[rowj][colj] is illuminated or not. After answering the jth query, turn off the lamp at grid[rowj][colj] and its 8 adjacent lamps if they exist. A lamp is adjacent if its cell shares either a side or corner with grid[rowj][colj].
 *
 * Return an array of integers ans, where ans[j] should be 1 if the cell in the jth query was illuminated, or 0 if the lamp was not.
 *
 * Example 1:
 *
 * Input: n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
 * Output: [1,0]
 * Explanation: We have the initial grid with all lamps turned off. In the above picture we see the grid after turning on the lamp at grid[0][0] then turning on the lamp at grid[4][4].
 * The 0th query asks if the lamp at grid[1][1] is illuminated or not (the blue square). It is illuminated, so set ans[0] = 1. Then, we turn off all lamps in the red square.
 *
 * The 1st query asks if the lamp at grid[1][0] is illuminated or not (the blue square). It is not illuminated, so set ans[1] = 0. Then, we turn off all lamps in the red rectangle.
 *
 * Example 2:
 *
 * Input: n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
 * Output: [1,1]
 * Example 3:
 *
 * Input: n = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
 * Output: [1,1,0]
 *
 * Constraints:
 *
 * 1 <= n <= 109
 * 0 <= lamps.length <= 20000
 * 0 <= queries.length <= 20000
 * lamps[i].length == 2
 * 0 <= rowi, coli < n
 * queries[j].length == 2
 * 0 <= rowj, colj < n
 */
public class GridIllumination {

    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        int[][] directions = new int[][]{{0, 0}, {0, 1}, {0, -1}, {1, 0}, {1, -1}, {1, 1}, {-1, 0}, {-1, 1}, {-1, -1}};
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> column = new HashMap<>();
        Map<Integer, Integer> diagonal = new HashMap<>();
        Map<Integer, Integer> antiDiagonal = new HashMap<>();
        Map<Integer, Integer> onOff = new HashMap<>();
        for (int[] lamp : lamps) {
            int x = lamp[0];
            int y = lamp[1];
            row.put(x, row.getOrDefault(x, 0) + 1);
            column.put(y, column.getOrDefault(y, 0) + 1);
            diagonal.put(x - y, diagonal.getOrDefault(x - y, 0) + 1);
            antiDiagonal.put(x + y, antiDiagonal.getOrDefault(x + y, 0) + 1);
            onOff.put(x * n + y, onOff.getOrDefault(n * x + y, 0) + 1);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            ans[i] = (row.getOrDefault(x, 0) > 0 || column.getOrDefault(y, 0) > 0
                    || diagonal.getOrDefault(x - y, 0) > 0 || antiDiagonal.getOrDefault(x + y, 0) > 0) ? 1 : 0;
            for (int[] dir : directions) {
                int x1 = x + dir[0];
                int y1 = y + dir[1];
                if (x1 >= 0 && y1 >= 0 && x1 < n && y1 < n && onOff.containsKey(n * x1 + y1)) {
                    int times = onOff.get(n * x1 + y1);
                    row.put(x1, row.getOrDefault(x1, 1) - times);
                    column.put(y1, column.getOrDefault(y1, 1) - times);
                    diagonal.put(x1 - y1, diagonal.getOrDefault(x1 - y1, 1) - times);
                    antiDiagonal.put(x1 + y1, antiDiagonal.getOrDefault(x1 + y1, 1) - times);
                    onOff.remove(n * x1 + y1);
                }
            }
        }
        return ans;
    }

}
