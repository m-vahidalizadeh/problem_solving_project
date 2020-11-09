package leetcode.companies.amazon;

import java.util.*;

/**
 * 1129. Shortest Path with Alternating Colors
 * Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.
 *
 * Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.
 *
 * Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).
 *
 * Example 1:
 *
 * Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
 * Output: [0,1,-1]
 * Example 2:
 *
 * Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
 * Output: [0,1,-1]
 * Example 3:
 *
 * Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
 * Output: [0,-1,-1]
 * Example 4:
 *
 * Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
 * Output: [0,1,2]
 * Example 5:
 *
 * Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
 * Output: [0,1,1]
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * red_edges.length <= 400
 * blue_edges.length <= 400
 * red_edges[i].length == blue_edges[i].length == 2
 * 0 <= red_edges[i][j], blue_edges[i][j] < n
 */
public class AlternatePath {

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        int[][] g = buildGraph(n, red_edges, blue_edges);
        // BFS
        Queue<int[]> q = new LinkedList<>(); // Queue of <node:color>
        q.add(new int[]{0, 1}); // Start from 0 with red
        q.add(new int[]{0, -1}); // Start from 0 with blue
        int l = 0; // Lenght of the path
        Set<String> visited = new HashSet<>();
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[0] = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            l++;
            for (int i = 0; i < size; i++) {
                int[] e = q.poll();
                int node = e[0];
                int color = e[1];
                int oppositeColor = -color;
                for (int j = 1; j < n; j++) {
                    if (g[node][j] == oppositeColor || g[node][j] == 0) {
                        String key = j + "" + oppositeColor;
                        if (visited.contains(key)) continue;
                        visited.add(key);
                        q.add(new int[]{j, oppositeColor});
                        result[j] = Math.min(l, result[j]);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (result[i] == Integer.MAX_VALUE) result[i] = -1;
        }
        return result;
    }

    /**
     * The cells will 1 has a red edge. The cells with -1 has a blue edge. The cells with 0 has both red and blue edges.
     *
     * @param n          The number of nodes
     * @param red_edges  All the red edges
     * @param blue_edges All the blue edges
     * @return The adjacency matrix
     */
    private int[][] buildGraph(int n, int[][] red_edges, int[][] blue_edges) {
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(g[i], -n);
        for (int[] e : red_edges) g[e[0]][e[1]] = 1;
        for (int[] e : blue_edges) {
            if (g[e[0]][e[1]] == 1) g[e[0]][e[1]] = 0;
            else g[e[0]][e[1]] = -1;
        }
        return g;
    }

    public static void main(String[] args) {
        AlternatePath a = new AlternatePath();
        System.out.println(Arrays.toString(a.shortestAlternatingPaths(3, new int[][]{{0, 1}, {1, 2}}, new int[][]{})));
        System.out.println(Arrays.toString(a.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{2, 1}})));
        System.out.println(Arrays.toString(a.shortestAlternatingPaths(3, new int[][]{{1, 0}}, new int[][]{{2, 1}})));
        System.out.println(Arrays.toString(a.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{1, 2}})));
        System.out.println(Arrays.toString(a.shortestAlternatingPaths(3, new int[][]{{0, 1}, {0, 2}}, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(a.shortestAlternatingPaths(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}},
                new int[][]{{1, 2}, {2, 3}, {3, 1}})));
    }

}
