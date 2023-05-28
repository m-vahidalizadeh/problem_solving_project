package leetcode.hard;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1632. Rank Transform of a Matrix
 * Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].
 *
 * The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:
 *
 * The rank is an integer starting from 1.
 * If two elements p and q are in the same row or column, then:
 * If p < q then rank(p) < rank(q)
 * If p == q then rank(p) == rank(q)
 * If p > q then rank(p) > rank(q)
 * The rank should be as small as possible.
 * The test cases are generated so that answer is unique under the given rules.
 *
 * Example 1:
 *
 * Input: matrix = [[1,2],[3,4]]
 * Output: [[1,2],[2,3]]
 * Explanation:
 * The rank of matrix[0][0] is 1 because it is the smallest integer in its row and column.
 * The rank of matrix[0][1] is 2 because matrix[0][1] > matrix[0][0] and matrix[0][0] is rank 1.
 * The rank of matrix[1][0] is 2 because matrix[1][0] > matrix[0][0] and matrix[0][0] is rank 1.
 * The rank of matrix[1][1] is 3 because matrix[1][1] > matrix[0][1], matrix[1][1] > matrix[1][0], and both matrix[0][1] and matrix[1][0] are rank 2.
 * Example 2:
 *
 * Input: matrix = [[7,7],[7,7]]
 * Output: [[1,1],[1,1]]
 * Example 3:
 *
 * Input: matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
 * Output: [[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 500
 * -10^9 <= matrix[row][col] <= 10^9
 */
public class RankTransformOfAMatrix {

    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, rank[] = new int[m + n];
        Map<Integer, List<Pair<Integer, Integer>>> invMap = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                invMap.computeIfAbsent(matrix[i][j], l -> new ArrayList<>()).add(new Pair<>(i, j));
            }
        }
        for (Map.Entry<Integer, List<Pair<Integer, Integer>>> entry : invMap.entrySet()) {
            UF uf = new UF(m + n);
            int[] rank2 = rank.clone();
            for (Pair<Integer, Integer> coord : entry.getValue()) {
                Pair<Integer, Integer> res = uf.union(coord.getKey(), coord.getValue() + m);
                rank2[res.getValue()] = Math.max(rank2[res.getValue()], rank2[res.getKey()]);
            }
            for (Pair<Integer, Integer> coord : entry.getValue())
                rank[coord.getKey()] = rank[coord.getValue() + m] = matrix[coord.getKey()][coord.getValue()] = rank2[uf.find(coord.getKey())] + 1;
        }
        return matrix;
    }

    class UF {
        int[] parent;

        public UF(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int x) {
            return parent[x] = parent[x] == x ? x : find(parent[x]);
        }

        public Pair<Integer, Integer> union(int x, int y) {
            int px = find(x), py = find(y);
            parent[px] = py;
            return new Pair<>(px, py);
        }
    }

}
