package leetcode.hard;

import java.util.*;

/**
 * 1489. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree
 * Given a weighted undirected connected graph with n vertices numbered from 0 to n - 1, and an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional and weighted edge between nodes ai and bi. A minimum spanning tree (MST) is a subset of the graph's edges that connects all vertices without cycles and with the minimum possible total edge weight.
 *
 * Find all the critical and pseudo-critical edges in the given graph's minimum spanning tree (MST). An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. On the other hand, a pseudo-critical edge is that which can appear in some MSTs but not all.
 *
 * Note that you can return the indices of the edges in any order.
 *
 * Example 1:
 *
 * Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
 * Output: [[0,1],[2,3,4,5]]
 * Explanation: The figure above describes the graph.
 * The following figure shows all the possible MSTs:
 *
 * Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
 * The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.
 * Example 2:
 *
 * Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
 * Output: [[],[0,1,2,3]]
 * Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * 1 <= edges.length <= min(200, n * (n - 1) / 2)
 * edges[i].length == 3
 * 0 <= ai < bi < n
 * 1 <= weighti <= 1000
 * All pairs (ai, bi) are distinct.
 */
public class CriticalAndPCriticalInMST {

    public class UF { // Data structure to find the MST- Union Find
        int[] parents;
        int count; // count of the nodes in the current MST
        int weightSum; // sum of the weights of the current MST

        public UF(int n) {
            parents = new int[n];
            count = 0;
            weightSum = 0;
            for (int i = 0; i < n; i++) parents[i] = i; // parents of the nodes- originally root of each node is itself
        }

        public int find(int x) { // find the root of x
            while (x != parents[x]) x = parents[x]; // go till x==parents[x]-> this is the root
            return x;
        }

        public void join(int x, int y, int weight) { // Join root of x and root of y if they are not the same
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parents[rootX] = rootY;
                count++; // One more node is connected
                weightSum += weight; // The weight of the current edges should be added to the cost of current MST
            }
        }
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<Integer> critical = new ArrayList<>(); // critical
        List<Integer> pCritical = new ArrayList<>(); // pseudo-critical
        Map<int[], Integer> indexMap = new HashMap<>();
        for (int i = 0; i < edges.length; i++) indexMap.put(edges[i], i); // Keep track of original indexes
        Arrays.sort(edges, (a, b) -> a[2] - b[2]); // Sort the edges to find the MST
        int minMSTCost = findMSTCost(null, null, n, edges); // Find the original MST (with all the edges)
        for (int i = 0; i < edges.length; i++) {
            int index = indexMap.get(edges[i]); // Fetch the original index
            if (findMSTCost(null, i, n, edges) > minMSTCost) critical.add(index); // critical if force exclude edge i -> MST cost > min MST cost
            else if (findMSTCost(i, null, n, edges) == minMSTCost) pCritical.add(index); // pseudo-critical if force include edge i -> MST cost =min MST cost
        }
        return List.of(critical, pCritical);
    }

    private int findMSTCost(Integer include, Integer exclude, int n, int[][] edges) {
        UF uf = new UF(n); // create a new union find
        if (include != null)
            uf.join(edges[include][0], edges[include][1], edges[include][2]); // is there any force include?
        for (int i = 0; i < edges.length; i++) {
            if (exclude != null && i == exclude) continue; // is there any force exclude?
            uf.join(edges[i][0], edges[i][1], edges[i][2]); // include the current edge
        }
        return uf.count == n - 1 ? uf.weightSum : Integer.MAX_VALUE; // Could we find any valid MST (connected tree- includes all the nodes)
    }

}
