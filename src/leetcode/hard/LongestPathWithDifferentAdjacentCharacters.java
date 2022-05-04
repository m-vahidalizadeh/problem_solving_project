package leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 2246. Longest Path With Different Adjacent Characters
 * You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
 *
 * You are also given a string s of length n, where s[i] is the character assigned to node i.
 *
 * Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.
 *
 * Example 1:
 *
 * Input: parent = [-1,0,0,1,1,2], s = "abacbe"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is returned.
 * It can be proven that there is no longer path that satisfies the conditions.
 * Example 2:
 *
 * Input: parent = [-1,0,0,0], s = "aabc"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.
 *
 * Constraints:
 *
 * n == parent.length == s.length
 * 1 <= n <= 105
 * 0 <= parent[i] <= n - 1 for all i >= 1
 * parent[0] == -1
 * parent represents a valid tree.
 * s consists of only lowercase English letters.
 */
public class LongestPathWithDifferentAdjacentCharacters {

    int res;

    public int longestPath(int[] parent, String s) {
        res = 0;
        List<Integer>[] children = new ArrayList[parent.length];
        for (int i = 0; i < parent.length; i++) children[i] = new ArrayList<>();
        for (int i = 1; i < parent.length; i++) children[parent[i]].add(i);
        dfs(children, s, 0);
        return res;
    }

    private int dfs(List<Integer>[] children, String s, int i) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int j : children[i]) {
            int cur = dfs(children, s, j);
            if (s.charAt(j) != s.charAt(i)) queue.add(cur);
        }
        int big1 = queue.isEmpty() ? 0 : queue.poll();
        int big2 = queue.isEmpty() ? 0 : queue.poll();
        res = Math.max(res, big1 + big2 + 1);
        return big1 + 1;
    }

}
