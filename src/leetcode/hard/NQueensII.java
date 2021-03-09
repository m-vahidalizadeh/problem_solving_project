package leetcode.hard;

/**
 * 52. N-Queens II
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= n <= 9
 */
public class NQueensII {

    int n;
    int[][] board;

    public int totalNQueens(int n) {
        this.n = n;
        this.board = new int[n][n];
        return backTrack(0, n);
    }

    private int backTrack(int row, int queens) {
        if (queens == 0) return 1;
        int count = 0;
        for (int col = 0; col < n; col++) {
            if (board[row][col] == 0) {
                set(row, col, 1);
                count += backTrack(row + 1, queens - 1);
                set(row, col, -1);
            }
        }
        return count;
    }

    private void set(int row, int col, int val) {
        for (int j = 0; j < n; j++) board[row][j] += val; // row
        for (int i = 0; i < n; i++) board[i][col] += val; // column
        for (int i = 0; row + i < n && col + i < n; i++) board[row + i][col + i] += val; // diagonal right
        for (int i = 0; row - i >= 0 && col - i >= 0; i++) board[row - i][col - i] += val; // diagonal left
        for (int i = 0; row - i >= 0 && col + i < n; i++) board[row - i][col + i] += val; // anti-diagonal right
        for (int i = 0; row + i < n && col - i >= 0; i++) board[row + i][col - i] += val; // anti-diagonal left
    }

}
