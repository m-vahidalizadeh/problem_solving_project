package leetcode.companies.google;

/**
 * Toeplitz Matrix
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
 * <p>
 * Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * matrix = [
 * [1,2,3,4],
 * [5,1,2,3],
 * [9,5,1,2]
 * ]
 * Output: True
 * Explanation:
 * In the above grid, the diagonals are:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 * In each diagonal all elements are the same, so the answer is True.
 * Example 2:
 * <p>
 * Input:
 * matrix = [
 * [1,2],
 * [2,2]
 * ]
 * Output: False
 * Explanation:
 * The diagonal "[1, 2]" has different elements.
 * <p>
 * Note:
 * <p>
 * matrix will be a 2D array of integers.
 * matrix will have a number of rows and columns in range [1, 20].
 * matrix[i][j] will be integers in range [0, 99].
 * <p>
 * Follow up:
 * <p>
 * What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
 * What if the matrix is so large that you can only load up a partial row into the memory at once?
 */
public class ToeplitxMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {
        int i = 0;
        int j = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        for (j = 0; j < m; j++) {
            if (!satisfy(matrix, i, j, n, m)) return false;
        }
        j = 0;
        for (i = 1; i < n; i++) {
            if (!satisfy(matrix, i, j, n, m)) return false;
        }
        return true;
    }

    private boolean satisfy(int[][] matrix, int i, int j, int n, int m) {
        int first = matrix[i][j];
        i++;
        j++;
        while (i < n && j < m) {
            if (matrix[i][j] != first) return false;
            i++;
            j++;
        }
        return true;
    }

    public static void main(String[] args) {
        ToeplitxMatrix t = new ToeplitxMatrix();
        System.out.println(t.isToeplitzMatrix(new int[][]{{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}}));
        System.out.println(t.isToeplitzMatrix(new int[][]{{1, 2}, {2, 2}}));
    }

}
