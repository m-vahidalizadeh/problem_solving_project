import java.util.*;

public class BLDirectedGraphPaths {

    Map<Integer, Set<Integer>> keyConnectedToValues = new HashMap<>();
    List<List<Integer>> paths = new LinkedList<>();

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

    public static void main(String[] args) {
        int[][] input = {
                {1, 2},
                {3},
                {3},
                {}
        };
        BLDirectedGraphPaths blDirectedGraphPaths = new BLDirectedGraphPaths();
        List<List<Integer>> result = blDirectedGraphPaths.allPathsSourceTarget(input);
        System.out.println();
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (keyConnectedToValues.containsKey(i)) {
                    Set<Integer> tempSet = keyConnectedToValues.get(i);
                    tempSet.add(graph[i][j]);
                } else {
                    Set<Integer> tempSet = new HashSet<>();
                    tempSet.add(graph[i][j]);
                    keyConnectedToValues.put(i, tempSet);
                }
            }
        }
        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(0);
        exploreNodes(currentPath, 0, graph.length - 1);
        return paths;
    }

    public void exploreNodes(List<Integer> currentPath, int currentNode, int target) {
        if (currentNode == target) {
            paths.add(currentPath);
        } else {
            Set<Integer> possibleNodes = keyConnectedToValues.get(currentNode);
            if (possibleNodes == null || possibleNodes.isEmpty()) {
                return;
            }
            for (Integer n : possibleNodes) {
                List<Integer> tempList = new ArrayList<>(currentPath);
                tempList.add(n);
                exploreNodes(tempList, n, target);
            }
        }
    }

}
