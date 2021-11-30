package leetcode.hard;

import java.util.List;

/**
 * 1301. Number of Paths with Max Score
 * You are given a square board of characters. You can move on the board starting at the bottom right square marked with the character 'S'.
 *
 * You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.
 *
 * Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.
 *
 * In case there is no path, return [0, 0].
 *
 * Example 1:
 *
 * Input: board = ["E23","2X2","12S"]
 * Output: [7,1]
 * Example 2:
 *
 * Input: board = ["E12","1X1","21S"]
 * Output: [4,2]
 * Example 3:
 *
 * Input: board = ["E11","XXX","11S"]
 * Output: [0,0]
 *
 * Constraints:
 *
 * 2 <= board.length == board[i].length <= 100
 */
public class NumberOfPathsWithMaxScore {

    public int[] pathsWithMaxScore(List<String> board) {
        int[][] directions = new int[][]{{-1, 0}, {0, -1}, {-1, -1}};
        int n = board.size();
        int m = board.get(0).length();
        int[][] dpSum = new int[m][n];
        int[][] dpCount = new int[m][n];
        dpCount[m - 1][n - 1] = 1; // We start at m-1,n-1
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (dpCount[r][c] == 0) continue; // We can't reach this point
                for (int[] dir : directions) {
                    int newR = r + dir[0];
                    int newC = c + dir[1];
                    if (newR < 0 || newC < 0 || board.get(newR).charAt(newC) == 'X') continue;
                    int newSum = dpSum[r][c];
                    if (board.get(newR).charAt(newC) != 'E') newSum += board.get(newR).charAt(newC) - '0';
                    if (newSum > dpSum[newR][newC]) {
                        dpSum[newR][newC] = newSum;
                        dpCount[newR][newC] = dpCount[r][c];
                    } else if (newSum == dpSum[newR][newC])
                        dpCount[newR][newC] = (dpCount[newR][newC] + dpCount[r][c]) % 1_000_000_007;
                }
            }
        }
        return new int[]{dpSum[0][0], dpCount[0][0]};
    }

}
