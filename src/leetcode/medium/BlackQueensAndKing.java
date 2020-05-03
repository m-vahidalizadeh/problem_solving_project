package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Queens That Can Attack the King
 * On an 8x8 chessboard, there can be multiple Black Queens and one White King.
 * <p>
 * Given an array of integer coordinates queens that represents the positions of the Black Queens, and a pair of coordinates king that represent the position of the White King, return the coordinates of all the queens (in any order) that can attack the King.
 * <p>
 * Example 1:
 * <p>
 * Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
 * Output: [[0,1],[1,0],[3,3]]
 * Explanation:
 * The queen at [0,1] can attack the king cause they're in the same row.
 * The queen at [1,0] can attack the king cause they're in the same column.
 * The queen at [3,3] can attack the king cause they're in the same diagnal.
 * The queen at [0,4] can't attack the king cause it's blocked by the queen at [0,1].
 * The queen at [4,0] can't attack the king cause it's blocked by the queen at [1,0].
 * The queen at [2,4] can't attack the king cause it's not in the same row/column/diagnal as the king.
 * Example 2:
 * <p>
 * Input: queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
 * Output: [[2,2],[3,4],[4,4]]
 * Example 3:
 * <p>
 * Input: queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
 * Output: [[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= queens.length <= 63
 * queens[0].length == 2
 * 0 <= queens[i][j] < 8
 * king.length == 2
 * 0 <= king[0], king[1] < 8
 * At most one piece is allowed in a cell.
 */
public class BlackQueensAndKing {

    public static void main(String[] args) {
        // Example 1
//        int[][] queens = {{0, 1}, {1, 0}, {4, 0}, {0, 4}, {3, 3}, {2, 4}};
//        int[] king = {0, 0};
        // Example 2
//        int[][] queens = {{0,0},{1,1},{2,2},{3,4},{3,5},{4,4},{4,5}};
//        int[] king = {3, 3};
        // Example 3
        int[][] queens = {{5, 6}, {7, 7}, {2, 1}, {0, 7}, {1, 6}, {5, 1}, {3, 7}, {0, 3}, {4, 0}, {1, 2}, {6, 3}, {5, 0}, {0, 4}, {2, 2}, {1, 1}, {6, 4}, {5, 4}, {0, 0}, {2, 6}, {4, 5}, {5, 2}, {1, 4}, {7, 5}, {2, 3}, {0, 5}, {4, 2}, {1, 0}, {2, 7}, {0, 1}, {4, 6}, {6, 1}, {0, 6}, {4, 3}, {1, 7}};
        int[] king = {3, 4};
        BlackQueensAndKing b = new BlackQueensAndKing();
        List<List<Integer>> result = b.queensAttacktheKing(queens, king);
        for (List<Integer> r : result) {
            for (Integer e : r) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> result = new ArrayList<>();
        int[][] board = new int[8][8];
        for (int i = 0; i < queens.length; i++) {
            board[queens[i][0]][queens[i][1]] = 1;
        }
        // North
        for (int i = king[0] - 1; i >= 0; i--) {
            if (board[i][king[1]] == 1) {
                result.add(List.of(i, king[1]));
                break;
            }
        }
        // South
        for (int i = king[0] + 1; i < 8; i++) {
            if (board[i][king[1]] == 1) {
                result.add(List.of(i, king[1]));
                break;
            }
        }
        // East
        for (int j = king[1] + 1; j < 8; j++) {
            if (board[king[0]][j] == 1) {
                result.add(List.of(king[0], j));
                break;
            }
        }
        // West
        for (int j = king[1] - 1; j >= 0; j--) {
            if (board[king[0]][j] == 1) {
                result.add(List.of(king[0], j));
                break;
            }
        }
        // North east
        int a = king[0] - 1;
        int b = king[1] + 1;
        while (a >= 0 && b < 8) {
            if (board[a][b] == 1) {
                result.add(List.of(a, b));
                break;
            }
            a--;
            b++;
        }
        // North west
        a = king[0] - 1;
        b = king[1] - 1;
        while (a >= 0 && b >= 0) {
            if (board[a][b] == 1) {
                result.add(List.of(a, b));
                break;
            }
            a--;
            b--;
        }
        // South east
        a = king[0] + 1;
        b = king[1] + 1;
        while (a < 8 && b < 8) {
            if (board[a][b] == 1) {
                result.add(List.of(a, b));
                break;
            }
            a++;
            b++;
        }
        // South west
        a = king[0] + 1;
        b = king[1] - 1;
        while (a < 8 && b >= 0) {
            if (board[a][b] == 1) {
                result.add(List.of(a, b));
                break;
            }
            a++;
            b--;
        }
        return result;
    }

}
