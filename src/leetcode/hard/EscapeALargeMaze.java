package leetcode.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * 1036. Escape a Large Maze
 * There is a 1 million by 1 million grid on an XY-plane, and the coordinates of each grid square are (x, y).
 *
 * We start at the source = [sx, sy] square and want to reach the target = [tx, ty] square. There is also an array of blocked squares, where each blocked[i] = [xi, yi] represents a blocked square with coordinates (xi, yi).
 *
 * Each move, we can walk one square north, east, south, or west if the square is not in the array of blocked squares. We are also not allowed to walk outside of the grid.
 *
 * Return true if and only if it is possible to reach the target square from the source square through a sequence of valid moves.
 *
 * Example 1:
 *
 * Input: blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
 * Output: false
 * Explanation: The target square is inaccessible starting from the source square because we cannot move.
 * We cannot move north or east because those squares are blocked.
 * We cannot move south or west because we cannot go outside of the grid.
 * Example 2:
 *
 * Input: blocked = [], source = [0,0], target = [999999,999999]
 * Output: true
 * Explanation: Because there are no blocked cells, it is possible to reach the target square.
 *
 * Constraints:
 *
 * 0 <= blocked.length <= 200
 * blocked[i].length == 2
 * 0 <= xi, yi < 106
 * source.length == target.length == 2
 * 0 <= sx, sy, tx, ty < 106
 * source != target
 * It is guaranteed that source and target are not blocked.
 */
public class EscapeALargeMaze {

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<Long> blockedSet = new HashSet<>();
        for (int[] coordinate : blocked) blockedSet.add(coordinate[0] * 1_000_000L + coordinate[1]);
        return check(blockedSet, source, target, source, new HashSet<>()) && check(blockedSet, target, source, target, new HashSet<>());
    }

    private boolean check(Set<Long> blocked, int[] source, int[] target, int[] curr, Set<Long> visited) {
        if (Math.abs(curr[0] - source[0]) == 200 || Math.abs(curr[1] - source[1]) == 200 || (visited.size() > 0 && curr[0] == target[0] && curr[1] == target[1])) return true;
        visited.add(curr[0] * 1_000_000L + curr[1]);
        for (int[] dir : new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}}) {
            int[] newCurr = new int[]{curr[0] + dir[0], curr[1] + dir[1]};
            if (newCurr[0] >= 0 && newCurr[0] < 1_000_000L && newCurr[1] >= 0 && newCurr[1] < 1_000_000L && !visited.contains(newCurr[0] * 1_000_000L + newCurr[1]) && !blocked.contains(newCurr[0] * 1_000_000L + newCurr[1]) && check(blocked, source, target, newCurr, visited)) return true;
        }
        return false;
    }

}
