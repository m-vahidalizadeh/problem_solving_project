package leetcode.hard;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1782. Count Pairs Of Nodes
 * You are given an undirected graph defined by an integer n, the number of nodes, and a 2D integer array edges, the edges in the graph, where edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi. You are also given an integer array queries.
 *
 * Let incident(a, b) be defined as the number of edges that are connected to either node a or b.
 *
 * The answer to the jth query is the number of pairs of nodes (a, b) that satisfy both of the following conditions:
 *
 * a < b
 * incident(a, b) > queries[j]
 * Return an array answers such that answers.length == queries.length and answers[j] is the answer of the jth query.
 *
 * Note that there can be multiple edges between the same two nodes.
 *
 * Example 1:
 *
 * Input: n = 4, edges = [[1,2],[2,4],[1,3],[2,3],[2,1]], queries = [2,3]
 * Output: [6,5]
 * Explanation: The calculations for incident(a, b) are shown in the table above.
 * The answers for each of the queries are as follows:
 * - answers[0] = 6. All the pairs have an incident(a, b) value greater than 2.
 * - answers[1] = 5. All the pairs except (3, 4) have an incident(a, b) value greater than 3.
 * Example 2:
 *
 * Input: n = 5, edges = [[1,5],[1,5],[3,4],[2,5],[1,3],[5,1],[2,3],[2,5]], queries = [1,2,3,4,5]
 * Output: [10,10,9,8,6]
 *
 * Constraints:
 *
 * 2 <= n <= 2 * 104
 * 1 <= edges.length <= 105
 * 1 <= ui, vi <= n
 * ui != vi
 * 1 <= queries.length <= 20
 * 0 <= queries[j] < edges.length
 */
public class CountPairsOfNodes {

    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] degree = new int[n + 1]; // degree of each node
        Map<Pair<Integer, Integer>, Integer> sharedCount = new HashMap<>(); // shared edges between u and v
        for (int[] edge : edges) {
            int u = Math.min(edge[0], edge[1]); // u<v in (u,v)
            int v = Math.max(edge[0], edge[1]);
            degree[u]++;
            degree[v]++;
            Pair<Integer, Integer> pair = new Pair<>(u, v);
            sharedCount.put(pair, sharedCount.getOrDefault(pair, 0) + 1);
        }
        int[] sortedDegree = degree.clone(); // sorted degrees of nodes
        Arrays.sort(sortedDegree);
        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) { // iterate over the queries
            int l = 1; // 1 based index
            int r = n;
            int count = 0;
            while (l < r) {
                if (sortedDegree[l] + sortedDegree[r] <= queries[q]) l++;
                else {
                    count += r - l; // Add all the pairs (u,v) which v is r
                    r--; // skip the current r
                }
            }
            for (Map.Entry<Pair<Integer, Integer>, Integer> entry : sharedCount.entrySet()) { // remove the wrong counts
                int u = entry.getKey().getKey();
                int v = entry.getKey().getValue();
                int edgeCountUV = entry.getValue();
                if (degree[u] + degree[v] > queries[q] && degree[u] + degree[v] - edgeCountUV <= queries[q])
                    count--; // when we remove the shared, it doesn't meet the criteria
            }
            ans[q] = count;
        }
        return ans;
    }

}
