package leetcode.companies.facebook;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 317. Shortest Distance from All Buildings
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
 * 1 <= m, n <= 100
 * grid[i][j] is either 0, 1, or 2.
 * There will be at least one building in the grid.
 */
public class ShortestDistanceFromAllBuildings {

    int n;
    int m;
    int[][] canReach;
    int[][] dist;
    int buildingCount;
    int[][] grid;
    int[][] directions;

    public int shortestDistance(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        canReach = new int[n][m];
        dist = new int[n][m];
        this.grid = grid;
        directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    buildingCount++;
                    if (!bfs(i, j)) return -1;
                }
            }
        }
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (canReach[i][j] == buildingCount) minDist = Math.min(minDist, dist[i][j]);
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    private boolean bfs(int row, int col) {
        boolean[][] visited = new boolean[n][m];
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{row, col});
        visited[row][col] = true;
        int l = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            l++;
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];
                for (int[] dir : directions) {
                    int newRow = r + dir[0];
                    int newCol = c + dir[1];
                    if (isValid(newRow, newCol, visited)) {
                        if (grid[newRow][newCol] == 0) {
                            dist[newRow][newCol] += l;
                            canReach[newRow][newCol]++;
                            q.add(new int[]{newRow, newCol});
                        }
                        visited[newRow][newCol] = true;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == 1) return false;
            }
        }
        return true;
    }

    private boolean isValid(int i, int j, boolean[][] visited) {
        if (i < 0 || i >= n || j < 0 || j >= m || visited[i][j] || grid[i][j] == 2) return false;
        return true;
    }

}
