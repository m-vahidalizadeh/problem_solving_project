package leetcode.hard;

import java.util.*;

/**
 * 2097. Valid Arrangement of Pairs
 * You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, we have endi-1 == starti.
 *
 * Return any valid arrangement of pairs.
 *
 * Note: The inputs will be generated such that there exists a valid arrangement of pairs.
 *
 * Example 1:
 *
 * Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
 * Output: [[11,9],[9,4],[4,5],[5,1]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 9 == 9 = start1
 * end1 = 4 == 4 = start2
 * end2 = 5 == 5 = start3
 * Example 2:
 *
 * Input: pairs = [[1,3],[3,2],[2,1]]
 * Output: [[1,3],[3,2],[2,1]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 3 == 3 = start1
 * end1 = 2 == 2 = start2
 * The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.
 * Example 3:
 *
 * Input: pairs = [[1,2],[1,3],[2,1]]
 * Output: [[1,2],[2,1],[1,3]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 2 == 2 = start1
 * end1 = 1 == 1 = start2
 *
 * Constraints:
 *
 * 1 <= pairs.length <= 105
 * pairs[i].length == 2
 * 0 <= starti, endi <= 109
 * starti != endi
 * No two pairs are exactly the same.
 * There exists a valid arrangement of pairs.
 */
public class ValidArrangementOfPairs {

    public int[][] validArrangement(int[][] pairs) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        Map<Integer, LinkedList<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int[] pair : pairs) {
            adj.computeIfAbsent(pair[0], x -> new LinkedList<>()).add(pair[1]);
            inDegree.put(pair[0], inDegree.getOrDefault(pair[0], 0) + 1);
            inDegree.put(pair[1], inDegree.getOrDefault(pair[1], 0) - 1);
        }
        int start = pairs[0][0];
        for (int key : inDegree.keySet()) {
            if (inDegree.get(key) > 0) {
                start = key;
                break;
            }
        }
        dfs(start, adj, ans);
        int[][] res = new int[pairs.length][pairs[0].length];
        for (int i = 0; i < res.length; i++) {
            List<Integer> point = ans.removeFirst(); // Points are saved in order
            res[i][0] = point.get(0);
            res[i][1] = point.get(1);
        }
        return res;
    }

    private void dfs(int node, Map<Integer, LinkedList<Integer>> adj, LinkedList<List<Integer>> ans) {
        while (adj.containsKey(node) && !adj.get(node).isEmpty()) {
            int nei = adj.get(node).removeFirst();
            dfs(nei, adj, ans);
            ans.addFirst(List.of(node, nei)); // Because of DFS add to the beginning
        }
    }

}
