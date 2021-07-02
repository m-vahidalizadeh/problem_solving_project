package leetcode.companies.google;

/**
 * 308. Range Sum Query 2D - Mutable
 * Given a 2D matrix matrix, handle multiple queries of the following types:
 *
 * Update the value of a cell in matrix.
 * Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Implement the NumMatrix class:
 *
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * void update(int row, int col, int val) Updates the value of matrix[row][col] to be val.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Example 1:
 *
 * Input
 * ["NumMatrix", "sumRegion", "update", "sumRegion"]
 * [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]
 * Output
 * [null, 8, null, 10]
 *
 * Explanation
 * NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e. sum of the left red rectangle)
 * numMatrix.update(3, 2, 2);       // matrix changes from left image to right image
 * numMatrix.sumRegion(2, 1, 4, 3); // return 10 (i.e. sum of the right red rectangle)
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * -105 <= matrix[i][j] <= 105
 * 0 <= row < m
 * 0 <= col < n
 * -105 <= val <= 105
 * 0 <= row1 <= row2 < m
 * 0 <= col1 <= col2 < n
 * At most 104 calls will be made to sumRegion and update.
 */
public class Matrix2DSum {

    int rows;
    int columns;
    int[][] bits;

    public Matrix2DSum(int[][] matrix) {
        rows = matrix.length;
        if (rows == 0) return;
        columns = matrix[0].length;
        bits = new int[rows + 1][columns + 1];
        buildBit(matrix);
    }

    private int lsb(int n) {
        return n & (-n);
    }

    public void updateBit(int row, int col, int val) {
        for (int i = row; i <= rows; i += lsb(i)) {
            for (int j = col; j <= columns; j += lsb(j)) {
                bits[i][j] += val;
            }
        }
    }

    private int queryBit(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i -= lsb(i)) {
            for (int j = col; j > 0; j -= lsb(j)) {
                sum += bits[i][j];
            }
        }
        return sum;
    }

    private void buildBit(int[][] matrix) {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                int val = matrix[i - 1][j - 1];
                updateBit(i, j, val);
            }
        }
    }

    public void update(int row, int col, int val) {
        int oldVal = sumRegion(row, col, row, col);
        row++;
        col++;
        int diff = val - oldVal;
        updateBit(row, col, diff);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1++;
        col1++;
        row2++;
        col2++;
        int a = queryBit(row2, col2);
        int b = queryBit(row1 - 1, col1 - 1);
        int c = queryBit(row2, col1 - 1);
        int d = queryBit(row1 - 1, col2);
        return (a + b) - (c + d);
    }

}
