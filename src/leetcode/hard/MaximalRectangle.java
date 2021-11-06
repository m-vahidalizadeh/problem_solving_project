package leetcode.hard;

import java.util.Arrays;

/**
 * 85. Maximal Rectangle
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 * Example 1:
 *
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above picture.
 * Example 2:
 *
 * Input: matrix = []
 * Output: 0
 * Example 3:
 *
 * Input: matrix = [["0"]]
 * Output: 0
 * Example 4:
 *
 * Input: matrix = [["1"]]
 * Output: 1
 * Example 5:
 *
 * Input: matrix = [["0","0"]]
 * Output: 0
 *
 * Constraints:
 *
 * rows == matrix.length
 * cols == matrix[i].length
 * 0 <= row, cols <= 200
 * matrix[i][j] is '0' or '1'.
 */
public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int maxArea = 0;
        int[] left = new int[m];
        int[] right = new int[m];
        int[] height = new int[m];
        Arrays.fill(right, m - 1);
        for (int i = 0; i < n; i++) {
            int currRight = m - 1;
            for (int j = m - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') right[j] = Math.min(right[j], currRight);
                else {
                    right[j] = m - 1;
                    currRight = j - 1;
                }
            }
            int currLeft = 0;
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                    left[j] = Math.max(left[j], currLeft);
                    maxArea = Math.max(maxArea, height[j] * (right[j] - left[j] + 1));
                } else {
                    height[j] = 0;
                    left[j] = 0;
                    currLeft = j + 1;
                }
            }
        }
        return maxArea;
    }

}
