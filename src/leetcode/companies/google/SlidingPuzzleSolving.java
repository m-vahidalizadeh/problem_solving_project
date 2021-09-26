package leetcode.companies.google;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 773. Sliding Puzzle
 * On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * Given the puzzle board board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
 *
 * Example 1:
 *
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 * Example 2:
 *
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 * Example 3:
 *
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 * Example 4:
 *
 * Input: board = [[3,2,4],[1,5,0]]
 * Output: 14
 *
 * Constraints:
 *
 * board.length == 2
 * board[i].length == 3
 * 0 <= board[i][j] <= 5
 * Each value board[i][j] is unique.
 */
public class SlidingPuzzleSolving {

    public int slidingPuzzle(int[][] board) {
        StringBuilder sb = new StringBuilder();
        int startZeroId = -1;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
                if (board[i][j] == 0) startZeroId = i * 3 + j;
            }
        }
        String startVal = sb.toString();
        String targetVal = "123450";
        if (targetVal.equals(startVal)) return 0;
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<State> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add(startVal);
        q.add(new State(startZeroId, startVal));
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                State currState = q.poll();
                if (targetVal.equals(currState.val)) return step;
                int zeroI = currState.zeroId / 3;
                int zeroJ = currState.zeroId % 3;
                for (int[] dir : directions) {
                    int newI = zeroI + dir[0];
                    int newJ = zeroJ + dir[1];
                    if (newI < 0 || newI > 1 || newJ < 0 || newJ > 2) continue;
                    int newId = newI * 3 + newJ;
                    sb = new StringBuilder(currState.val);
                    sb.setCharAt(currState.zeroId, currState.val.charAt(newId));
                    sb.setCharAt(newId, '0');
                    String newVal = sb.toString();
                    if (!visited.contains(newVal)) {
                        visited.add(newVal);
                        q.add(new State(newId, newVal));
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public class State {
        int zeroId;
        String val;

        public State(int zeroId, String val) {
            this.zeroId = zeroId;
            this.val = val;
        }
    }

}
