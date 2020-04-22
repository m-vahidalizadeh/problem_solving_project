package bloomberg;

import java.util.*;

public class BLPathsFrom0ToNMinusOne {

    Map<Integer, Set<Integer>> adjacencyMap = new HashMap<>();
    Set<List<Integer>> paths = new HashSet<>();

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
        BLPathsFrom0ToNMinusOne blPathsFrom0ToNMinusOne = new BLPathsFrom0ToNMinusOne();
        int[][] graph = new int[][]{{0, 1, 1, 0}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 0}};
        List<List<Integer>> result = blPathsFrom0ToNMinusOne.allPathsSourceTarget(graph);
        System.out.println();
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        buildHM(graph);
        int n = graph.length;
        int nMinusOne = n - 1;
        List<Integer> startPath = new LinkedList<>();
        addPaths(0, startPath, nMinusOne);
        List<List<Integer>> result = new LinkedList<>();
        for (List<Integer> path : paths) {
            result.add(path);
        }
        return result;
    }

    public void addPaths(int end, List<Integer> path, int target) {
        if (end == target) {
            List<Integer> tempPath = new ArrayList<>(path);
            tempPath.add(end);
            paths.add(tempPath);
        } else if (adjacencyMap.containsKey(end)) {
            List<Integer> tempPath = new ArrayList<>(path);
            tempPath.add(end);
            Set<Integer> adjacents = adjacencyMap.get(end);
            for (int a : adjacents) {
                addPaths(a, tempPath, target);
            }
        }
    }

    public void buildHM(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                int tempElement = graph[i][j];
                if (i != j) {
                    if (adjacencyMap.containsKey(i)) {
                        Set<Integer> tempSet = adjacencyMap.get(i);
                        tempSet.add(tempElement);
                        adjacencyMap.put(i, tempSet);
                    } else {
                        Set<Integer> tempSet = new HashSet<>();
                        tempSet.add(tempElement);
                        adjacencyMap.put(i, tempSet);
                    }
                }
            }
        }
    }

}
