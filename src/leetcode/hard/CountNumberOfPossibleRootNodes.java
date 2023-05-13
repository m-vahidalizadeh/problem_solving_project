package leetcode.hard;

import java.util.*;

/**
 * 2581. Count Number of Possible Root Nodes
 * Alice has an undirected tree with n nodes labeled from 0 to n - 1. The tree is represented as a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 *
 * Alice wants Bob to find the root of the tree. She allows Bob to make several guesses about her tree. In one guess, he does the following:
 *
 * Chooses two distinct integers u and v such that there exists an edge [u, v] in the tree.
 * He tells Alice that u is the parent of v in the tree.
 * Bob's guesses are represented by a 2D integer array guesses where guesses[j] = [uj, vj] indicates Bob guessed uj to be the parent of vj.
 *
 * Alice being lazy, does not reply to each of Bob's guesses, but just says that at least k of his guesses are true.
 *
 * Given the 2D integer arrays edges, guesses and the integer k, return the number of possible nodes that can be the root of Alice's tree. If there is no such tree, return 0.
 *
 * Example 1:
 *
 * Input: edges = [[0,1],[1,2],[1,3],[4,2]], guesses = [[1,3],[0,1],[1,0],[2,4]], k = 3
 * Output: 3
 * Explanation:
 * Root = 0, correct guesses = [1,3], [0,1], [2,4]
 * Root = 1, correct guesses = [1,3], [1,0], [2,4]
 * Root = 2, correct guesses = [1,3], [1,0], [2,4]
 * Root = 3, correct guesses = [1,0], [2,4]
 * Root = 4, correct guesses = [1,3], [1,0]
 * Considering 0, 1, or 2 as root node leads to 3 correct guesses.
 *
 * Example 2:
 *
 * Input: edges = [[0,1],[1,2],[2,3],[3,4]], guesses = [[1,0],[3,4],[2,1],[3,2]], k = 1
 * Output: 5
 * Explanation:
 * Root = 0, correct guesses = [3,4]
 * Root = 1, correct guesses = [1,0], [3,4]
 * Root = 2, correct guesses = [1,0], [2,1], [3,4]
 * Root = 3, correct guesses = [1,0], [2,1], [3,2], [3,4]
 * Root = 4, correct guesses = [1,0], [2,1], [3,2]
 * Considering any node as root will give at least 1 correct guess.
 *
 * Constraints:
 *
 * edges.length == n - 1
 * 2 <= n <= 105
 * 1 <= guesses.length <= 105
 * 0 <= ai, bi, uj, vj <= n - 1
 * ai != bi
 * uj != vj
 * edges represents a valid tree.
 * guesses[j] is an edge of the tree.
 * guesses is unique.
 * 0 <= k <= guesses.length
 */
public class CountNumberOfPossibleRootNodes {

    public int rootCount(int[][] edges, int[][] guesses, int k) {
        int n = edges.length + 1;
        List<Integer>[] graph = new List[n];
        Set<Integer>[] guessGraph = new Set[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            guessGraph[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        for (int[] guess : guesses) guessGraph[guess[0]].add(guess[1]);
        int res = 0;
        Map<Long, Integer> memo = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (dfs(graph, guessGraph, memo, i, -1) >= k) res++;
        }
        return res;
    }

    private int dfs(List<Integer>[] graph, Set<Integer>[] guessGraph, Map<Long, Integer> memo, int current, int prev) {
        long key = (long) current * 100_000 + prev;
        if (memo.containsKey(key)) return memo.get(key);
        int count = prev != -1 && guessGraph[prev].contains(current) ? 1 : 0;
        for (int next : graph[current]) {
            if (next != prev) count += dfs(graph, guessGraph, memo, next, current);
        }
        memo.put(key, count);
        return count;
    }

}
