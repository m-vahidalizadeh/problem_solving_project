package leetcode.medium;

import java.util.Arrays;

/**
 * Sparse Matrix Multiplication
 * Given two sparse matrices A and B, return the result of AB.
 *
 * You may assume that A's column number is equal to B's row number.
 *
 * Example:
 *
 * Input:
 *
 * A = [
 *   [ 1, 0, 0],
 *   [-1, 0, 3]
 * ]
 *
 * B = [
 *   [ 7, 0, 0 ],
 *   [ 0, 0, 0 ],
 *   [ 0, 0, 1 ]
 * ]
 *
 * Output:
 *
 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                   | 0 0 1 |
 *
 * Constraints:
 *
 * 1 <= A.length, B.length <= 100
 * 1 <= A[i].length, B[i].length <= 100
 * -100 <= A[i][j], B[i][j] <= 100
 */
public class SparceMatrixMultiplication {

    public int[][] multiply(int[][] A, int[][] B) {
        int r1 = A.length;
        int n = A[0].length;
        int c2 = B[0].length;
        int[][] C = new int[r1][c2];
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                C[i][j] = mulRowColumn(A, B, i, j, n);
            }
        }
        return C;
    }

    private int mulRowColumn(int[][] A, int[][] B, int i1, int j2, int n) {
        int sum = 0;
        for (int a = 0; a < n; a++) {
            if (A[i1][a] != 0 && B[a][j2] != 0) {
                sum += A[i1][a] * B[a][j2];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        SparceMatrixMultiplication s = new SparceMatrixMultiplication();
        int[][] A = {
                {1, 0, 0},
                {-1, 0, 3}
        };
        int[][] B = {
                {7, 0, 0},
                {0, 0, 0},
                {0, 0, 1}
        };
        System.out.println(Arrays.deepToString(s.multiply(A, B)));
    }

}
