package leetcode.hard;

import java.util.*;

/**
 * 1766. Tree of Coprimes
 * There is a tree (i.e., a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. Each node has a value associated with it, and the root of the tree is node 0.
 *
 * To represent this tree, you are given an integer array nums and a 2D array edges. Each nums[i] represents the ith node's value, and each edges[j] = [uj, vj] represents an edge between nodes uj and vj in the tree.
 *
 * Two values x and y are coprime if gcd(x, y) == 1 where gcd(x, y) is the greatest common divisor of x and y.
 *
 * An ancestor of a node i is any other node on the shortest path from node i to the root. A node is not considered an ancestor of itself.
 *
 * Return an array ans of size n, where ans[i] is the closest ancestor to node i such that nums[i] and nums[ans[i]] are coprime, or -1 if there is no such ancestor.
 *
 * Example 1:
 *
 * Input: nums = [2,3,3,2], edges = [[0,1],[1,2],[1,3]]
 * Output: [-1,0,0,1]
 * Explanation: In the above figure, each node's value is in parentheses.
 * - Node 0 has no coprime ancestors.
 * - Node 1 has only one ancestor, node 0. Their values are coprime (gcd(2,3) == 1).
 * - Node 2 has two ancestors, nodes 1 and 0. Node 1's value is not coprime (gcd(3,3) == 3), but node 0's
 *   value is (gcd(2,3) == 1), so node 0 is the closest valid ancestor.
 * - Node 3 has two ancestors, nodes 1 and 0. It is coprime with node 1 (gcd(3,2) == 1), so node 1 is its
 *   closest valid ancestor.
 * Example 2:
 *
 * Input: nums = [5,6,10,2,3,6,15], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
 * Output: [-1,0,-1,0,0,0,-1]
 *
 * Constraints:
 *
 * nums.length == n
 * 1 <= nums[i] <= 50
 * 1 <= n <= 105
 * edges.length == n - 1
 * edges[j].length == 2
 * 0 <= uj, vj < n
 * uj != vj
 */
public class TreeOfCoPrimes {

    Set<Integer> seen;
    Map<Integer, Map<Integer, int[]>> parents;
    Map<Integer, List<Integer>> graph;
    int[] nums;

    public int[] getCoprimes(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nums = nums;
        parents = new HashMap<>();
        graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>());
            graph.computeIfAbsent(edge[1], x -> new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        seen = new HashSet<>();
        dfs(0, new HashMap<>(), 0);
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int num1 = nums[i];
            List<int[]> parentList = new ArrayList<>(); // depth,key,nodeId
            for (Map.Entry<Integer, int[]> entry : parents.get(i).entrySet()) {
                parentList.add(new int[]{entry.getValue()[0], entry.getKey(), entry.getValue()[1]});
            }
            parentList.sort((a, b) -> b[0] - a[0]);
            int node = -1;
            for (int[] item : parentList) {
                if (gcd(item[1], num1) == 1) {
                    node = item[2];
                    break;
                }
            }
            ans[i] = node;
        }
        return ans;
    }

    private void dfs(int curr, Map<Integer, int[]> pathDict, int depth) {
        parents.put(curr, new HashMap<>(pathDict));
        seen.add(curr);
        Map<Integer, int[]> newPathDict = new HashMap<>(pathDict);
        newPathDict.put(nums[curr], new int[]{depth, curr});
        if (graph.containsKey(curr)) {
            for (int child : graph.get(curr)) {
                if (!seen.contains(child)) {
                    dfs(child, newPathDict, depth + 1);
                }
            }
        }
    }

    private int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
