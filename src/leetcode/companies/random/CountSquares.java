package leetcode.companies.random;

import java.util.HashSet;
import java.util.Set;

/**
 * 1277. Count Square Submatrices with All Ones
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 *
 * Example 1:
 *
 * Input: matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 * Example 2:
 *
 * Input: matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * Output: 7
 * Explanation:
 * There are 6 squares of side 1.
 * There is 1 square of side 2.
 * Total number of squares = 6 + 1 = 7.
 *
 * Constraints:
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 */
public class CountSquares {

    private Set<String> squares;
    private int n;
    private int m;

    public int countSquares(int[][] matrix) {
        squares = new HashSet<>();
        n = matrix.length;
        m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) dfs(matrix, i, j, i, j);
            }
        }
        return squares.size();
    }

    private void dfs(int[][] matrix, int x1, int y1, int x2, int y2) {
        if (x2 >= n || y2 >= m) return;
        String key = x1 + "-" + y1 + "-" + x2 + "-" + y2;
        if (squares.contains(key)) return;
        if (doesSatisfy(matrix, x1, y1, x2, y2)) {
            squares.add(key);
            dfs(matrix, x1, y1, x2 + 1, y2 + 1);
        }
    }

    private boolean doesSatisfy(int[][] matrix, int x1, int y1, int x2, int y2) {
        for (int y = y1; y <= y2; y++) {
            if (matrix[x2][y] == 0) return false;
        }
        for (int x = x1; x <= x2; x++) {
            if (matrix[x][y2] == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CountSquares c = new CountSquares();
        System.out.println(c.countSquares(new int[][]{
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        }));
        System.out.println(c.countSquares(new int[][]{
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        }));
        System.out.println(c.countSquares(new int[][]{
                {0, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 0, 1, 0}
        }));
    }

}
