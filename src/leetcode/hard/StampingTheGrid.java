package leetcode.hard;

/**
 * 2132. Stamping the Grid
 * You are given an m x n binary matrix grid where each cell is either 0 (empty) or 1 (occupied).
 *
 * You are then given stamps of size stampHeight x stampWidth. We want to fit the stamps such that they follow the given restrictions and requirements:
 *
 * Cover all the empty cells.
 * Do not cover any of the occupied cells.
 * We can put as many stamps as we want.
 * Stamps can overlap with each other.
 * Stamps are not allowed to be rotated.
 * Stamps must stay completely inside the grid.
 * Return true if it is possible to fit the stamps while following the given restrictions and requirements. Otherwise, return false.
 *
 * Example 1:
 *
 * Input: grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
 * Output: true
 * Explanation: We have two overlapping stamps (labeled 1 and 2 in the image) that are able to cover all the empty cells.
 * Example 2:
 *
 * Input: grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
 * Output: false
 * Explanation: There is no way to fit the stamps onto all the empty cells without the stamps going outside the grid.
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[r].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 2 * 105
 * grid[r][c] is either 0 or 1.
 * 1 <= stampHeight, stampWidth <= 105
 */
public class StampingTheGrid {

    public boolean possibleToStamp(int[][] M, int h, int w) {
        int m = M.length;
        int n = M[0].length;
        int[][] A = new int[m + 1][n + 1];
        int[][] B = new int[m + 1][n + 1];
        int[][] good = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i + 1][j + 1] = A[i + 1][j] + A[i][j + 1] - A[i][j] + (1 - M[i][j]);
                if (i + 1 >= h && j + 1 >= w) {
                    int x = i + 1 - h;
                    int y = j + 1 - w;
                    if (A[i + 1][j + 1] - A[x][j + 1] - A[i + 1][y] + A[x][y] == w * h) good[i][j]++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                B[i + 1][j + 1] = B[i + 1][j] + B[i][j + 1] - B[i][j] + good[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = Math.min(i + h, m);
                int y = Math.min(j + w, n);
                if (M[i][j] == 0 && B[x][y] - B[i][y] - B[x][j] + B[i][j] == 0) return false;
            }
        }
        return true;
    }

}
