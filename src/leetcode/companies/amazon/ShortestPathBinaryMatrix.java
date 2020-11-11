package leetcode.companies.amazon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1091. Shortest Path in Binary Matrix
 * In an N by N square grid, each cell is either empty (0) or blocked (1).
 *
 * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
 *
 * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
 * C_1 is at location (0, 0) (ie. has value grid[0][0])
 * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
 * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
 * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
 *
 * Example 1:
 *
 * Input: [[0,1],[1,0]]
 *
 * Output: 2
 *
 * Example 2:
 *
 * Input: [[0,0,0],[1,1,0],[1,1,0]]
 *
 * Output: 4
 *
 * Note:
 *
 * 1 <= grid.length == grid[0].length <= 100
 * grid[r][c] is 0 or 1
 */
public class ShortestPathBinaryMatrix {

    int[][] directions;
    int n;
    int m;
    int nMinusOne;
    int mMinusOne;

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null) return -1;
        n = grid.length;
        if (n == 0) return -1;
        nMinusOne = n - 1;
        m = grid[0].length;
        mMinusOne = m - 1;
        if (grid[0][0] == 1 || grid[nMinusOne][mMinusOne] == 1) return -1;
        directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        Set<Integer> visited = new HashSet<>();
        int level = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            int size = q.size();
            level++;
            for (int a = 0; a < size; a++) {
                int cell = q.poll();
                int i = cell / m;
                int j = cell % m;
                if (i == nMinusOne && j == mMinusOne) return level;
                for (int[] dir : directions) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];
                    if (newI < 0 || newI >= n || newJ < 0 || newJ >= m) continue;
                    int newCell = newI * m + newJ;
                    if (visited.contains(newCell) || grid[newI][newJ] == 1) continue;
                    q.add(newCell);
                    visited.add(newCell);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ShortestPathBinaryMatrix s = new ShortestPathBinaryMatrix();
        System.out.println(s.shortestPathBinaryMatrix(new int[][]{{0, 1}, {1, 0}}));
        System.out.println(s.shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
        System.out.println(s.shortestPathBinaryMatrix(new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
    }

}
