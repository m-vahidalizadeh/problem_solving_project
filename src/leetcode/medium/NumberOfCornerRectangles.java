package leetcode.medium;

/**
 * Number Of Corner Rectangles
 * Given a grid where each entry is only 0 or 1, find the number of corner rectangles.
 * <p>
 * A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.
 * <p>
 * Example 1:
 * <p>
 * Input: grid =
 * [[1, 0, 0, 1, 0],
 * [0, 0, 1, 0, 1],
 * [0, 0, 0, 1, 0],
 * [1, 0, 1, 0, 1]]
 * Output: 1
 * Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
 * <p>
 * Example 2:
 * <p>
 * Input: grid =
 * [[1, 1, 1],
 * [1, 1, 1],
 * [1, 1, 1]]
 * Output: 9
 * Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
 * <p>
 * Example 3:
 * <p>
 * Input: grid =
 * [[1, 1, 1, 1]]
 * Output: 0
 * Explanation: Rectangles must have four distinct corners.
 * <p>
 * Note:
 * <p>
 * The number of rows and columns of grid will each be in the range [1, 200].
 * Each grid[i][j] will be either 0 or 1.
 * The number of 1s in the grid will be at most 6000.
 */
public class NumberOfCornerRectangles {

    public int countCornerRectangles(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int counter = 0;
        for (int currentRow = 0; currentRow < n; currentRow++) {
            for (int j1 = 0; j1 < m - 1; j1++) {
                for (int j2 = j1 + 1; j2 < m; j2++) {
                    if (grid[currentRow][j1] == 1 && grid[currentRow][j2] == 1) {
                        for (int tempRow = currentRow + 1; tempRow < n; tempRow++) {
                            if (grid[tempRow][j1] == 1 && grid[tempRow][j2] == 1) {
                                counter++;
                            }
                        }
                    }
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        NumberOfCornerRectangles n = new NumberOfCornerRectangles();
        int[][] grid1 = {{1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0},
                {1, 0, 1, 0, 1}};
        System.out.println(n.countCornerRectangles(grid1));
        int[][] grid2 = {{1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}};
        System.out.println(n.countCornerRectangles(grid2));
        int[][] grid3 = {{1, 1, 1, 1}};
        System.out.println(n.countCornerRectangles(grid3));
    }

}
