package leetcode.hard;

import java.util.*;

/**
 * 1548. The Most Similar Path in a Graph
 * We have n cities and m bi-directional roads where roads[i] = [ai, bi] connects city ai with city bi. Each city has a name consisting of exactly 3 upper-case English letters given in the string array names. Starting at any city x, you can reach any city y where y != x (i.e. the cities and the roads are forming an undirected connected graph).
 *
 * You will be given a string array targetPath. You should find a path in the graph of the same length and with the minimum edit distance to targetPath.
 *
 * You need to return the order of the nodes in the path with the minimum edit distance, The path should be of the same length of targetPath and should be valid (i.e. there should be a direct road between ans[i] and ans[i + 1]). If there are multiple answers return any one of them.
 *
 * The edit distance is defined as follows:
 *
 * Follow-up: If each node can be visited only once in the path, What should you change in your solution?
 *
 * Example 1:
 *
 * Input: n = 5, roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]], names = ["ATL","PEK","LAX","DXB","HND"], targetPath = ["ATL","DXB","HND","LAX"]
 * Output: [0,2,4,2]
 * Explanation: [0,2,4,2], [0,3,0,2] and [0,3,1,2] are accepted answers.
 * [0,2,4,2] is equivalent to ["ATL","LAX","HND","LAX"] which has edit distance = 1 with targetPath.
 * [0,3,0,2] is equivalent to ["ATL","DXB","ATL","LAX"] which has edit distance = 1 with targetPath.
 * [0,3,1,2] is equivalent to ["ATL","DXB","PEK","LAX"] which has edit distance = 1 with targetPath.
 * Example 2:
 *
 * Input: n = 4, roads = [[1,0],[2,0],[3,0],[2,1],[3,1],[3,2]], names = ["ATL","PEK","LAX","DXB"], targetPath = ["ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX"]
 * Output: [0,1,0,1,0,1,0,1]
 * Explanation: Any path in this graph has edit distance = 8 with targetPath.
 * Example 3:
 *
 * Input: n = 6, roads = [[0,1],[1,2],[2,3],[3,4],[4,5]], names = ["ATL","PEK","LAX","ATL","DXB","HND"], targetPath = ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
 * Output: [3,4,5,4,3,2,1]
 * Explanation: [3,4,5,4,3,2,1] is the only path with edit distance = 0 with targetPath.
 * It's equivalent to ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * m == roads.length
 * n - 1 <= m <= (n * (n - 1) / 2)
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * The graph is guaranteed to be connected and each pair of nodes may have at most one direct road.
 * names.length == n
 * names[i].length == 3
 * names[i] consists of upper-case English letters.
 * There can be two cities with the same name.
 * 1 <= targetPath.length <= 100
 * targetPath[i].length == 3
 * targetPath[i] consists of upper-case English letters.
 */
public class MostSimilarPath {

    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        List<Integer>[] graph = new ArrayList[n];
        for (int[] road : roads) {
            if (graph[road[0]] == null) graph[road[0]] = new ArrayList<>();
            if (graph[road[1]] == null) graph[road[1]] = new ArrayList<>();
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }
        int m = targetPath.length;
        int[][] path = new int[n][m];
        int[][] dist = new int[n][m];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                dist[a[0]][a[1]] == dist[b[0]][b[1]] ? b[1] - a[1] : dist[a[0]][a[1]] - dist[b[0]][b[1]]);
        for (int i = 0; i < n; i++) {
            dist[i][0] = targetPath[0].equals(names[i]) ? 0 : 1;
            pq.add(new int[]{i, 0});
            for (int j = 1; j < m; j++) dist[i][j] = Integer.MAX_VALUE;
        }
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int c = curr[0];
            int p = curr[1];
            int d = dist[c][p];
            if (p == m - 1) break;
            for (int b : graph[c]) {
                int dd = d + (targetPath[p + 1].equals(names[b]) ? 0 : 1);
                if (dd < dist[b][p + 1]) {
                    dist[b][p + 1] = dd;
                    pq.add(new int[]{b, p + 1});
                    path[b][p + 1] = c;
                }
            }
        }
        int last = 0;
        for (int i = 1; i < n; i++) {
            if (dist[i][m - 1] < dist[last][m - 1]) last = i;
        }
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = m - 1; i >= 0; i--) {
            res.addFirst(last);
            last = path[last][i];
        }
        return res;
    }

}
