package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 782. Transform to Chessboard
 * An N x N board contains only 0s and 1s. In each move, you can swap any 2 rows with each other, or any 2 columns with each other.
 * <p>
 * What is the minimum number of moves to transform the board into a "chessboard" - a board where no 0s and no 1s are 4-directionally adjacent? If the task is impossible, return -1.
 * <p>
 * Examples:
 * Input: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
 * Output: 2
 * Explanation:
 * One potential sequence of moves is shown below, from left to right:
 * <p>
 * 0110     1010     1010
 * 0110 --> 1010 --> 0101
 * 1001     0101     1010
 * 1001     0101     0101
 * <p>
 * The first move swaps the first and second column.
 * The second move swaps the second and third row.
 * <p>
 * <p>
 * Input: board = [[0, 1], [1, 0]]
 * Output: 0
 * Explanation:
 * Also note that the board with 0 in the top left corner,
 * 01
 * 10
 * <p>
 * is also a valid chessboard.
 * <p>
 * Input: board = [[1, 0], [1, 0]]
 * Output: -1
 * Explanation:
 * No matter what sequence of moves you make, you cannot end with a valid chessboard.
 * Note:
 * <p>
 * board will have the same number of rows and columns, a number in the range [2, 30].
 * board[i][j] will be only 0s or 1s.
 */
public class MoveToChessboard {

    public int movesToChessboard(int[][] board) {
        Map<Integer, Integer> patternToCountMap = new HashMap<>();
        int n = board.length;
        for (int r = 0; r < n; r++) {
            int code = 0;
            for (int c = 0; c < n; c++) {
                code = code * 2 + board[r][c];
            }
            patternToCountMap.put(code, patternToCountMap.getOrDefault(code, 0) + 1);
        }
        int rowSwitches = getMinSwitches(patternToCountMap, n);
        if (rowSwitches == -1) return -1;
        patternToCountMap = new HashMap<>();
        for (int c = 0; c < n; c++) {
            int code = 0;
            for (int r = 0; r < n; r++) {
                code = code * 2 + board[r][c];
            }
            patternToCountMap.put(code, patternToCountMap.getOrDefault(code, 0) + 1);
        }
        int columnSwitches = getMinSwitches(patternToCountMap, n);
        if (columnSwitches == -1) return -1;
        return rowSwitches + columnSwitches;
    }

    private int getMinSwitches(Map<Integer, Integer> patternToCountMap, int n) {
        if (patternToCountMap.size() != 2) return -1; // Only two different patterns should exist
        List<Integer> patterns = new ArrayList<>(patternToCountMap.keySet());
        int p1 = patterns.get(0);
        int p2 = patterns.get(1);
        if (!(patternToCountMap.get(p1) == n / 2 && patternToCountMap.get(p2) == (n + 1) / 2) &&
                !(patternToCountMap.get(p2) == n / 2 && patternToCountMap.get(p1) == (n + 1) / 2)
        ) return -1; // Not possible
        int nOnes = (1 << n) - 1;
        if ((p1 ^ p2) != nOnes) return -1; // Two patterns should be complement of each other
        int p1Ones = Integer.bitCount(p1 & nOnes);
        int minSwitches = Integer.MAX_VALUE;
        if (n % 2 == 0 || p1Ones * 2 < n)
            minSwitches = Math.min(minSwitches, Integer.bitCount(p1 ^ 0xAAAAAAAA & nOnes) / 2); // 32 bit 1010...
        if (n % 2 == 0 || p1Ones * 2 > n)
            minSwitches = Math.min(minSwitches, Integer.bitCount(p1 ^ 0x55555555 & nOnes) / 2); // 32 bit 0101...
        return minSwitches;
    }

}
