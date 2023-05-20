package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 2646. Minimize the Total Price of the Trips
 * There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1. You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 *
 * Each node has an associated price. You are given an integer array price, where price[i] is the price of the ith node.
 *
 * The price sum of a given path is the sum of the prices of all nodes lying on that path.
 *
 * Additionally, you are given a 2D integer array trips, where trips[i] = [starti, endi] indicates that you start the ith trip from the node starti and travel to the node endi by any path you like.
 *
 * Before performing your first trip, you can choose some non-adjacent nodes and halve the prices.
 *
 * Return the minimum total price sum to perform all the given trips.
 *
 * Example 1:
 *
 * Input: n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
 * Output: 23
 * Explanation: The diagram above denotes the tree after rooting it at node 2. The first part shows the initial tree and the second part shows the tree after choosing nodes 0, 2, and 3, and making their price half.
 * For the 1st trip, we choose path [0,1,3]. The price sum of that path is 1 + 2 + 3 = 6.
 * For the 2nd trip, we choose path [2,1]. The price sum of that path is 2 + 5 = 7.
 * For the 3rd trip, we choose path [2,1,3]. The price sum of that path is 5 + 2 + 3 = 10.
 * The total price sum of all trips is 6 + 7 + 10 = 23.
 * It can be proven, that 23 is the minimum answer that we can achieve.
 * Example 2:
 *
 * Input: n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
 * Output: 1
 * Explanation: The diagram above denotes the tree after rooting it at node 0. The first part shows the initial tree and the second part shows the tree after choosing node 0, and making its price half.
 * For the 1st trip, we choose path [0]. The price sum of that path is 1.
 * The total price sum of all trips is 1. It can be proven, that 1 is the minimum answer that we can achieve.
 *
 * Constraints:
 *
 * 1 <= n <= 50
 * edges.length == n - 1
 * 0 <= ai, bi <= n - 1
 * edges represents a valid tree.
 * price.length == n
 * price[i] is an even integer.
 * 1 <= price[i] <= 1000
 * 1 <= trips.length <= 100
 * 0 <= starti, endi <= n - 1
 */
public class MinimizeTheTotalPriceOfTheTrips {

    private ArrayList<Integer>[] tree;
    private int[] price;
    private HashMap<Integer, Integer> pathCount;
    private int n;

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        this.price = price;
        this.n = n;
        this.tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }
        this.pathCount = new HashMap<>();
        for (int[] t : trips) {
            ArrayList<Integer> path = new ArrayList<>();
            dfsForPath(-1, t[0], t[1], path);
            for (int p : path) pathCount.put(p, pathCount.getOrDefault(p, 0) + 1);
        }
        int[] res = dfs(-1, 0);
        return Math.min(res[0], res[1]);
    }

    private int[] dfs(int parent, int node) {
        int[] res = new int[2];
        for (int child : tree[node]) {
            if (child == parent) continue;
            int[] sub = dfs(node, child);
            res[0] += sub[1];
            res[1] += Math.min(sub[0], sub[1]);
        }
        if (pathCount.containsKey(node)) {
            res[0] += pathCount.get(node) * price[node] / 2;
            res[1] += pathCount.get(node) * price[node];
        }
        return res;
    }

    private boolean dfsForPath(int parent, int node, int dest, ArrayList<Integer> path) {
        path.add(node);
        if (dest == node) return true;
        for (int child : tree[node]) {
            if (child == parent) continue;
            if (dfsForPath(node, child, dest, path)) return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

}
