package leetcode.companies.facebook;

/**
 * 311. Sparse Matrix Multiplication
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 * Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the result of mat1 x mat2. You may assume that multiplication is always possible.
 *
 * Example 1:
 *
 * Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
 * Output: [[7,0,0],[-7,0,3]]
 * Example 2:
 *
 * Input: mat1 = [[0]], mat2 = [[0]]
 * Output: [[0]]
 *
 * Constraints:
 *
 * m == mat1.length
 * k == mat1[i].length == mat2.length
 * n == mat2[i].length
 * 1 <= m, n, k <= 100
 * -100 <= mat1[i][j], mat2[i][j] <= 100
 */
public class MatrixMultiply {

    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length, k = mat1[0].length, n = mat2[0].length, res[][] = new int[m][n];
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                for (int z = 0; z < k; z++) {
                    res[x][y] += mat1[x][z] * mat2[z][y];
                }
            }
        }
        return res;
    }

}
