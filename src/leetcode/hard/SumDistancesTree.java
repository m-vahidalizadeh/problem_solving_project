package leetcode.hard;

import java.util.*;

/**
 * 834. Sum of Distances in Tree
 * An undirected, connected tree with n nodes labelled 0...n-1 and n-1 edges are given.
 *
 * The ith edge connects nodes edges[i][0] and edges[i][1] together.
 *
 * Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.
 *
 * Example 1:
 *
 * Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation:
 * Here is a diagram of the given tree:
 *   0
 *  / \
 * 1   2
 *    /|\
 *   3 4 5
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
 * Note: 1 <= n <= 10000
 */
public class SumDistancesTree {

    List<Integer>[] graph;
    boolean[] seen;
    int[] closerNodes;
    int[] sums;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        graph = new List[n];
        closerNodes = new int[n];
        sums = new int[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        seen = new boolean[n];
        seen[0] = true;
        countSubNodesDfs(0); // Find summation for root 0 and find sum for node 0
        seen = new boolean[n];
        seen[0] = true;
        findSumsDfs(0, n); // Find summations for nodes 1 to n-1
        return sums;
    }

    private int countSubNodesDfs(int root) {
        int SubCount = 1;
        for (int child : graph[root]) {
            if (!seen[child]) {
                seen[child] = true;
                int childNodesCount = countSubNodesDfs(child);
                SubCount += childNodesCount;
                sums[0] += childNodesCount;
            }
        }
        closerNodes[root] = SubCount;
        return SubCount;
    }

    private void findSumsDfs(int root, int n) {
        for (int child : graph[root]) {
            if (!seen[child]) {
                seen[child] = true;
                sums[child] = sums[root] - closerNodes[child] + (n - closerNodes[child]);
                findSumsDfs(child, n);
            }
        }
    }

}
