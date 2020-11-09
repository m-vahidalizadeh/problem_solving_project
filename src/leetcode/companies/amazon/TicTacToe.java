package leetcode.companies.amazon;

public class TicTacToe {

    int[][] board;
    int n;

    public TicTacToe(int n) {
        this.n = n;
        board = new int[n][n];
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either:
     * 0: No one wins.
     * 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        board[row][col] = player;
        return isRowWinner(player, row) || isColWinner(player, col) ||
                isDiagonalWinner(player, row, col) || isAntiDiagonalWinner(player, row, col) ? player : 0;
    }

    private boolean isRowWinner(int player, int row) {
        for (int j = 0; j < n; j++) {
            if (board[row][j] != player) return false;
        }
        return true;
    }

    private boolean isColWinner(int player, int col) {
        for (int i = 0; i < n; i++) {
            if (board[i][col] != player) return false;
        }
        return true;
    }

    private boolean isDiagonalWinner(int player, int row, int col) {
        if (row != col) return false;
        for (int i = 0; i < n; i++) {
            if (board[i][i] != player) return false;
        }
        return true;
    }

    private boolean isAntiDiagonalWinner(int player, int row, int col) {
        if (row != n - 1 - col) return false;
        for (int i = 0; i < n; i++) {
            if (board[i][n - 1 - i] != player) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        TicTacToe t = new TicTacToe(2);
        t.move(0, 1, 1);
        t.move(1, 1, 2);
        System.out.println(t.move(1, 0, 1));
    }

}
