package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 685. Redundant Connection II
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
 *
 * The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a directed edge connecting nodes ui and vi, where ui is a parent of child vi.
 *
 * Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.
 *
 * Example 1:
 *
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 * Example 2:
 *
 * Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
 * Output: [4,1]
 *
 * Constraints:
 *
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * ui != vi
 */
public class RedundantConnectionII {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        Map<Integer, Integer> map = new HashMap<>();
        int cycle[] = null, twoParents[][] = null;
        for (int[] edge : edges) {
            if (!map.containsKey(edge[0])) map.put(edge[0], edge[0]);
            if (!map.containsKey(edge[1]) || map.get(edge[1]) == edge[1]) map.put(edge[1], edge[0]);
            else {
                twoParents = new int[][]{{map.get(edge[1]), edge[1]}, edge};
                if (cycle != null) return twoParents[0];
            }
            if (cycle == null && findRoot(map, edge[0]) == null) {
                cycle = edge;
                if (twoParents != null) return twoParents[0];
            }
        }
        if (cycle != null) return cycle;
        if (twoParents != null) return twoParents[1];
        return new int[2];
    }

    private Integer findRoot(Map<Integer, Integer> map, int node0) {
        if (map.get(node0) == node0) return node0;
        int node = node0;
        while (map.get(node) != node) {
            node = map.get(node);
            if (node == node0) return null;
        }
        return node;
    }

}
