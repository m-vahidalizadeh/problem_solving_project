package leetcode.hard;

import java.util.*;

/**
 * 815. Bus Routes
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
 *
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.
 *
 * Example:
 * Input:
 * routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * Output: 2
 * Explanation:
 * The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 *
 *
 * Constraints:
 *
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 10^5.
 * 0 <= routes[i][j] < 10 ^ 6.
 */
public class NumBussesToDest {

    public int numBusesToDestination(int[][] routes, int S, int T) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int stop = routes[i][j];
                graph.computeIfAbsent(stop, a -> new HashSet<>());
                graph.get(stop).add(i);
            }
        }
        int res = 0;
        Deque<Integer> q = new ArrayDeque<>();
        Set<Integer> visitedStops = new HashSet<>();
        Set<Integer> visitedRoutes = new HashSet<>();
        q.add(S);
        while (!q.isEmpty()) {
            Deque<Integer> next = new ArrayDeque<>();
            while (!q.isEmpty()) {
                int stop = q.poll();
                if (stop == T) return res;
                for (int rootId : graph.get(stop)) {
                    if (visitedRoutes.contains(rootId)) continue;
                    for (int newStop : routes[rootId]) {
                        if (visitedStops.contains(newStop)) continue;
                        next.add(newStop);
                        visitedStops.add(newStop);
                    }
                    visitedRoutes.add(rootId);
                }
            }
            res++;
            q = next;
        }
        return -1;
    }

}
