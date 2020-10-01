package leetcode.hard;

import java.util.*;

/**
 * Strange Printer II
 * There is a strange printer with the following two special requirements:
 * <p>
 * On each turn, the printer will print a solid rectangular pattern of a single color on the grid. This will cover up the existing colors in the rectangle.
 * Once the printer has used a color for the above operation, the same color cannot be used again.
 * You are given a m x n matrix targetGrid, where targetGrid[row][col] is the color in the position (row, col) of the grid.
 * <p>
 * Return true if it is possible to print the matrix targetGrid, otherwise, return false.
 * <p>
 * Example 1:
 * <p>
 * Input: targetGrid = [[1,1,1,1],[1,2,2,1],[1,2,2,1],[1,1,1,1]]
 * Output: true
 * Example 2:
 * <p>
 * Input: targetGrid = [[1,1,1,1],[1,1,3,3],[1,1,3,4],[5,5,1,4]]
 * Output: true
 * Example 3:
 * <p>
 * Input: targetGrid = [[1,2,1],[2,1,2],[1,2,1]]
 * Output: false
 * Explanation: It is impossible to form targetGrid because it is not allowed to print the same color in different turns.
 * Example 4:
 * <p>
 * Input: targetGrid = [[1,1,1],[3,1,3]]
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * m == targetGrid.length
 * n == targetGrid[i].length
 * 1 <= m, n <= 60
 * 1 <= targetGrid[row][col] <= 60
 */
public class StrangePrinterII {

    int n;
    int m;

    private boolean canRemove(int[][] targetGrid, int color) {
        int minx = n;
        int maxx = -1;
        int miny = m;
        int maxy = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (targetGrid[i][j] == color) {
                    minx = Math.min(minx, i);
                    maxx = Math.max(maxx, i);
                    miny = Math.min(miny, j);
                    maxy = Math.max(maxy, j);
                }
            }
        }
        for (int i = minx; i <= maxx; i++) {
            for (int j = miny; j <= maxy; j++) {
                if (targetGrid[i][j] != color && targetGrid[i][j] != 0) return false;
            }
        }
        for (int i = minx; i <= maxx; i++) {
            for (int j = miny; j <= maxy; j++) {
                targetGrid[i][j] = 0;
            }
        }
        return true;
    }

    public boolean isPrintable(int[][] targetGrid) {
        n = targetGrid.length;
        m = targetGrid[0].length;
        Set<Integer> colors = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                colors.add(targetGrid[i][j]);
            }
        }
        while (!colors.isEmpty()) {
            boolean anyRemoval = false;
            Iterator<Integer> it = colors.iterator();
            while (it.hasNext()) {
                int color = it.next();
                if (canRemove(targetGrid, color)) {
                    it.remove();
                    anyRemoval = true;
                }
            }
            if (!anyRemoval) break;
        }
        return colors.isEmpty();
    }

    public static void main(String[] args) {
        StrangePrinterII s = new StrangePrinterII();
        System.out.println(s.isPrintable(new int[][]{{1, 1, 1, 1}, {1, 2, 2, 1}, {1, 2, 2, 1}, {1, 1, 1, 1}}));
        System.out.println(s.isPrintable(new int[][]{{1, 1, 1, 1}, {1, 1, 3, 3}, {1, 1, 3, 4}, {5, 5, 1, 4}}));
        System.out.println(s.isPrintable(new int[][]{{1, 2, 1}, {2, 1, 2}, {1, 2, 1}}));
        System.out.println(s.isPrintable(new int[][]{{1, 1, 1}, {3, 1, 3}}));
    }

}
