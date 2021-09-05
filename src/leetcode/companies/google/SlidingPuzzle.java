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
public class SlidingPuzzle {

    public int slidingPuzzle(int[][] board) {
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        String target = "123450";
        StringBuilder sb = new StringBuilder();
        Set<String> visited = new HashSet<>();
        int zeroIndex = -1;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
                if (board[i][j] == 0) zeroIndex = sb.length() - 1;
            }
        }
        Queue<State> q = new LinkedList<>();
        String initialVal = sb.toString();
        q.add(new State(initialVal, zeroIndex));
        visited.add(initialVal);
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                State curr = q.poll();
                if (target.equals(curr.val)) return steps;
                int zeroCol = curr.zeroIndex % 3;
                int zeroRow = curr.zeroIndex / 3;
                for (int[] dir : directions) {
                    int neiCol = zeroCol + dir[0];
                    int neiRow = zeroRow + dir[1];
                    if (neiCol < 0 || neiCol >= 3 || neiRow < 0 || neiRow >= 2) continue;
                    int neiIndex = neiRow * 3 + neiCol;
                    StringBuilder newS = new StringBuilder(curr.val);
                    newS.setCharAt(curr.zeroIndex, curr.val.charAt(neiIndex));
                    newS.setCharAt(neiIndex, '0');
                    String newVal = newS.toString();
                    if (!visited.contains(newVal)) {
                        visited.add(newVal);
                        q.add(new State(newVal, neiIndex));
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public class State {
        String val;
        int zeroIndex;

        public State(String val, int zeroIndex) {
            this.val = val;
            this.zeroIndex = zeroIndex;
        }
    }

}
