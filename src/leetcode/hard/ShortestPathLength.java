package leetcode.hard;

import java.util.PriorityQueue;

/**
 * 847. Shortest Path Visiting All Nodes
 * An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
 *
 * graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.
 *
 * Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 *
 * Example 1:
 *
 * Input: [[1,2,3],[0],[0],[0]]
 * Output: 4
 * Explanation: One possible path is [1,0,2,0,3]
 * Example 2:
 *
 * Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * Output: 4
 * Explanation: One possible path is [0,1,4,2,3]
 *
 * Note:
 *
 * 1 <= graph.length <= 12
 * 0 <= graph[i].length < graph.length
 */
public class ShortestPathLength {

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int allVisited = (int) Math.pow(2, n) - 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // last node,bit mast,path length
        for (int i = 0; i < n; i++) pq.add(new int[]{i, 1 << i, 1});
        boolean[][] visited = new boolean[n][allVisited + 1];
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int lastNode = curr[0];
            int mask = curr[1];
            int length = curr[2];
            if (mask == allVisited) return length - 1;
            if (visited[lastNode][mask]) continue;
            visited[lastNode][mask] = true;
            for (int nei : graph[lastNode]) pq.add(new int[]{nei, mask | 1 << nei, length + 1});
        }
        return -1;
    }

}
