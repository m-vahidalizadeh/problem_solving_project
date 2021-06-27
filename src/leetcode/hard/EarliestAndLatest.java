package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 1900. The Earliest and Latest Rounds Where Players Compete
 * There is a tournament where n players are participating. The players are standing in a single row and are numbered from 1 to n based on their initial standing position (player 1 is the first player in the row, player 2 is the second player in the row, etc.).
 *
 * The tournament consists of multiple rounds (starting from round number 1). In each round, the ith player from the front of the row competes against the ith player from the end of the row, and the winner advances to the next round. When the number of players is odd for the current round, the player in the middle automatically advances to the next round.
 *
 * For example, if the row consists of players 1, 2, 4, 6, 7
 * Player 1 competes against player 7.
 * Player 2 competes against player 6.
 * Player 4 automatically advances to the next round.
 * After each round is over, the winners are lined back up in the row based on the original ordering assigned to them initially (ascending order).
 *
 * The players numbered firstPlayer and secondPlayer are the best in the tournament. They can win against any other player before they compete against each other. If any two other players compete against each other, either of them might win, and thus you may choose the outcome of this round.
 *
 * Given the integers n, firstPlayer, and secondPlayer, return an integer array containing two values, the earliest possible round number and the latest possible round number in which these two players will compete against each other, respectively.
 *
 * Example 1:
 *
 * Input: n = 11, firstPlayer = 2, secondPlayer = 4
 * Output: [3,4]
 * Explanation:
 * One possible scenario which leads to the earliest round number:
 * First round: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
 * Second round: 2, 3, 4, 5, 6, 11
 * Third round: 2, 3, 4
 * One possible scenario which leads to the latest round number:
 * First round: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
 * Second round: 1, 2, 3, 4, 5, 6
 * Third round: 1, 2, 4
 * Fourth round: 2, 4
 * Example 2:
 *
 * Input: n = 5, firstPlayer = 1, secondPlayer = 5
 * Output: [1,1]
 * Explanation: The players numbered 1 and 5 compete in the first round.
 * There is no way to make them compete in any other round.
 *
 * Constraints:
 *
 * 2 <= n <= 28
 * 1 <= firstPlayer < secondPlayer <= n
 */
public class EarliestAndLatest {

    int first;
    int second;
    int n;

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        this.first = firstPlayer - 1;
        this.second = secondPlayer - 1;
        this.n = n;
        return new int[]{minDfs(0), maxDfs(0)};
    }

    private int minDfs(int mask) {
        int start = 0;
        int end = n - 1;
        List<Integer> masks = new ArrayList<>();
        if (eliminateTillEncounter(masks, mask, start, end)) return 1;
        else {
            int min = Integer.MAX_VALUE;
            for (int nextMask : masks) min = Math.min(min, 1 + minDfs(nextMask));
            return min;
        }
    }

    private int maxDfs(int mask) {
        int start = 0;
        int end = n - 1;
        List<Integer> masks = new ArrayList<>();
        if (eliminateTillEncounter(masks, mask, start, end)) return 1;
        else {
            int max = Integer.MIN_VALUE;
            for (int nextMask : masks) max = Math.max(max, 1 + maxDfs(nextMask));
            return max;
        }
    }

    private boolean eliminateTillEncounter(List<Integer> masks, int mask, int start, int end) {
        if (start >= end) {
            masks.add(mask);
            return false;
        } else {
            while (start < end && (mask & (1 << start)) != 0) start++; // find first unprocessed from left
            while (start < end && (mask & (1 << end)) != 0) end--; // find first unprocessed from right
            if (start >= end) return eliminateTillEncounter(masks, mask, start + 1, end - 1);
            else if (start == first && end == second) return true;
            else if (start == first || start == second)
                return eliminateTillEncounter(masks, mask | (1 << end), start + 1, end - 1);
            else if (end == first || end == second)
                return eliminateTillEncounter(masks, mask | (1 << start), start + 1, end - 1);
            else return eliminateTillEncounter(masks, mask | (1 << end), start + 1, end - 1) ||
                        eliminateTillEncounter(masks, mask | (1 << start), start + 1, end - 1);
        }

    }

}
