package leetcode.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * 1761. Minimum Degree of a Connected Trio in a Graph
 * You are given an undirected graph. You are given an integer n which is the number of nodes in the graph and an array edges, where each edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.
 *
 * A connected trio is a set of three nodes where there is an edge between every pair of them.
 *
 * The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.
 *
 * Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.
 *
 * Example 1:
 *
 * Input: n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
 * Output: 3
 * Explanation: There is exactly one trio, which is [1,2,3]. The edges that form its degree are bolded in the figure above.
 * Example 2:
 *
 * Input: n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
 * Output: 0
 * Explanation: There are exactly three trios:
 * 1) [1,4,3] with degree 0.
 * 2) [2,5,6] with degree 2.
 * 3) [5,6,7] with degree 2.
 *
 * Constraints:
 *
 * 2 <= n <= 400
 * edges[i].length == 2
 * 1 <= edges.length <= n * (n-1) / 2
 * 1 <= ui, vi <= n
 * ui != vi
 * There are no repeated edges.
 */
public class MinDegreeOfAConnectedTrio {

    public int minTrioDegree(int n, int[][] edges) {
        Set<Integer>[] graph = new Set[n + 1];
        int[] degree = new int[n + 1];
        for (int[] edge : edges) {
            int u = Math.min(edge[0], edge[1]);
            int v = Math.max(edge[0], edge[1]);
            if (graph[u] == null) graph[u] = new HashSet<>();
            graph[u].add(v); // lower node points to higher node. undirected -> directed
            degree[u]++;
            degree[v]++;
        }
        int res = Integer.MAX_VALUE;
        for (int n1 = 1; n1 <= n; n1++) {
            if (graph[n1] == null) continue;
            for (int n2 : graph[n1]) {
                for (int n3 : graph[n1]) {
                    if (graph[n2] != null && graph[n2].contains(n3)) {
                        res = Math.min(res, degree[n1] + degree[n2] + degree[n3] - 6); // remove 6 internal edges of the trio each node has 2 edges
                    }
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
