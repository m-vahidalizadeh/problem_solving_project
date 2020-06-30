package leetcode.companies.bloomberg;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Candy Crush
 * This question is about implementing a basic elimination algorithm for Candy Crush.
 * <p>
 * Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:
 * <p>
 * If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
 * After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
 * After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
 * If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
 * You need to perform the above rules until the board becomes stable, then return the current board.
 * <p>
 * Example:
 * <p>
 * Input: board =  [[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],[710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]  Output: [[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]  Explanation:
 * <p>
 * Note:
 * <p>
 * The length of board will be in the range [3, 50].
 * The length of board[i] will be in the range [3, 50].
 * Each board[i][j] will initially start as an integer in the range [1, 2000].
 */
public class CandyCrush {

    static class Coordinate {
        int i;
        int j;

        Coordinate(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int[][] candyCrush(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        Queue<Coordinate> toBeDeleted = new LinkedList<>();
        while (true) {
            // Mark to be deleted cells
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 0) continue;
                    int left = j;
                    int right = j;
                    int up = i;
                    int down = i;
                    // Go to left
                    while (left >= 0 && left > j - 3 && board[i][left] == board[i][j]) left--;
                    // Go to right
                    while (right < m && right < j + 3 && board[i][right] == board[i][j]) right++;
                    // Go up
                    while (up >= 0 && up > i - 3 && board[up][j] == board[i][j]) up--;
                    // Go down
                    while (down < n && down < i + 3 && board[down][j] == board[i][j]) down++;
                    // Did you find more than or equals similar candy vertically or horizontally?
                    if (right - left > 3 || down - up > 3) toBeDeleted.add(new Coordinate(i, j));
                }
            }
            // We are done
            if (toBeDeleted.isEmpty()) break;
            // Crush the candies
            while (!toBeDeleted.isEmpty()) {
                Coordinate c = toBeDeleted.poll();
                board[c.i][c.j] = 0;
            }
            // Drops
            for (int j = 0; j < m; j++) {
                int lastNonZeroCellRow = n - 1;
                for (int i = n - 1; i >= 0; i--) {
                    if (board[i][j] > 0) {
                        int temp = board[i][j];
                        board[i][j] = 0;
                        board[lastNonZeroCellRow--][j] = temp;
                    }
                }
            }
        }
        return board;
    }

}
