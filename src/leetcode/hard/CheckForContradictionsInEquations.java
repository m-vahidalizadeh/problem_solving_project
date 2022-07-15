package leetcode.hard;

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
        Map<String, Set<String>> map = new HashMap<>();
        Map<Pair, Double> weights = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> l = equations.get(i);
            String first = l.get(0);
            String second = l.get(1);
            Double value = dfs(first, second, map, weights, new HashSet<>(), 1d);
            if (value != null) {
                if (!dEquals(value, values[i])) return true;
            }
            map.putIfAbsent(first, new HashSet<>());
            map.get(first).add(second);
            map.putIfAbsent(second, new HashSet<>());
            map.get(second).add(first);
            weights.put(new Pair(second, first), 1 / values[i]);
            weights.put(new Pair(first, second), values[i]);
        }
        return false;
    }

    private Double dfs(String curr, String end, Map<String, Set<String>> map,
                       Map<Pair, Double> weights, Set<String> visited, Double val) {
        if (visited.contains(curr)) return null;
        visited.add(curr);
        if (curr.equals(end)) return val;
        for (String next : map.getOrDefault(curr, new HashSet<>())) {
            Double ret = dfs(next, end, map, weights, visited, val * weights.get(new Pair(curr, next)));
            if (ret != null) return ret;
        }
        return null;
    }

    private boolean dEquals(Double a, Double b) {
        return Math.abs(a - b) <= Math.pow(10, -5);
    }

    public class Pair {
        String first;
        String second;

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        @Override
        public boolean equals(Object o) {
            Pair p = (Pair) o;
            return p.first.equals(this.first) && p.second.equals(this.second);
        }

        @Override
        public String toString() {
            return first + " " + second;
        }
    }

}
