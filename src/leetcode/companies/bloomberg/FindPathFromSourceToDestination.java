package leetcode.companies.bloomberg;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * All Paths From Source to Target
 * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
 * <p>
 * The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.
 * <p>
 * Example:
 * Input: [[1,2], [3], [3], []]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: The graph looks like this:
 * 0--->1
 * |    |
 * v    v
 * 2--->3
 * There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * Note:
 * <p>
 * The number of nodes in the graph will be in the range [2, 15].
 * You can print different paths in any order, but you should keep the order of nodes inside one path.
 */
public class FindPathFromSourceToDestination {

    public class Result {
        List<List<Integer>> paths;

        public Result() {
            paths = new LinkedList<>();
        }
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length - 1;
        Result result = new Result();
        findPaths(graph, 0, n, result, new LinkedList<>(), new HashSet<>());
        return result.paths;
    }

    private void findPaths(int[][] graph, int source, int destination, Result result, List<Integer> path, Set<Integer> visited) {
        if (source == destination) {
            path.add(source);
            result.paths.add(path);
        }
        for (int j = 0; j < graph[source].length; j++) {
            if (!visited.contains(graph[source][j])) {
                visited.add(source);
                List<Integer> newList = new LinkedList<>(path);
                newList.add(source);
                Set<Integer> newVisited = new HashSet<>(visited);
                newVisited.add(source);
                findPaths(graph, graph[source][j], destination, result, newList, newVisited);
            }
        }
    }

    public static void main(String[] args) {
        FindPathFromSourceToDestination f = new FindPathFromSourceToDestination();
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        List<List<Integer>> paths = f.allPathsSourceTarget(graph);
        for (List<Integer> path : paths) {
            for (Integer e : path) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

}
