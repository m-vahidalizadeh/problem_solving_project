package leetcode.hard;

import java.util.*;

/**
 * 1192. Critical Connections in a Network
 * There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 *
 * Example 1:
 *
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 *
 * Constraints:
 *
 * 2 <= n <= 105
 * n - 1 <= connections.length <= 105
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * There are no repeated connections.
 */
public class CriticalConnections {

    Integer[] visitTimes;
    int[] lowestReachableTimes;
    List<Integer>[] graph;
    List<List<Integer>> res;
    int time;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        visitTimes = new Integer[n];
        lowestReachableTimes = new int[n];
        graph = new ArrayList[n];
        res = new ArrayList<>();
        time = 1;
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (List<Integer> connection : connections) {
            int a = connection.get(0);
            int b = connection.get(1);
            graph[a].add(b);
            graph[b].add(a);
        }
        dfs(0, -1);
        return res;
    }

    private void dfs(int curr, int parent) {
        visitTimes[curr] = lowestReachableTimes[curr] = time++;
        for (int child : graph[curr]) {
            if (child == parent) continue;
            if (visitTimes[child] == null) {
                dfs(child, curr);
                lowestReachableTimes[curr] = Math.min(lowestReachableTimes[curr], lowestReachableTimes[child]);
                if (visitTimes[curr] < lowestReachableTimes[child]) res.add(List.of(curr, child));
            } else {
                lowestReachableTimes[curr] = Math.min(lowestReachableTimes[curr], lowestReachableTimes[child]);
            }
        }
    }

}
