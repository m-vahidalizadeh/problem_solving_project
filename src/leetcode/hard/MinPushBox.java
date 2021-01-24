package leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1263. Minimum Moves to Move a Box to Their Target Location
 * Storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.
 *
 * The game is represented by a grid of size m x n, where each element is a wall, floor, or a box.
 *
 * Your task is move the box 'B' to the target position 'T' under the following rules:
 *
 * Player is represented by character 'S' and can move up, down, left, right in the grid if it is a floor (empy cell).
 * Floor is represented by character '.' that means free cell to walk.
 * Wall is represented by character '#' that means obstacle  (impossible to walk there).
 * There is only one box 'B' and one target cell 'T' in the grid.
 * The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a push.
 * The player cannot walk through the box.
 * Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.
 *
 * Example 1:
 *
 * Input: grid = [["#","#","#","#","#","#"],
 *                ["#","T","#","#","#","#"],
 *                ["#",".",".","B",".","#"],
 *                ["#",".","#","#",".","#"],
 *                ["#",".",".",".","S","#"],
 *                ["#","#","#","#","#","#"]]
 * Output: 3
 * Explanation: We return only the number of times the box is pushed.
 * Example 2:
 *
 * Input: grid = [["#","#","#","#","#","#"],
 *                ["#","T","#","#","#","#"],
 *                ["#",".",".","B",".","#"],
 *                ["#","#","#","#",".","#"],
 *                ["#",".",".",".","S","#"],
 *                ["#","#","#","#","#","#"]]
 * Output: -1
 * Example 3:
 *
 * Input: grid = [["#","#","#","#","#","#"],
 *                ["#","T",".",".","#","#"],
 *                ["#",".","#","B",".","#"],
 *                ["#",".",".",".",".","#"],
 *                ["#",".",".",".","S","#"],
 *                ["#","#","#","#","#","#"]]
 * Output: 5
 * Explanation:  push the box down, left, left, up and up.
 * Example 4:
 *
 * Input: grid = [["#","#","#","#","#","#","#"],
 *                ["#","S","#",".","B","T","#"],
 *                ["#","#","#","#","#","#","#"]]
 * Output: -1
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 20
 * 1 <= n <= 20
 * grid contains only characters '.', '#',  'S' , 'T', or 'B'.
 * There is only one character 'S', 'B' and 'T' in the grid.
 */
public class MinPushBox {

    char[][] grid;
    int n;
    int m;
    int[][] directions;

    public int minPushBox(char[][] grid) {
        int push = 0;
        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length;
        boolean[][][] visited = new boolean[n][m][4];
        directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int[] owner = new int[2];
        int[] box = new int[2];
        int[] target = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'B') box = new int[]{i, j};
                else if (grid[i][j] == 'T') target = new int[]{i, j};
                else if (grid[i][j] == 'S') owner = new int[]{i, j};
            }
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{box[0], box[1], owner[0], owner[1]});
        while (!q.isEmpty()) {
            for (int i = 0, l = q.size(); i < l; i++) {
                int[] curr = q.poll();
                if (curr[0] == target[0] && curr[1] == target[1]) return push;
                for (int j = 0; j < directions.length; j++) {
                    if (visited[curr[0]][curr[1]][j]) continue;
                    int[] dir = directions[j];
                    int newOwnRow = curr[0] + dir[0];
                    int newOwnCol = curr[1] + dir[1];
                    if (newOwnRow < 0 || newOwnRow >= n || newOwnCol < 0 || newOwnCol >= m || grid[newOwnRow][newOwnCol] == '#')
                        continue;
                    int newBoxRow = curr[0] - dir[0];
                    int newBoxCol = curr[1] - dir[1];
                    if (newBoxRow < 0 || newBoxRow >= n || newBoxCol < 0 || newBoxCol >= m || grid[newBoxRow][newBoxCol] == '#')
                        continue;
                    if (!isReachable(newOwnRow, newOwnCol, curr)) continue;
                    visited[curr[0]][curr[1]][j] = true;
                    q.add(new int[]{newBoxRow, newBoxCol, curr[0], curr[1]});
                }
            }
            push++;
        }
        return -1;
    }

    private boolean isReachable(int i, int j, int[] start) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{start[2], start[3]});
        boolean[][] visited = new boolean[n][m];
        visited[start[0]][start[1]] = true;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[0] == i && curr[1] == j) return true;
            for (int[] dir : directions) {
                int newR = curr[0] - dir[0];
                int newC = curr[1] - dir[1];
                if (newR < 0 || newR >= n || newC < 0 || newC >= m || visited[newR][newC] || grid[newR][newC] == '#')
                    continue;
                visited[newR][newC] = true;
                q.add(new int[]{newR, newC});
            }
        }
        return false;
    }

}
