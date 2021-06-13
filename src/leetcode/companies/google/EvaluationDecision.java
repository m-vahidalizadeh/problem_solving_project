package leetcode.companies.google;

import java.util.*;

/**
 * 399. Evaluate Division
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 *
 * Example 1:
 *
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * Example 2:
 *
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * Example 3:
 *
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 * Constraints:
 *
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class EvaluationDecision {

    public class Node {
        String label;
        Map<String, Double> children;

        public Node(String label) {
            this.label = label;
            this.children = new HashMap<>();
        }
    }

    Map<String, Node> graph;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        graph = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            if (!graph.containsKey(a)) {
                Node node = new Node(a);
                graph.put(a, node);
            }
            if (!graph.containsKey(b)) {
                Node node = new Node(b);
                graph.put(b, node);
            }
            graph.get(a).children.put(b, values[i]);
            graph.get(b).children.put(a, 1 / values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            Double ans = bfs(queries.get(i).get(0), queries.get(i).get(1));
            res[i] = ans == null ? -1.0 : ans;
        }
        return res;
    }

    private Double bfs(String a, String b) {
        if (!graph.containsKey(a) || !graph.containsKey(b)) return null;
        if (a.equals(b)) return 1.0;
        Queue<String> qNode = new ArrayDeque<>();
        Queue<Double> qVal = new ArrayDeque<>();
        qNode.add(a);
        qVal.add(1.0);
        Set<String> visited = new HashSet<>();
        visited.add(a);
        while (!qNode.isEmpty()) {
            String node = qNode.poll();
            double val = qVal.poll();
            if (node.equals(b)) return val;
            if (!graph.get(node).children.isEmpty()) {
                for (Map.Entry<String, Double> e : graph.get(node).children.entrySet()) {
                    String nei = e.getKey();
                    double neiVal = e.getValue();
                    if (!visited.contains(nei)) {
                        visited.add(nei);
                        qNode.add(nei);
                        qVal.add(val * neiVal);
                    }
                }
            }
        }
        return null;
    }

}
