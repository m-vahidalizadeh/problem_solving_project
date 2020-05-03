package leetcode.medium;

import leetcode.base.Utils;

/**
 * Spiral Matrix III
 * On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.
 * <p>
 * Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.
 * <p>
 * Now, we walk in a clockwise spiral shape to visit every position in this grid.
 * <p>
 * Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.)
 * <p>
 * Eventually, we reach all R * C spaces of the grid.
 * <p>
 * Return a list of coordinates representing the positions of the grid in the order they were visited.
 * <p>
 * Example 1:
 * <p>
 * Input: R = 1, C = 4, r0 = 0, c0 = 0
 * Output: [[0,0],[0,1],[0,2],[0,3]]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: R = 5, C = 6, r0 = 1, c0 = 4
 * Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
 * <p>
 * Note:
 * <p>
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        SpiralMatrix sm = new SpiralMatrix();
        Utils.printArray(sm.spiralMatrixIII(5, 6, 1, 4));
    }

    static class Result {
        int[][] array;
        int currentIndex;

        Result(int n) {
            this.currentIndex = 0;
            this.array = new int[n][2];
        }
    }

    /**
     * Traverses the matrix in a spiral mode. It starts from r0 and c0.
     *
     * @param R  The number of rows
     * @param C  The number of columns
     * @param r0 The starting row
     * @param c0 The starting column
     * @return The spiral traversal 2 dimensions array
     */
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int totalMoves = R * C;
        int step = 1;
        Result result = new Result(totalMoves);
        while (result.currentIndex < totalMoves) {
            // Right crawl- first call the method then ++
            for (int i = 0; i < step; i++)
                add(R, C, r0, c0++, result);
            // Down crawl- first call the method then ++
            for (int i = 0; i < step; i++)
                add(R, C, r0++, c0, result);
            step++;
            // Left crawl- first call the method then ++
            for (int i = 0; i < step; i++)
                add(R, C, r0, c0--, result);
            // Up crawl- first call the method then ++
            for (int i = 0; i < step; i++)
                add(R, C, r0--, c0, result);
            step++;
        }
        return result.array;
    }

    /**
     * Add the point to the result if it is in range.
     *
     * @param R      Number of rows
     * @param C      Number of columns
     * @param r      The current row
     * @param c      The current column
     * @param result The result
     */
    private void add(int R, int C, int r, int c, Result result) {
        if (r >= 0 && r < R && c >= 0 && c < C) {
            result.array[result.currentIndex][0] = r;
            result.array[result.currentIndex][1] = c;
            result.currentIndex++;
        }
    }

}
