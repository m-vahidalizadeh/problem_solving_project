package leetcode.medium;

import java.util.*;

/**
 * Reorder Routes to Make All Paths Lead to the City Zero
 * There are n cities numbered from 0 to n-1 and n-1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
 * <p>
 * Roads are represented by connections where connections[i] = [a, b] represents a road from city a to b.
 * <p>
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 * <p>
 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
 * <p>
 * It's guaranteed that each city can reach the city 0 after reorder.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * Output: 3
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * Example 2:
 * <p>
 * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * Output: 2
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * Example 3:
 * <p>
 * Input: n = 3, connections = [[1,0],[2,0]]
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * 2 <= n <= 5 * 10^4
 * connections.length == n-1
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] <= n-1
 * connections[i][0] != connections[i][1]
 */
public class PathsToCityZero {

    public int minReorder(int n, int[][] connections) {
        Set<Integer> a = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();
        a.add(0);
        for (int[] connection : connections) q.add(connection);
        int res = 0;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int start = curr[1];
            int end = curr[0];
            if (a.contains(start)) {
                a.add(end);
            } else if (a.contains(end)) {
                res++;
                a.add(start);
            } else {
                q.add(curr);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        PathsToCityZero p = new PathsToCityZero();
        int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
        System.out.println(p.minReorder(6, connections));
    }

}
