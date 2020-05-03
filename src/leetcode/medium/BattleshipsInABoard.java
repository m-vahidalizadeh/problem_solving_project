package leetcode.medium;

/**
 * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are
 * represented with '.'s. You may assume the following rules:
 * You receive a valid board, made of only battleships or empty slots.
 * Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape
 * 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
 * At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 * Example:
 * <p>
 * X..X
 * ...X
 * ...X
 * In the above board there are 2 battleships.
 * Invalid Example:
 * <p>
 * ...X
 * XXXX
 * ...X
 * This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
 * Follow up:
 * Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
 */
public class BattleshipsInABoard {

    public static void main(String[] args) {
        BattleshipsInABoard b = new BattleshipsInABoard();
        char[][] board = {
                {'X', '.', '.', 'X'}
                , {'.', '.', '.', 'X'}
                , {'.', '.', '.', 'X'}
        };
        System.out.println(b.countBattleships(board));
    }

    public int countBattleships(char[][] board) {
        int counter = 0;
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'X' && !middleOrEndOfBattleship(board, i, j)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private boolean middleOrEndOfBattleship(char[][] board, int i, int j) {
        return (i - 1 >= 0 && board[i - 1][j] == 'X') || (j - 1 >= 0 && board[i][j - 1] == 'X');
    }

}
