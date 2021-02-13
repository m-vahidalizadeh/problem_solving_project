package leetcode.hard;

import java.util.*;

/**
 * 864. Shortest Path to Get All Keys
 * We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.
 *
 * We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.
 *
 * For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.
 *
 * Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.
 *
 * Example 1:
 *
 * Input: ["@.a.#","###.#","b.A.B"]
 * Output: 8
 * Example 2:
 *
 * Input: ["@..aA","..B#.","....b"]
 * Output: 6
 *
 * Note:
 *
 * 1 <= grid.length <= 30
 * 1 <= grid[0].length <= 30
 * grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
 * The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.
 */
public class ShortestPathToAllKeys {

    String[] grid;
    int n;
    int m;

    public int shortestPathAllKeys(String[] grid) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length();
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int[] start = null;
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') start = new int[]{i, j};
                else if (isKey(c)) k++;
            }
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{start[0], start[1], 0}); // i,j,mask
        int goal = (1 << k) - 1;
        boolean[][][] visited = new boolean[n][m][goal + 1];
        visited[0][0][0] = true;
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                if (curr[2] == goal) return steps;
                for (int[] dir : directions) {
                    int newI = curr[0] + dir[0];
                    int newJ = curr[1] + dir[1];
                    int key = curr[2];
                    if (!isValid(newI, newJ)) continue;
                    char c = grid[newI].charAt(newJ);
                    if (isKey(c)) key |= (1 << (c - 'a'));
                    if (isLock(c) && (key >> (c - 'A') & 1) == 0) continue;
                    if (!visited[newI][newJ][key]) {
                        q.add(new int[]{newI, newJ, key});
                        visited[newI][newJ][key] = true;
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    private boolean isLock(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private boolean isKey(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m && grid[i].charAt(j) != '#';
    }

}
