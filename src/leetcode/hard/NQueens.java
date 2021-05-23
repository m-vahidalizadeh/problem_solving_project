package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N-Queens
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 * Example 2:
 *
 * Input: n = 1
 * Output: [["Q"]]
 *
 * Constraints:
 *
 * 1 <= n <= 9
 */
public class NQueens {

    char[][] board;
    List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        board = new char[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(board[i], '.');
        res = new ArrayList<>();
        backTrack(0, n);
        return res;
    }

    private void backTrack(int row, int n) {
        if (row == n) {
            res.add(buildBoard(n));
        } else {
            for (int col = 0; col < n; col++) {
                if (canPlace(row, col, n)) {
                    board[row][col] = 'Q';
                    backTrack(row + 1, n);
                    board[row][col] = '.';
                }
            }
        }
    }

    private boolean canPlace(int row, int col, int n) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        for (int j = 0; j < col; j++) {
            if (board[row][j] == 'Q') return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private List<String> buildBoard(int n) {
        List<String> boardList = new ArrayList<>();
        for (int i = 0; i < n; i++) boardList.add(new String(board[i]));
        return boardList;
    }

}
