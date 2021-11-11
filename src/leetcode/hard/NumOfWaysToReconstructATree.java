package leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1719. Number Of Ways To Reconstruct A Tree
 * You are given an array pairs, where pairs[i] = [xi, yi], and:
 *
 * There are no duplicates.
 * xi < yi
 * Let ways be the number of rooted trees that satisfy the following conditions:
 *
 * The tree consists of nodes whose values appeared in pairs.
 * A pair [xi, yi] exists in pairs if and only if xi is an ancestor of yi or yi is an ancestor of xi.
 * Note: the tree does not have to be a binary tree.
 * Two ways are considered to be different if there is at least one node that has different parents in both ways.
 *
 * Return:
 *
 * 0 if ways == 0
 * 1 if ways == 1
 * 2 if ways > 1
 * A rooted tree is a tree that has a single root node, and all edges are oriented to be outgoing from the root.
 *
 * An ancestor of a node is any node on the path from the root to that node (excluding the node itself). The root has no ancestors.
 *
 * Example 1:
 *
 * Input: pairs = [[1,2],[2,3]]
 * Output: 1
 * Explanation: There is exactly one valid rooted tree, which is shown in the above figure.
 * Example 2:
 *
 * Input: pairs = [[1,2],[2,3],[1,3]]
 * Output: 2
 * Explanation: There are multiple valid rooted trees. Three of them are shown in the above figures.
 * Example 3:
 *
 * Input: pairs = [[1,2],[2,3],[2,4],[1,5]]
 * Output: 0
 * Explanation: There are no valid rooted trees.
 *
 * Constraints:
 *
 * 1 <= pairs.length <= 105
 * 1 <= xi < yi <= 500
 * The elements in pairs are unique.
 */
public class NumOfWaysToReconstructATree {

    public int checkWays(int[][] pairs) {
        Map<Integer, Set<Integer>> freq = new HashMap<>();
        for (int[] pair : pairs) {
            freq.computeIfAbsent(pair[0], x -> new HashSet<>()).add(pair[1]);
            freq.computeIfAbsent(pair[1], x -> new HashSet<>()).add(pair[0]);
        }
        return solve(new HashSet<>(), new HashSet<>(freq.keySet()), freq);
    }

    private int solve(Set<Integer> parents, Set<Integer> currNodes, Map<Integer, Set<Integer>> freq) {
        int ans = 1;
        int root = -1;
        for (int curr : currNodes) {
            if (freq.get(curr).size() == parents.size() + currNodes.size() - 1) {
                if (root != -1) ans = 2;
                root = curr;
            }
            if (freq.get(curr).size() >= parents.size() + currNodes.size()) return 0;
        }
        if (root == -1) return 0;
        for (int nei : freq.get(root)) {
            if (!parents.contains(nei) && !currNodes.contains(nei)) return 0;
        }
        parents = new HashSet<>(parents);
        parents.add(root);
        currNodes.remove(root);
        while (!currNodes.isEmpty()) {
            int nextChild = -1;
            int nextChildDegree = 0;
            for (int node : currNodes) {
                if (freq.get(node).size() > nextChildDegree) {
                    nextChild = node;
                    nextChildDegree = freq.get(node).size();
                }
            }
            Set<Integer> nextNodes = new HashSet<>();
            for (int node : currNodes) {
                if (node == nextChild || freq.get(node).contains(nextChild)) nextNodes.add(node);
            }
            for (int node : nextNodes) {
                currNodes.remove(node);
            }
            int nextAns = solve(parents, nextNodes, freq);
            if (nextAns == 0) return 0;
            if (nextAns == 2) ans = 2;
        }
        return ans;
    }

}
