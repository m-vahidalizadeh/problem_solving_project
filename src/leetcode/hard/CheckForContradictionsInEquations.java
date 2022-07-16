package leetcode.hard;

import javafx.util.Pair;

import java.util.*;

/**
 * 2307. Check for Contradictions in Equations
 * You are given a 2D array of strings equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] means that Ai / Bi = values[i].
 *
 * Determine if there exists a contradiction in the equations. Return true if there is a contradiction, or false otherwise.
 *
 * Note:
 *
 * When checking if two numbers are equal, check that their absolute difference is less than 10-5.
 * The testcases are generated such that there are no cases targeting precision, i.e. using double is enough to solve the problem.
 *
 * Example 1:
 *
 * Input: equations = [["a","b"],["b","c"],["a","c"]], values = [3,0.5,1.5]
 * Output: false
 * Explanation:
 * The given equations are: a / b = 3, b / c = 0.5, a / c = 1.5
 * There are no contradictions in the equations. One possible assignment to satisfy all equations is:
 * a = 3, b = 1 and c = 2.
 * Example 2:
 *
 * Input: equations = [["le","et"],["le","code"],["code","et"]], values = [2,5,0.5]
 * Output: true
 * Explanation:
 * The given equations are: le / et = 2, le / code = 5, code / et = 0.5
 * Based on the first two equations, we get code / et = 0.4.
 * Since the third equation is code / et = 0.5, we get a contradiction.
 *
 * Constraints:
 *
 * 1 <= equations.length <= 100
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * Ai, Bi consist of lowercase English letters.
 * equations.length == values.length
 * 0.0 < values[i] <= 10.0
 * values[i] has a maximum of 2 decimal places.
 */
public class CheckForContradictionsInEquations {

    public boolean checkContradictions(List<List<String>> equations, double[] values) {
        Map<String, Set<String>> graph = new HashMap<>(); // a map from one node to its neighbors
        Map<Pair<String, String>, Double> weights = new HashMap<>(); // a map from an edge to its cost
        for (int i = 0; i < equations.size(); i++) { // Iterate over the equations
            List<String> l = equations.get(i);
            String u = l.get(0);
            String v = l.get(1);
            Double value = dfs(u, v, graph, weights, new HashSet<>(), 1d);
            if (value != null && Math.abs(value - values[i]) > Math.pow(10, -5)) return true; // We found contradiction
            graph.computeIfAbsent(u, x -> new HashSet<>()).add(v); // Update u neighbors
            weights.put(new Pair<>(u, v), values[i]); // cost from u to v is values[i]
            graph.computeIfAbsent(v, x -> new HashSet<>()).add(u); // Update v neighbors
            weights.put(new Pair<>(v, u), 1 / values[i]); // cost from v to u is 1/values[i]
        }
        return false; // We couldn't fina any contradiction
    }

    private Double dfs(String curr, String end, Map<String, Set<String>> graph,
                       Map<Pair<String, String>, Double> weights, Set<String> visited, Double val) {
        if (visited.contains(curr)) return null; // It is already visited
        visited.add(curr); // mark it visited
        if (curr.equals(end)) return val; // if we are in the final node, no more traverse
        for (String next : graph.getOrDefault(curr, new HashSet<>())) { // iterate over curr neighbors
            Double ret = dfs(next, end, graph, weights, visited, val * weights.get(new Pair<>(curr, next))); // for each branch, the weight of the edge will be multiplied to the current val
            if (ret != null) return ret; // if we found the end in this branch, don't continue
        }
        return null; // We couldn't find the end in this branch
    }

}
