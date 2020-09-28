package leetcode.companies.google;

import java.util.*;

/**
 * Evaluate Division
 * You are given equations in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating-point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 * <p>
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 * <p>
 * Example 1:
 * <p>
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * Example 2:
 * <p>
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * Example 3:
 * <p>
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 * <p>
 * Constraints:
 * <p>
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= equations[i][0], equations[i][1] <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= queries[i][0], queries[i][1] <= 5
 * equations[i][0], equations[i][1], queries[i][0], queries[i][1] consist of lower case English letters and digits.
 */
public class CalculateEquation {

    public class GNode {
        String label;
        Map<String, Double> edges;
        Map<String, GNode> children;

        public GNode(String label) {
            this.label = label;
            edges = new HashMap<>();
            children = new HashMap<>();
        }
    }

    Map<String, GNode> nodes;
    Double cost;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        nodes = new HashMap<>();
        int n = values.length;
        for (int i = 0; i < n; i++) {
            List<String> equation = equations.get(i);
            GNode sNode = getNode(equation.get(0), true);
            GNode tNode = getNode(equation.get(1), true);
            nodes.put(sNode.label, sNode);
            nodes.put(tNode.label, tNode);
            sNode.edges.put(tNode.label, values[i]);
            sNode.children.put(tNode.label, tNode);
            tNode.edges.put(sNode.label, 1 / values[i]);
            tNode.children.put(sNode.label, sNode);
        }
        int qN = queries.size();
        double[] result = new double[qN];
        int i = 0;
        for (List<String> query : queries) {
            cost = null;
            dfs(query.get(0), query.get(1), 1, new HashSet<>());
            result[i++] = cost == null ? -1.0 : cost;
        }
        return result;
    }

    private void dfs(String source, String target, double currCost, Set<String> visited) {
        if (visited.contains(source)) return;
        GNode sNode = getNode(source, false);
        if (sNode == null) return;
        if (sNode.label.equals(target)) {
            cost = currCost;
        } else {
            visited.add(source);
            for (GNode child : sNode.children.values()) {
                dfs(child.label, target, currCost * sNode.edges.get(child.label), visited);
            }
            visited.remove(source);
        }
    }

    private GNode getNode(String input, boolean canCreate) {
        if (nodes.containsKey(input)) return nodes.get(input);
        if (!canCreate) return null;
        GNode newNode = new GNode(input);
        nodes.put(input, newNode);
        return newNode;
    }

    public static void main(String[] args) {
        CalculateEquation c = new CalculateEquation();
        System.out.println(Arrays.toString(
                c.calcEquation(List.of(List.of("a", "b"), List.of("b", "c"))
                        ,
                        new double[]{2.0, 3.0}
                        ,
                        List.of(List.of("a", "c"), List.of("b", "a"), List.of("a", "e"), List.of("a", "a"), List.of("x", "x")))
        ));
        System.out.println(Arrays.toString(
                c.calcEquation(List.of(List.of("a", "b"), List.of("b", "c"), List.of("bc", "cd"))
                        ,
                        new double[]{1.5, 2.5, 5.0}
                        ,
                        List.of(List.of("a", "c"), List.of("c", "b"), List.of("bc", "cd"), List.of("cd", "bc")))
        ));
        System.out.println(Arrays.toString(
                c.calcEquation(List.of(List.of("a", "b"))
                        ,
                        new double[]{0.5}
                        ,
                        List.of(List.of("a", "b"), List.of("b", "a"), List.of("a", "c"), List.of("x", "y"))
                )));
    }

}
