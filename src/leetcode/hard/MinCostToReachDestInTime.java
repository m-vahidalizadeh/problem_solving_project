package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1928. Minimum Cost to Reach Destination in Time
 * There is a country of n cities numbered from 0 to n - 1 where all the cities are connected by bi-directional roads. The roads are represented as a 2D integer array edges where edges[i] = [xi, yi, timei] denotes a road between cities xi and yi that takes timei minutes to travel. There may be multiple roads of differing travel times connecting the same two cities, but no road connects a city to itself.
 *
 * Each time you pass through a city, you must pay a passing fee. This is represented as a 0-indexed integer array passingFees of length n where passingFees[j] is the amount of dollars you must pay when you pass through city j.
 *
 * In the beginning, you are at city 0 and want to reach city n - 1 in maxTime minutes or less. The cost of your journey is the summation of passing fees for each city that you passed through at some moment of your journey (including the source and destination cities).
 *
 * Given maxTime, edges, and passingFees, return the minimum cost to complete your journey, or -1 if you cannot complete it within maxTime minutes.
 *
 * Example 1:
 *
 * Input: maxTime = 30, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * Output: 11
 * Explanation: The path to take is 0 -> 1 -> 2 -> 5, which takes 30 minutes and has $11 worth of passing fees.
 * Example 2:
 *
 * Input: maxTime = 29, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * Output: 48
 * Explanation: The path to take is 0 -> 3 -> 4 -> 5, which takes 26 minutes and has $48 worth of passing fees.
 * You cannot take path 0 -> 1 -> 2 -> 5 since it would take too long.
 * Example 3:
 *
 * Input: maxTime = 25, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * Output: -1
 * Explanation: There is no way to reach city 5 from city 0 within 25 minutes.
 *
 * Constraints:
 *
 * 1 <= maxTime <= 1000
 * n == passingFees.length
 * 2 <= n <= 1000
 * n - 1 <= edges.length <= 1000
 * 0 <= xi, yi <= n - 1
 * 1 <= timei <= 1000
 * 1 <= passingFees[j] <= 1000
 * The graph may contain multiple edges between two nodes.
 * The graph does not contain self loops.
 */
public class MinCostToReachDestInTime {

    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        ArrayList<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<int[]>(); // node,time
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int t = edges[i][2];
            graph[u].add(new int[]{v, t});
            graph[v].add(new int[]{u, t});
        }
        int[] fees = new int[n];
        Arrays.fill(fees, Integer.MAX_VALUE);
        int[] times = new int[n];
        Arrays.fill(times, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]); // fee,time,node
        pq.add(new int[]{passingFees[0], 0, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int fee = curr[0];
            int time = curr[1];
            int u = curr[2];
            for (int[] nei : graph[u]) {
                int v = nei[0];
                int t = nei[1];
                if ((time + t <= maxTime) && (fee + passingFees[v] < fees[v] || time + t < times[v])) {
                    pq.add(new int[]{fee + passingFees[v], time + t, v});
                    if (fee + passingFees[v] < fees[v]) fees[v] = fee + passingFees[v];
                    if (time + t < times[v]) times[v] = time + t;
                }
            }
        }
        return fees[n - 1] < Integer.MAX_VALUE ? fees[n - 1] : -1;
    }

}
