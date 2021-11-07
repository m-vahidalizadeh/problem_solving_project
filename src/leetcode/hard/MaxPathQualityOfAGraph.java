package leetcode.hard;

import java.util.*;

/**
 * 2065. Maximum Path Quality of a Graph
 * There is an undirected graph with n nodes numbered from 0 to n - 1 (inclusive). You are given a 0-indexed integer array values where values[i] is the value of the ith node. You are also given a 0-indexed 2D integer array edges, where each edges[j] = [uj, vj, timej] indicates that there is an undirected edge between the nodes uj and vj, and it takes timej seconds to travel between the two nodes. Finally, you are given an integer maxTime.
 *
 * A valid path in the graph is any path that starts at node 0, ends at node 0, and takes at most maxTime seconds to complete. You may visit the same node multiple times. The quality of a valid path is the sum of the values of the unique nodes visited in the path (each node's value is added at most once to the sum).
 *
 * Return the maximum quality of a valid path.
 *
 * Note: There are at most four edges connected to each node.
 *
 * Example 1:
 *
 * Input: values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
 * Output: 75
 * Explanation:
 * One possible path is 0 -> 1 -> 0 -> 3 -> 0. The total time taken is 10 + 10 + 10 + 10 = 40 <= 49.
 * The nodes visited are 0, 1, and 3, giving a maximal path quality of 0 + 32 + 43 = 75.
 * Example 2:
 *
 * Input: values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
 * Output: 25
 * Explanation:
 * One possible path is 0 -> 3 -> 0. The total time taken is 10 + 10 = 20 <= 30.
 * The nodes visited are 0 and 3, giving a maximal path quality of 5 + 20 = 25.
 * Example 3:
 *
 * Input: values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
 * Output: 7
 * Explanation:
 * One possible path is 0 -> 1 -> 3 -> 1 -> 0. The total time taken is 10 + 13 + 13 + 10 = 46 <= 50.
 * The nodes visited are 0, 1, and 3, giving a maximal path quality of 1 + 2 + 4 = 7.
 * Example 4:
 *
 * Input: values = [0,1,2], edges = [[1,2,10]], maxTime = 10
 * Output: 0
 * Explanation:
 * The only path is 0. The total time taken is 0.
 * The only node visited is 0, giving a maximal path quality of 0.
 *
 * Constraints:
 *
 * n == values.length
 * 1 <= n <= 1000
 * 0 <= values[i] <= 108
 * 0 <= edges.length <= 2000
 * edges[j].length == 3
 * 0 <= uj < vj <= n - 1
 * 10 <= timej, maxTime <= 100
 * All the pairs [uj, vj] are unique.
 * There are at most four edges connected to each node.
 * The graph may not be connected.
 */
public class MaxPathQualityOfAGraph {

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        int[] minTimeToZero = dijkstra(graph, n);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        return dfs(graph, minTimeToZero, values, 0, maxTime, visited);
    }

    private int dfs(List<int[]>[] map, int[] minTimeToZero, int[] values, int curr, int remainingTime, Set<Integer> visited) {
        int res = 0; // This variable keeps track of the max
        if (curr == 0) {
            int sum = 0;
            for (int i : visited) sum += values[i];
            res = sum;
        }
        for (int[] nei : map[curr]) {
            if (nei[1] + minTimeToZero[nei[0]] <= remainingTime) { // Make sure there is enough time to go back to 0
                boolean added = visited.add(nei[0]);
                res = Math.max(res, dfs(map, minTimeToZero, values, nei[0], remainingTime - nei[1], visited));
                if (added)
                    visited.remove(nei[0]); // If this node was added for the first time in this branch, remove it.
            }
        }
        return res;
    }

    private int[] dijkstra(List<int[]>[] map, int n) { // Run dijkstra to find the min distance between 0 and all the nodes
        int[] minTimeToZero = new int[n];
        Arrays.fill(minTimeToZero, Integer.MAX_VALUE); // At the beginning all nodes are + infinity
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{0, 0});
        minTimeToZero[0] = 0; // There is no cost for node source: 0
        boolean[] visited = new boolean[n];
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            visited[curr[0]] = true;
            for (int[] nei : map[curr[0]]) {
                if (!visited[nei[0]]) { // There is no point in reviewing the visited nodes. They are already optimal
                    if (curr[1] + nei[1] < minTimeToZero[nei[0]]) {
                        minTimeToZero[nei[0]] = curr[1] + nei[1];
                        pq.add(new int[]{nei[0], curr[1] + nei[1]});
                    }
                }
            }
        }
        return minTimeToZero;
    }

}
