package leetcode.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * 37. Sudoku Solver
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 *
 * Example 1:
 *
 * Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 * Explanation: The input board is shown above and the only valid solution is shown below:
 *
 * Constraints:
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit or '.'.
 * It is guaranteed that the input board has only one solution.
 */
public class SudokuSolver {

    boolean solved;
    Set<Character>[] rows;
    Set<Character>[] columns;
    Set<Character>[] boxes;

    public void solveSudoku(char[][] board) {
        rows = new HashSet[9];
        columns = new HashSet[9];
        boxes = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            if (rows[i] == null) rows[i] = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (columns[j] == null) columns[j] = new HashSet<>();
                int boxId = i / 3 * 3 + j / 3;
                if (boxes[boxId] == null) boxes[boxId] = new HashSet<>();
                if (board[i][j] != '.') {
                    rows[i].add(board[i][j]);
                    columns[j].add(board[i][j]);
                    boxes[boxId].add(board[i][j]);
                }
            }
        }
        backTrack(board, 0, 0);
    }

    private void backTrack(char[][] board, int i, int j) {
        if (i == 9) {
            solved = true;
            return;
        }
        int newI = i + (j + 1) / 9;
        int newJ = (j + 1) % 9;
        if (board[i][j] != '.') {
            backTrack(board, newI, newJ);
            return;
        }
        int boxId = i / 3 * 3 + j / 3; // Generates a num between 0 and 8 -> box number
        for (char num = '1'; num <= '9'; num++) {
            if (rows[i].contains(num) || columns[j].contains(num) || boxes[boxId].contains(num)) continue;
            rows[i].add(num);
            columns[j].add(num);
            boxes[boxId].add(num);
            board[i][j] = num;
            backTrack(board, newI, newJ);
            if (!solved) {
                rows[i].remove(num);
                columns[j].remove(num);
                boxes[boxId].remove(num);
                board[i][j] = '.';
            }
        }
    }

}
