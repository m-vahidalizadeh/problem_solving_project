import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BLDirectedGraphPaths {

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
                {0, 1, 1, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
        };
        List<List<Integer>> result = allPathsSourceTarget(input);
    }

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        int finalNode = graph.length - 1;
        Queue<List<Integer>> q = new LinkedList<>();
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> initList = new LinkedList<>();
        initList.add(0);
        q.add(initList);
        while (!q.isEmpty()) {
            List<Integer> currentList = q.remove();
            int lastNode = currentList.get(currentList.size() - 1);
            for (int j = 0; j < n; j++) {
                if (graph[lastNode][j] == 1 && j == finalNode) {
                    List<Integer> tempList = new LinkedList<>(currentList);
                    tempList.add(finalNode);
                    paths.add(tempList);
                } else if (graph[lastNode][j] == 1) {
                    List<Integer> tempList = new LinkedList<>(currentList);
                    tempList.add(j);
                    q.add(tempList);
                }
            }
        }
        return paths;
    }

}
