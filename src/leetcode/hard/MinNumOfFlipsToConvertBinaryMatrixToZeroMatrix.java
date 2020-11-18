package leetcode.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
 * Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbours of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighboors if they share one edge.
 *
 * Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.
 *
 * Binary matrix is a matrix with all cells equal to 0 or 1 only.
 *
 * Zero matrix is a matrix with all cells equal to 0.
 *
 * Example 1:
 *
 * Input: mat = [[0,0],[0,1]]
 * Output: 3
 * Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
 * Example 2:
 *
 * Input: mat = [[0]]
 * Output: 0
 * Explanation: Given matrix is a zero matrix. We don't need to change it.
 * Example 3:
 *
 * Input: mat = [[1,1,1],[1,0,1],[0,0,0]]
 * Output: 6
 * Example 4:
 *
 * Input: mat = [[1,0,0],[1,0,0]]
 * Output: -1
 * Explanation: Given matrix can't be a zero matrix
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[0].length
 * 1 <= m <= 3
 * 1 <= n <= 3
 * mat[i][j] is 0 or 1.
 */
public class MinNumOfFlipsToConvertBinaryMatrixToZeroMatrix {

    public int minFlips(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int bits = 0;
        int steps = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bits = (bits << 1) | mat[i][j];
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(bits);
        Set<Integer> visited = new HashSet<>();
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; k--) {
                int b = q.poll();
                if (b == 0) return steps;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        int temp = b;
                        temp = temp ^ (1 << (i * m + j));
                        for (int[] dir : directions) {
                            int newI = i + dir[0];
                            int newJ = j + dir[1];
                            if (newI < 0 || newI >= n || newJ < 0 || newJ >= m) continue;
                            temp = temp ^ (1 << (newI * m + newJ));
                        }
                        if (!visited.contains(temp)) {
                            visited.add(temp);
                            q.add(temp);
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }

}
