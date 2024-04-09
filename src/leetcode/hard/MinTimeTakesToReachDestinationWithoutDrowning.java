package leetcode.hard;

import javafx.util.Pair;

import java.util.*;

/**
 * 2814. Minimum Time Takes to Reach Destination Without Drowning
 * Solved
 * Hard
 *
 * Topics
 *
 * Hint
 * You are given an n * m 0-indexed grid of string land. Right now, you are standing at the cell that contains "S", and you want to get to the cell containing "D". There are three other types of cells in this land:
 *
 * ".": These cells are empty.
 * "X": These cells are stone.
 * "*": These cells are flooded.
 * At each second, you can move to a cell that shares a side with your current cell (if it exists). Also, at each second, every empty cell that shares a side with a flooded cell becomes flooded as well.
 * There are two problems ahead of your journey:
 *
 * You can't step on stone cells.
 * You can't step on flooded cells since you will drown (also, you can't step on a cell that will be flooded at the same time as you step on it).
 * Return the minimum time it takes you to reach the destination in seconds, or -1 if it is impossible.
 *
 * Note that the destination will never be flooded.
 *
 * Example 1:
 *
 * Input: land = [["D",".","*"],[".",".","."],[".","S","."]]
 * Output: 3
 * Explanation: The picture below shows the simulation of the land second by second. The blue cells are flooded, and the gray cells are stone.
 * Picture (0) shows the initial state and picture (3) shows the final state when we reach destination. As you see, it takes us 3 second to reach destination and the answer would be 3.
 * It can be shown that 3 is the minimum time needed to reach from S to D.
 *
 * Example 2:
 *
 * Input: land = [["D","X","*"],[".",".","."],[".",".","S"]]
 * Output: -1
 * Explanation: The picture below shows the simulation of the land second by second. The blue cells are flooded, and the gray cells are stone.
 * Picture (0) shows the initial state. As you see, no matter which paths we choose, we will drown at the 3rd second. Also the minimum path takes us 4 seconds to reach from S to D.
 * So the answer would be -1.
 *
 * Example 3:
 *
 * Input: land = [["D",".",".",".","*","."],[".","X",".","X",".","."],[".",".",".",".","S","."]]
 * Output: 6
 * Explanation: It can be shown that we can reach destination in 6 seconds. Also it can be shown that 6 is the minimum seconds one need to reach from S to D.
 *
 * Constraints:
 *
 * 2 <= n, m <= 100
 * land consists only of "S", "D", ".", "*" and "X".
 * Exactly one of the cells is equal to "S".
 * Exactly one of the cells is equal to "D".
 */
public class MinTimeTakesToReachDestinationWithoutDrowning {

    public int minimumSeconds(List<List<String>> land) {
        Pair<Integer, Integer> source = null, dest = null;
        int n = land.size();
        int m = land.get(0).size();
        int[][] l = new int[n][land.get(0).size()], f = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], Integer.MAX_VALUE);
        Deque<Pair<Integer, Integer>> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < land.get(0).size(); j++) {
                if (land.get(i).get(j).equals("S")) source = new Pair<>(i, j);
                else if (land.get(i).get(j).equals("D")) dest = new Pair<>(i, j);
                else if (land.get(i).get(j).equals("X")) l[i][j] = 1;
                else if (land.get(i).get(j).equals("*")) {
                    q.addLast(new Pair<>(i, j));
                    f[i][j] = 0;
                    visited[i][j] = true;
                }
            }
        }
        int t = 0;
        while (!q.isEmpty()) {
            Deque<Pair<Integer, Integer>> next = new ArrayDeque<>();
            int size = q.size();
            for (int a = 0; a < size; a++) {
                Pair<Integer, Integer> curr = q.pollFirst();
                int i = curr.getKey();
                int j = curr.getValue();
                f[i][j] = t;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        if (x != 0 && y != 0) continue;
                        int i2 = i + x, j2 = j + y;
                        if (dest.getKey() == i2 && dest.getValue() == j2) continue;
                        if (i2 >= 0 && i2 < n && j2 >= 0 && j2 < m && l[i2][j2] == 0 && !visited[i2][j2]) {
                            visited[i2][j2] = true;
                            next.addLast(new Pair<>(i2, j2));
                        }
                    }
                }
            }
            t++;
            q = next;
        }
        if (source.equals(dest)) return 0;
        q = new ArrayDeque<>();
        q.addLast(source);
        t = 0;
        while (!q.isEmpty()) {
            Deque<Pair<Integer, Integer>> next = new ArrayDeque<>();
            int size = q.size();
            for (int a = 0; a < size; a++) {
                Pair<Integer, Integer> curr = q.pollFirst();
                int i = curr.getKey();
                int j = curr.getValue();
                l[i][j] = -1;
                if (curr.getKey() == dest.getKey() && curr.getValue() == dest.getValue()) return t;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        if (x != 0 && y != 0) continue;
                        int i2 = i + x, j2 = j + y;
                        if (i2 >= 0 && i2 < n && j2 >= 0 && j2 < m && l[i2][j2] == 0 && f[i2][j2] > t + 1) {
                            l[i2][j2] = -1;
                            next.addLast(new Pair<>(i2, j2));
                        }
                    }
                }
            }
            t++;
            q = next;
        }
        return -1;
    }

}
