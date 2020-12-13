package leetcode.hard;

import java.util.*;

/**
 * 1168. Optimize Water Distribution in a Village
 * There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.
 *
 * For each house i, we can either build a well inside it directly with cost wells[i], or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes, where each pipes[i] = [house1, house2, cost] represents the cost to connect house1 and house2 together using a pipe. Connections are bidirectional.
 *
 * Find the minimum total cost to supply water to all houses.
 *
 * Example 1:
 *
 * Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
 * Output: 3
 * Explanation:
 * The image shows the costs of connecting houses using pipes.
 * The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.
 *
 * Constraints:
 *
 * 1 <= n <= 10000
 * wells.length == n
 * 0 <= wells[i] <= 10^5
 * 1 <= pipes.length <= 10000
 * 1 <= pipes[i][0], pipes[i][1] <= n
 * 0 <= pipes[i][2] <= 10^5
 * pipes[i][0] != pipes[i][1]
 */
public class WateringVillage {

    public class DSU {
        int[] size;
        int[] root;

        public DSU(int n) {
            size = new int[n + 1];
            root = new int[n + 1];
            Arrays.fill(size, 1);
            for (int i = 1; i <= n; i++) root[i] = i;
        }

        public int findRoot(int x) {
            while (x != root[x]) x = root[x];
            return x;
        }

        public boolean union(int x, int y) {
            int rootX = findRoot(x);
            int rootY = findRoot(y);
            if (rootX == rootY) return false;
            if (size[rootX] < size[rootY]) {
                size[rootY] += size[rootX];
                root[rootX] = rootY;
            } else {
                size[rootX] += size[rootY];
                root[rootY] = rootX;
            }
            return true;
        }
    }

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        DSU dsu = new DSU(n);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < n; i++) minHeap.add(new int[]{0, i + 1, wells[i]});
        for (int[] pipe : pipes) minHeap.add(pipe);
        int totalCost = 0;
        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            if (dsu.union(curr[0], curr[1])) totalCost += curr[2];
        }
        return totalCost;
    }

    public static void main(String[] args) {
        WateringVillage w = new WateringVillage();
        System.out.println(w.minCostToSupplyWater(3, new int[]{1, 2, 2}, new int[][]{{1, 2, 1}, {2, 3, 1}}));
    }

}
