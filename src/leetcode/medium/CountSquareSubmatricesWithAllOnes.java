package leetcode.medium;

/**
 * Count Square Submatrices with All Ones
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 * <p>
 * Example 1:
 * <p>
 * Input: matrix =
 * [
 * [0,1,1,1],
 * [1,1,1,1],
 * [0,1,1,1]
 * ]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 * Example 2:
 * <p>
 * Input: matrix =
 * [
 * [1,0,1],
 * [1,1,0],
 * [1,1,0]
 * ]
 * Output: 7
 * Explanation:
 * There are 6 squares of side 1.
 * There is 1 square of side 2.
 * Total number of squares = 6 + 1 = 7.
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 */
public class CountSquareSubmatricesWithAllOnes {

    /**
     * This method is using dynamic programming approach. We start from the left top corner. For each cell, the
     * number of squares with this cell as sits bottom right will be min[(i-1,j-1),(i,j-1) and (i-1,j)].
     *
     * @param matrix The input matrix
     * @return How many squares with all one exists in this input
     */
    public int countSquares(int[][] matrix) {
        int counter = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // Let zeros stay zero. Just accumulate ones.
                if (matrix[i][j] != 0) {
                    if (i == 0 || j == 0) {
                         /*
                         This is in the border. So, min[(i-1,j-1),(i,j-1) and (i-1,j)] is zero. Just add the current one length square.
                          */
                        counter++;
                    } else {
                         /*
                         The current cell with its three important neighbors:
                         i-1,j-1    i-1,j
                         i,j-1      i,j
                         The current cell accumulation is min[(i-1,j-1),(i,j-1) and (i-1,j)].
                          */
                        int tempMin = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i][j - 1], matrix[i - 1][j]));
                        // Update the cell accumulation value for the rest of the process.
                        matrix[i][j] += tempMin;
                        counter += matrix[i][j];
                    }
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        CountSquareSubmatricesWithAllOnes c = new CountSquareSubmatricesWithAllOnes();
        System.out.println(c.countSquares(matrix));
    }

}
