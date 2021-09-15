package leetcode.hard;

import java.util.*;

/**
 * 1857. Largest Color Value in a Directed Graph
 * There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
 *
 * You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
 *
 * A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.
 *
 * Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.
 *
 * Example 1:
 *
 * Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
 * Example 2:
 *
 * Input: colors = "a", edges = [[0,0]]
 * Output: -1
 * Explanation: There is a cycle from 0 to 0.
 *
 * Constraints:
 *
 * n == colors.length
 * m == edges.length
 * 1 <= n <= 105
 * 0 <= m <= 105
 * colors consists of lowercase English letters.
 * 0 <= aj, bj < n
 */
public class LargestColorValueInDG {

    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<Integer>[] graph = new List[n];
        Map<Integer, Integer> indegree = new HashMap<>();
        int[][] dp = new int[n][26];
        int[] colorValues = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        int visited = 0;

        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            indegree.put(v, indegree.getOrDefault(v, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            colorValues[i] = colors.charAt(i) - 'a';
        }
        for (int u = 0; u < n; u++) {
            if (!indegree.containsKey(u)) queue.add(u);
            dp[u][colorValues[u]] = 1;
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            visited++;
            for (int v : graph[u]) {
                for (int c = 0; c < 26; c++) {
                    dp[v][c] = Math.max(dp[v][c], dp[u][c] + (c == colorValues[v] ? 1 : 0));
                }
                indegree.put(v, indegree.get(v) - 1);
                if (indegree.get(v) == 0) {
                    queue.add(v);
                    indegree.remove(v);
                }
            }

        }
        if (visited < n) return -1;
        int max = dp[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

}
