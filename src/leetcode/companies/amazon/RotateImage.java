package leetcode.companies.amazon;

import java.util.Arrays;

/**
 * 48. Rotate Image
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * Example 2:
 * <p>
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * Example 3:
 * <p>
 * Input: matrix = [[1]]
 * Output: [[1]]
 * Example 4:
 * <p>
 * Input: matrix = [[1,2],[3,4]]
 * Output: [[3,1],[4,2]]
 * <p>
 * Constraints:
 * <p>
 * matrix.length == n
 * matrix[i].length == n
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class RotateImage {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int limit = n % 2 == 0 ? (n / 2) : n / 2 + 1;
        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < n / 2; j++) {
                // right
                int temp0 = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = matrix[i][j];
                // down
                int temp1 = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = temp0;
                // left
                int temp2 = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = temp1;
                // up
                matrix[i][j] = temp2;
            }
        }
        print2DArray(matrix);
        System.out.println();
    }

    public static void main(String[] args) {
        RotateImage r = new RotateImage();
        r.rotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        r.rotate(new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}});
        r.rotate(new int[][]{{1}});
        r.rotate(new int[][]{{1, 2}, {3, 4}});
    }

    private void print2DArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

}
