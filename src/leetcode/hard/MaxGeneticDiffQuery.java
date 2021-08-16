package leetcode.hard;

import java.util.*;

/**
 * 1938. Maximum Genetic Difference Query
 * There is a rooted tree consisting of n nodes numbered 0 to n - 1. Each node's number denotes its unique genetic value (i.e. the genetic value of node x is x). The genetic difference between two genetic values is defined as the bitwise-XOR of their values. You are given the integer array parents, where parents[i] is the parent for node i. If node x is the root of the tree, then parents[x] == -1.
 *
 * You are also given the array queries where queries[i] = [nodei, vali]. For each query i, find the maximum genetic difference between vali and pi, where pi is the genetic value of any node that is on the path between nodei and the root (including nodei and the root). More formally, you want to maximize vali XOR pi.
 *
 * Return an array ans where ans[i] is the answer to the ith query.
 *
 * Example 1:
 *
 * Input: parents = [-1,0,1,1], queries = [[0,2],[3,2],[2,5]]
 * Output: [2,3,7]
 * Explanation: The queries are processed as follows:
 * - [0,2]: The node with the maximum genetic difference is 0, with a difference of 2 XOR 0 = 2.
 * - [3,2]: The node with the maximum genetic difference is 1, with a difference of 2 XOR 1 = 3.
 * - [2,5]: The node with the maximum genetic difference is 2, with a difference of 5 XOR 2 = 7.
 * Example 2:
 *
 * Input: parents = [3,7,-1,2,0,7,0,2], queries = [[4,6],[1,15],[0,5]]
 * Output: [6,14,7]
 * Explanation: The queries are processed as follows:
 * - [4,6]: The node with the maximum genetic difference is 0, with a difference of 6 XOR 0 = 6.
 * - [1,15]: The node with the maximum genetic difference is 1, with a difference of 15 XOR 1 = 14.
 * - [0,5]: The node with the maximum genetic difference is 2, with a difference of 5 XOR 2 = 7.
 *
 * Constraints:
 *
 * 2 <= parents.length <= 105
 * 0 <= parents[i] <= parents.length - 1 for every node i that is not the root.
 * parents[root] == -1
 * 1 <= queries.length <= 3 * 104
 * 0 <= nodei <= parents.length - 1
 * 0 <= vali <= 2 * 105
 */
public class MaxGeneticDiffQuery {

    Trie root;
    int[] ans;
    Map<Integer, List<Integer>> tree;
    Map<Integer, List<int[]>> nodeToQuery;

    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        root = new Trie();
        ans = new int[queries.length];
        nodeToQuery = new HashMap<>();
        tree = new HashMap<>();
        int rootVal = -1;
        for (int i = 0; i < parents.length; i++) {
            int par = parents[i];
            if (par == -1) rootVal = i;
            tree.computeIfAbsent(par, x -> new ArrayList<>());
            tree.get(par).add(i);
        }
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int u = query[0];
            int v = query[1];
            if (nodeToQuery.containsKey(u)) {
                nodeToQuery.get(u).add(new int[]{i, v});
            } else {
                List<int[]> queryList = new ArrayList<>();
                queryList.add(new int[]{i, v});
                nodeToQuery.put(u, queryList);
            }
        }
        dfs(rootVal);
        return ans;
    }

    private void dfs(int u) {
        update(u, 1);
        if (nodeToQuery.containsKey(u)) {
            for (int[] query : nodeToQuery.get(u)) {
                int index = query[0];
                int val = query[1];
                ans[index] = query(val);
            }
        }
        if (tree.containsKey(u)) {
            for (int v : tree.get(u)) dfs(v);
        }
        update(u, -1);
    }

    private void update(int num, int val) {
        Trie curr = root;
        for (int i = 17; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (curr.children[bit] == null) curr.children[bit] = new Trie();
            curr = curr.children[bit];
            curr.count += val;
        }
    }

    private int query(int num) {
        Trie curr = root;
        int res = 0;
        for (int i = 17; i >= 0; i--) {
            int target = (num >> i) & 1 ^ 1;
            if (curr.children[target] != null && curr.children[target].count > 0) {
                res += (1 << i);
                curr = curr.children[target];
            } else curr = curr.children[target ^ 1];
        }
        return res;
    }

    public class Trie {
        Trie[] children;
        int count;

        public Trie() {
            children = new Trie[2];
            count = 0;
        }
    }

}
