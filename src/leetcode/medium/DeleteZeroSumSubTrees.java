package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Delete Tree Nodes
 * A tree rooted at node 0 is given as follows:
 * <p>
 * The number of nodes is nodes;
 * The value of the i-th node is value[i];
 * The parent of the i-th node is parent[i].
 * Remove every subtree whose sum of values of nodes is zero.
 * <p>
 * After doing so, return the number of nodes remaining in the tree.
 * <p>
 * Example 1:
 * <p>
 * Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
 * Output: 2
 * <p>
 * Constraints:
 * <p>
 * 1 <= nodes <= 10^4
 * -10^5 <= value[i] <= 10^5
 * parent.length == nodes
 * parent[0] == -1 which indicates that 0 is the root.
 */
public class DeleteZeroSumSubTrees {

    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        Map<Integer, List<Integer>> childrenMap = new HashMap<>();
        for (int i = nodes - 1; i > 0; i--) {
            value[parent[i]] += value[i];
            if (childrenMap.containsKey(parent[i])) {
                childrenMap.get(parent[i]).add(i);
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(i);
                childrenMap.put(parent[i], newList);
            }
        }
        for (int i = 0; i < nodes; i++) {
            if (value[i] == 0 && childrenMap.containsKey(i)) {
                for (int c : childrenMap.get(i)) {
                    value[c] = 0;
                }
            }
        }
        int counter = 0;
        for (int i = 0; i < nodes; i++) if (value[i] != 0) counter++;
        return counter;
    }

    public static void main(String[] args) {
        DeleteZeroSumSubTrees d = new DeleteZeroSumSubTrees();
        int nodes = 7;
        int[] parent = {-1, 0, 0, 1, 2, 2, 2};
        int[] value = {1, -2, 4, 0, -2, -1, -1};
        System.out.println(d.deleteTreeNodes(nodes, parent, value));
    }

}
