package leetcode.companies.facebook;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 317. Shortest Distance from All Buildings
 * Solved
 * Hard
 *
 * Topics
 *
 * Companies
 * You are given an m x n grid grid of values 0, 1, or 2, where:
 *
 * each 0 marks an empty land that you can pass by freely,
 * each 1 marks a building that you cannot pass through, and
 * each 2 marks an obstacle that you cannot pass through.
 * You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.
 *
 * Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.
 *
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 *
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * Example 1:
 *
 * Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * Output: 7
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
 * So return 7.
 * Example 2:
 *
 * Input: grid = [[1,0]]
 * Output: 1
 * Example 3:
 *
 * Input: grid = [[1]]
 * Output: -1
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0, 1, or 2.
 * There will be at least one building in the grid.
 */
public class ShortestDistance {

    public int shortestDistance(int[][] grid) {
        int buildings = 0, build[][] = new int[grid.length][grid[0].length], res[][] = new int[grid.length][grid[0].length];
        Set<Pair<Integer, Integer>> b = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    buildings++;
                    b.add(new Pair<>(i, j));
                }
            }
        }
        for (Pair<Integer, Integer> pair : b) dfs(pair.getKey(), pair.getValue(), grid, res, build);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 && build[i][j] == buildings && res[i][j] < min) min = Math.min(min, res[i][j]);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private void dfs(int x, int y, int[][] grid, int[][] res, int[][] build) {
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        q.offer(new Pair<>(x, y));
        visited.add(new Pair<>(x, y));
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int a = 0; a < size; a++) {
                Pair<Integer, Integer> curr = q.poll();
                int i = curr.getKey(), j = curr.getValue();
                if (grid[i][j] == 0) {
                    res[i][j] += level;
                    build[i][j]++;
                }
                for (int[] dir : new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}}) {
                    int newI = i + dir[0], newJ = j + dir[1];
                    if (newI < 0 || newI >= grid.length || newJ < 0 || newJ >= grid[0].length || grid[newI][newJ] != 0 || visited.contains(new Pair<>(newI, newJ)))
                        continue;
                    q.add(new Pair<>(newI, newJ));
                    visited.add(new Pair<>(newI, newJ));
                }
            }
            level++;
        }
    }

}
