package leetcode.hard;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 2503. Maximum Number of Points From Grid Queries
 * You are given an m x n integer matrix grid and an array queries of size k.
 *
 * Find an array answer of size k such that for each integer queries[i] you start in the top left cell of the matrix and repeat the following process:
 *
 * If queries[i] is strictly greater than the value of the current cell that you are in, then you get one point if it is your first time visiting this cell, and you can move to any adjacent cell in all 4 directions: up, down, left, and right.
 * Otherwise, you do not get any points, and you end this process.
 * After the process, answer[i] is the maximum number of points you can get. Note that for each query you are allowed to visit the same cell multiple times.
 *
 * Return the resulting array answer.
 *
 * Example 1:
 *
 * Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
 * Output: [5,8,1]
 * Explanation: The diagrams above show which cells we visit to get points for each query.
 * Example 2:
 *
 * Input: grid = [[5,2,1],[1,1,2]], queries = [3]
 * Output: [0]
 * Explanation: We can not get any points because the value of the top left cell is already greater than or equal to 3.
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 105
 * k == queries.length
 * 1 <= k <= 104
 * 1 <= grid[i][j], queries[i] <= 106
 */
public class MaxNumberOfPointsFromGridQueries {

    public int[] maxPoints(int[][] grid, int[] QS) {
        record Node(int i, int j, int v) {
        }
        record Cell(int i, int v) {
        }
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Cell[] qs = new Cell[QS.length];
        for (int i = 0; i < QS.length; i++) qs[i] = new Cell(i, QS[i]);
        Arrays.sort(qs, (a, b) -> a.v - b.v);
        Queue<Node> q = new PriorityQueue<>((a, b) -> a.v - b.v);
        q.offer(new Node(0, 0, grid[0][0]));
        grid[0][0] = -1;
        int m = grid.length, n = grid[0].length, idx = 0, res[] = new int[QS.length];
        for (int j = 0; j < qs.length; j++) {
            while (!q.isEmpty() && qs[j].v > q.peek().v) {
                Node cur = q.poll();
                res[qs[j].i]++;
                for (int[] dir : dirs) {
                    int i2 = cur.i + dir[0], j2 = cur.j + dir[1];
                    if (i2 < 0 || i2 >= m || j2 < 0 || j2 >= n || grid[i2][j2] == -1) continue;
                    q.offer(new Node(i2, j2, grid[i2][j2]));
                    grid[i2][j2] = -1;
                }
            }
            if (j + 1 < qs.length) res[qs[j + 1].i] = res[qs[j].i];
        }
        return res;
    }

}
