package leetcode.companies.amazon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 1102. Path With Maximum Minimum Value
 * Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].
 *
 * The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.
 *
 * A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).
 *
 * Example 1:
 *
 * Input: [[5,4,5],[1,2,6],[7,4,6]]
 * Output: 4
 * Explanation:
 * The path with the maximum score is highlighted in yellow.
 * Example 2:
 *
 * Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
 * Output: 2
 * Example 3:
 *
 * Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
 * Output: 3
 *
 * Note:
 *
 * 1 <= R, C <= 100
 * 0 <= A[i][j] <= 10^9
 */
public class MinScorePath {

    public class Node {
        int distance;
        int i;
        int j;

        public Node(int distance, int i, int j) {
            this.distance = distance;
            this.i = i;
            this.j = j;
        }
    }

    int n;
    int m;
    int nMinusOne;
    int mMinusOne;
    int[][] directions;

    public int maximumMinimumPath(int[][] A) {
        n = A.length;
        nMinusOne = n - 1;
        m = A[0].length;
        mMinusOne = m - 1;
        if (A[0][0] == 0 || A[nMinusOne][mMinusOne] == 0) return 0;
        directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.distance - a.distance);
        pq.add(new Node(A[0][0], 0, 0));
        Set<Integer> visited = new HashSet<>();
        int[][] dijkstra = new int[n][m];
        for (int[] row : dijkstra) Arrays.fill(row, Integer.MIN_VALUE);
        while (!pq.isEmpty()) {
            Node currNode = pq.poll();
            int cell = currNode.i * m + currNode.j;
            if (visited.contains(cell)) continue;
            visited.add(cell);
            if (currNode.i == nMinusOne && currNode.j == mMinusOne) return currNode.distance;
            for (int[] dir : directions) {
                int newI = currNode.i + dir[0];
                int newJ = currNode.j + dir[1];
                if (newI < 0 || newI >= n || newJ < 0 || newJ >= m) continue;
                int newDistance = Math.min(currNode.distance, A[newI][newJ]);
                if (dijkstra[newI][newJ] < newDistance) {
                    dijkstra[newI][newJ] = newDistance;
                    pq.add(new Node(newDistance, newI, newJ));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MinScorePath m = new MinScorePath();
        System.out.println(m.maximumMinimumPath(new int[][]{{5, 4, 5}, {1, 2, 6}, {7, 4, 6}}));
        System.out.println(m.maximumMinimumPath(new int[][]{{2, 2, 1, 2, 2, 2}, {1, 2, 2, 2, 1, 2}}));
        System.out.println(m.maximumMinimumPath(new int[][]{{3, 4, 6, 3, 4}, {0, 2, 1, 1, 7}, {8, 8, 3, 2, 7}, {3, 2, 4, 9, 8}, {4, 1, 2, 0, 0}, {4, 6, 5, 4, 3}}));
        System.out.println(m.maximumMinimumPath(new int[][]{{1, 1, 0, 3, 1, 1}, {0, 1, 0, 1, 1, 0}, {3, 3, 1, 3, 1, 1}, {0, 3, 2, 2, 0, 0}, {1, 0, 1, 2, 3, 0}}));
    }

}
