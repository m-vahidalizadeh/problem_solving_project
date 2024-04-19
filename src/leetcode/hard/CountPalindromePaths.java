package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2791. Count Paths That Can Form a Palindrome in a Tree
 * Solved
 * Hard
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
 *
 * You are also given a string s of length n, where s[i] is the character assigned to the edge between i and parent[i]. s[0] can be ignored.
 *
 * Return the number of pairs of nodes (u, v) such that u < v and the characters assigned to edges on the path from u to v can be rearranged to form a palindrome.
 *
 * A string is a palindrome when it reads the same backwards as forwards.
 *
 * Example 1:
 *
 * Input: parent = [-1,0,0,1,1,2], s = "acaabc"
 * Output: 8
 * Explanation: The valid pairs are:
 * - All the pairs (0,1), (0,2), (1,3), (1,4) and (2,5) result in one character which is always a palindrome.
 * - The pair (2,3) result in the string "aca" which is a palindrome.
 * - The pair (1,5) result in the string "cac" which is a palindrome.
 * - The pair (3,5) result in the string "acac" which can be rearranged into the palindrome "acca".
 * Example 2:
 *
 * Input: parent = [-1,0,0,0,0], s = "aaaaa"
 * Output: 10
 * Explanation: Any pair of nodes (u,v) where u < v is valid.
 *
 * Constraints:
 *
 * n == parent.length == s.length
 * 1 <= n <= 10^5
 * 0 <= parent[i] <= n - 1 for all i >= 1
 * parent[0] == -1
 * parent represents a valid tree.
 * s consists of only lowercase English letters.
 */
public class CountPalindromePaths {

    public long countPalindromePaths(List<Integer> parent, String s) {
        int n = s.length();
        List<List<Integer>> con = new ArrayList<>();
        for (int i = 0; i < n; i++) con.add(new ArrayList<>());
        for (int i = 1; i < n; i++) con.get(parent.get(i)).add(i);
        Map<Integer, Integer> have = new HashMap<>();
        have.put(0, 1);
        return dfs(0, 0, s, con, have);
    }

    private long dfs(int x, int mask, String s, List<List<Integer>> con, Map<Integer, Integer> have) {
        long r = 0;
        if (x != 0) {
            mask ^= 1 << (s.charAt(x) - 'a');
            for (int i = (1 << 25); i >= (1 << 0); i >>= 1) r += have.getOrDefault(mask ^ i, 0);
            r += have.getOrDefault(mask, 0);
            have.put(mask, have.getOrDefault(mask, 0) + 1);
        }
        for (int y : con.get(x)) {
            r += dfs(y, mask, s, con, have);
        }
        return r;
    }

}
