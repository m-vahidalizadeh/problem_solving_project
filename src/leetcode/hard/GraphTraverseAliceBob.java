package leetcode.hard;

/**
 * 1579. Remove Max Number of Edges to Keep Graph Fully Traversable
 * Alice and Bob have an undirected graph of n nodes and 3 types of edges:
 *
 * Type 1: Can be traversed by Alice only.
 * Type 2: Can be traversed by Bob only.
 * Type 3: Can by traversed by both Alice and Bob.
 * Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi, find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.
 *
 * Return the maximum number of edges you can remove, or return -1 if it's impossible for the graph to be fully traversed by Alice and Bob.
 *
 * Example 1:
 *
 * Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
 * Output: 2
 * Explanation: If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob. Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.
 * Example 2:
 *
 * Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
 * Output: 0
 * Explanation: Notice that removing any edge will not make the graph fully traversable by Alice and Bob.
 * Example 3:
 *
 * Input: n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
 * Output: -1
 * Explanation: In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1. Therefore it's impossible to make the graph fully traversable.
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * 1 <= edges.length <= min(10^5, 3 * n * (n-1) / 2)
 * edges[i].length == 3
 * 1 <= edges[i][0] <= 3
 * 1 <= edges[i][1] < edges[i][2] <= n
 * All tuples (typei, ui, vi) are distinct.
 */
public class GraphTraverseAliceBob {

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int edgeCount = 0;
        DSU alice = new DSU(n + 1);
        DSU bob = new DSU(n + 1);
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                edgeCount += alice.union(edge[1], edge[2]);
                bob.union(edge[1], edge[2]);
            }
        }
        for (int[] edge : edges) {
            if (edge[0] == 1) edgeCount += alice.union(edge[1], edge[2]);
            else if (edge[0] == 2) edgeCount += bob.union(edge[1], edge[2]);
        }
        return alice.edgeCount == n - 1 && bob.edgeCount == n - 1 ? edgeCount : -1;
    }

    public class DSU {
        int[] parents;
        int[] counts;
        int edgeCount;

        public DSU(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) parents[i] = i;
            counts = new int[n];
            edgeCount = 0;
        }

        public int find(int x) {
            while (x != parents[x]) x = parents[x];
            return x;
        }

        public int union(int x, int y) {
            int rX = find(x);
            int rY = find(y);
            if (rX == rY) return 1;
            edgeCount++;
            if (counts[rX] > counts[rY]) {
                parents[rY] = rX;
                counts[rX] += counts[rY];
            } else {
                parents[rX] = rY;
                counts[rY] += counts[rX];
            }
            return 0;
        }
    }

}
