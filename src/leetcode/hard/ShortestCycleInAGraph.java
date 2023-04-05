package leetcode.hard;

import java.util.*;

/**
 * 2608. Shortest Cycle in a Graph
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1. The edges in the graph are represented by a given 2D integer array edges, where edges[i] = [ui, vi] denotes an edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 *
 * Return the length of the shortest cycle in the graph. If no cycle exists, return -1.
 *
 * A cycle is a path that starts and ends at the same node, and each edge in the path is used only once.
 *
 * Example 1:
 *
 * Input: n = 7, edges = [[0,1],[1,2],[2,0],[3,4],[4,5],[5,6],[6,3]]
 * Output: 3
 * Explanation: The cycle with the smallest length is : 0 -> 1 -> 2 -> 0
 * Example 2:
 *
 * Input: n = 4, edges = [[0,1],[0,2]]
 * Output: -1
 * Explanation: There are no cycles in this graph.
 *
 * Constraints:
 *
 * 2 <= n <= 1000
 * 1 <= edges.length <= 1000
 * edges[i].length == 2
 * 0 <= ui, vi < n
 * ui != vi
 * There are no repeated edges.
 */
public class ShortestCycleInAGraph {

    public int findShortestCycle(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], s -> new HashSet<>()).add(e[1]);
            graph.computeIfAbsent(e[1], s -> new HashSet<>()).add(e[0]);
        }
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            Integer[] dist = new Integer[n], parent = new Integer[n];
            Arrays.fill(parent, -1);
            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            dist[i] = 0;
            while (!q.isEmpty()) {
                int curr = q.poll();
                for (int child : graph.getOrDefault(curr, Collections.emptySet())) {
                    if (dist[child] == null) {
                        dist[child] = dist[curr] + 1;
                        parent[child] = curr;
                        q.offer(child);
                    } else if (parent[child] != curr && parent[curr] != child) {
                        shortest = Math.min(shortest, dist[child] + dist[curr] + 1);
                    }
                }
            }
        }
        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }

}
