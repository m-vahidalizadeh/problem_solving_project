package leetcode.companies.facebook;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 778. Swim in Rising Water
 * On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
 *
 * Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?
 *
 * Example 1:
 *
 * Input: [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 *
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 * Example 2:
 *
 * Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation:
 *  0  1  2  3  4
 * 24 23 22 21  5
 * 12 13 14 15 16
 * 11 17 18 19 20
 * 10  9  8  7  6
 *
 * The final route is marked in bold.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 * Note:
 *
 * 2 <= N <= 50.
 * grid[i][j] is a permutation of [0, ..., N*N - 1].
 */
public class SwimInWater {

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        pq.add(new int[]{0, 0, grid[0][0]});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int i = curr[0];
            int j = curr[1];
            int currElevation = curr[2];
            if (i == n - 1 && j == n - 1) return currElevation;
            visited.add(new Pair<>(i, j));
            for (int[] dir : directions) {
                int newI = i + dir[0];
                int newJ = j + dir[1];
                if (newI >= 0 && newI < n && newJ >= 0 && newJ < n && !visited.contains(new Pair<>(newI, newJ))) {
                    int newElevation = Math.max(currElevation, grid[newI][newJ]);
                    pq.add(new int[]{newI, newJ, newElevation});
                }
            }
        }
        return -1;
    }

}
