package leetcode.hard;

import java.util.*;

/**
 * 1617. Count Subtrees With Max Distance Between Cities
 * There are n cities numbered from 1 to n. You are given an array edges of size n-1, where edges[i] = [ui, vi] represents a bidirectional edge between cities ui and vi. There exists a unique path between each pair of cities. In other words, the cities form a tree.
 *
 * A subtree is a subset of cities where every city is reachable from every other city in the subset, where the path between each pair passes through only the cities from the subset. Two subtrees are different if there is a city in one subtree that is not present in the other.
 *
 * For each d from 1 to n-1, find the number of subtrees in which the maximum distance between any two cities in the subtree is equal to d.
 *
 * Return an array of size n-1 where the dth element (1-indexed) is the number of subtrees in which the maximum distance between any two cities is equal to d.
 *
 * Notice that the distance between the two cities is the number of edges in the path between them.
 *
 * Example 1:
 *
 * Input: n = 4, edges = [[1,2],[2,3],[2,4]]
 * Output: [3,4,0]
 * Explanation:
 * The subtrees with subsets {1,2}, {2,3} and {2,4} have a max distance of 1.
 * The subtrees with subsets {1,2,3}, {1,2,4}, {2,3,4} and {1,2,3,4} have a max distance of 2.
 * No subtree has two nodes where the max distance between them is 3.
 * Example 2:
 *
 * Input: n = 2, edges = [[1,2]]
 * Output: [1]
 * Example 3:
 *
 * Input: n = 3, edges = [[1,2],[2,3]]
 * Output: [2,1]
 *
 * Constraints:
 *
 * 2 <= n <= 15
 * edges.length == n-1
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * All pairs (ui, vi) are distinct.
 */
public class CountSubTrees {

    Map<Integer, List<Integer>> graph;
    int n;

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        graph = new HashMap<>();
        this.n = n;
        for (int i = 0; i <= n - 1; i++) graph.put(i, new ArrayList<>());
        for (int[] edge : edges) {
            int edge0 = edge[0] - 1;
            int edge1 = edge[1] - 1;
            graph.get(edge0).add(edge1);
            graph.get(edge1).add(edge0);
        }
        int[] result = new int[n - 1];
        for (int mask = 0; mask < (1 << n); mask++) {
            int d = getMaxDist(mask);
            if (d > 0) result[d - 1]++;
        }
        return result;
    }

    private int getMaxDist(int mask) {
        if ((mask & (mask - 1)) == 0) return 0;
        int result = 0;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) continue;
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            int d = 0;
            int temp_mask = mask;
            temp_mask = temp_mask ^ (1 << i);
            while (!q.isEmpty()) {
                for (int j = q.size(); j > 0; j--) {
                    int node = q.poll();
                    for (int nei : graph.get(node)) {
                        if ((temp_mask & (1 << nei)) == 0) continue;
                        q.add(nei);
                        temp_mask = temp_mask ^ (1 << nei);
                    }
                }
                d++;
            }
            if (temp_mask > 0) return 0;
            result = Math.max(result, d);
        }
        return result - 1;
    }

}
