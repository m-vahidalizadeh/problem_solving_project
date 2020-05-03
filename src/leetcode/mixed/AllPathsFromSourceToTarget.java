package leetcode.mixed;

import java.util.*;

public class AllPathsFromSourceToTarget {

    private Set<List<Integer>> paths = new HashSet<>();
    private Map<Integer, Set<Integer>> nodesToMap;
    private Set<Integer> deadNodes = new HashSet<>();

    public static void main(String[] args) {
/*
Example:
Input: [[1,2], [3], [3], []]
Output: [[0,1,3],[0,2,3]]
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 */
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        AllPathsFromSourceToTarget allPathsFromSourceToTarget = new AllPathsFromSourceToTarget();
        List<List<Integer>> paths = allPathsFromSourceToTarget.allPathsSourceTarget(graph);
        printPaths(paths);
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int nMinusOne = graph.length - 1;
        nodesToMap = getNodesToMap(graph);
        findPaths(new ArrayList<>(), 0, nMinusOne);
        return new ArrayList<>(paths);
    }

    private static void printPaths(List<List<Integer>> paths) {
        for (List<Integer> path : paths) {
            for (Integer e : path) {
                System.out.format("%s ", e);
            }
            System.out.println();
        }
    }

    private void findPaths(List<Integer> path, int currentElement, int target) {
        if (!deadNodes.contains(currentElement)) {
            if (currentElement == target) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(currentElement);
                paths.add(newPath);
            } else {
                Set<Integer> possiblePaths = nodesToMap.get(currentElement);
                if (!possiblePaths.isEmpty()) {
                    for (Integer possiblePath : possiblePaths) {
                        List<Integer> newPath = new ArrayList<>(path);
                        newPath.add(currentElement);
                        findPaths(newPath, possiblePath, target);
                    }
                } else {
                    deadNodes.add(currentElement);
                }
            }
        }
    }

    private Map<Integer, Set<Integer>> getNodesToMap(int[][] graph) {
        Map<Integer, Set<Integer>> nodesToMap = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                int currentElement = graph[i][j];
                if (nodesToMap.containsKey(i)) {
                    Set<Integer> currentSet = nodesToMap.get(i);
                    currentSet.add(currentElement);
                    nodesToMap.put(i, currentSet);
                } else {
                    Set<Integer> nodeTo = new HashSet<>();
                    nodeTo.add(currentElement);
                    nodesToMap.put(i, nodeTo);
                }
            }
        }
        return nodesToMap;
    }

}
