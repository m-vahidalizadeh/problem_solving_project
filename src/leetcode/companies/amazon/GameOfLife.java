package leetcode.companies.amazon;

/**
 * 289. Game of Life
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 *
 * Example:
 *
 * Input:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * Output:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 * Follow up:
 *
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 */
public class GameOfLife {

    int[][] directions;
    int n;
    int m;

    public void gameOfLife(int[][] board) {
        if (board == null) return;
        n = board.length;
        if (n == 0) return;
        m = board[0].length;
        directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    int lives = countLives(board, i, j);
                    if (lives == 3) board[i][j] = -1; // Will become alive -1
                } else if (board[i][j] == 1) {
                    int lives = countLives(board, i, j);
                    if (lives < 2 || lives > 3) board[i][j] = 2; // Will die 2
                }
            }
        }
        update(board);
    }

    private void update(int[][] board) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == -1) board[i][j] = 1;
                else if (board[i][j] == 2) board[i][j] = 0;
            }
        }
    }

    private int countLives(int[][] board, int i, int j) {
        int sum = 0;
        for (int[] dir : directions) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (newI < 0 || newI >= n || newJ < 0 || newJ >= m) continue;
            if (board[newI][newJ] == 1 || board[newI][newJ] == 2) sum++;
        }
        return sum;
    }

}
