package leetcode.companies.google;

import java.util.*;

/**
 * All Paths from Source Lead to Destination
 * Given the edges of a directed graph where edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi, and two nodes source and destination of this graph, determine whether or not all paths starting from source eventually, end at destination, that is:
 * <p>
 * At least one path exists from the source node to the destination node
 * If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
 * The number of possible paths from source to destination is a finite number.
 * Return true if and only if all roads from source lead to destination.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
 * Output: false
 * Explanation: It is possible to reach and get stuck on both node 1 and node 2.
 * Example 2:
 * <p>
 * Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
 * Output: false
 * Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
 * Example 3:
 * <p>
 * Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
 * Output: true
 * Example 4:
 * <p>
 * Input: n = 3, edges = [[0,1],[1,1],[1,2]], source = 0, destination = 2
 * Output: false
 * Explanation: All paths from the source node end at the destination node, but there are an infinite number of paths, such as 0-1-2, 0-1-1-2, 0-1-1-1-2, 0-1-1-1-1-2, and so on.
 * Example 5:
 * <p>
 * <p>
 * Input: n = 2, edges = [[0,1],[1,1]], source = 0, destination = 1
 * Output: false
 * Explanation: There is infinite self-loop at destination node.
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 104
 * 0 <= edges.length <= 104
 * edges.length == 2
 * 0 <= ai, bi <= n - 1
 * 0 <= source <= n - 1
 * 0 <= destination <= n - 1
 * The given graph may have self-loops and parallel edges.
 */
public class LeadsToDest {

    Map<Integer, Set<Integer>> edgeMap;

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        edgeMap = new HashMap<>();
        for (int i = 0; i < n; i++) edgeMap.put(i, new HashSet<>());
        for (int[] edge : edges) {
            Set<Integer> existingSet = edgeMap.get(edge[0]);
            existingSet.add(edge[1]);
            edgeMap.put(edge[0], existingSet);
        }
        return dfs(source, destination, new HashSet<>());
    }

    private boolean dfs(int curr, int destination, Set<Integer> visited) {
        if (visited.contains(curr)) return false;
        Set<Integer> children = edgeMap.get(curr);
        if (children.contains(curr)) return false;
        if (curr == destination && children.isEmpty()) return true;
        if (children.isEmpty()) return false;
        visited.add(curr);
        boolean res = true;
        for (int c : children) {
            res &= dfs(c, destination, visited);
        }
        visited.remove(curr);
        return res;
    }

    public static void main(String[] args) {
        LeadsToDest l = new LeadsToDest();
        System.out.println(l.leadsToDestination(3, new int[][]{{0, 1}, {0, 2}}, 0, 2));
        System.out.println(l.leadsToDestination(4, new int[][]{{0, 1}, {0, 3}, {1, 2}, {2, 1}}, 0, 3));
        System.out.println(l.leadsToDestination(4, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}}, 0, 3));
        System.out.println(l.leadsToDestination(3, new int[][]{{0, 1}, {1, 1}, {1, 2}}, 0, 2));
        System.out.println(l.leadsToDestination(2, new int[][]{{0, 1}, {1, 1}}, 0, 1));
    }

}
