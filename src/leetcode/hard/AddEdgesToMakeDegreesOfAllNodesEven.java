package leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2508. Add Edges to Make Degrees of All Nodes Even
 * There is an undirected graph consisting of n nodes numbered from 1 to n. You are given the integer n and a 2D array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi. The graph can be disconnected.
 *
 * You can add at most two additional edges (possibly none) to this graph so that there are no repeated edges and no self-loops.
 *
 * Return true if it is possible to make the degree of each node in the graph even, otherwise return false.
 *
 * The degree of a node is the number of edges connected to it.
 *
 * Example 1:
 *
 * Input: n = 5, edges = [[1,2],[2,3],[3,4],[4,2],[1,4],[2,5]]
 * Output: true
 * Explanation: The above diagram shows a valid way of adding an edge.
 * Every node in the resulting graph is connected to an even number of edges.
 * Example 2:
 *
 * Input: n = 4, edges = [[1,2],[3,4]]
 * Output: true
 * Explanation: The above diagram shows a valid way of adding two edges.
 * Example 3:
 *
 * Input: n = 4, edges = [[1,2],[1,3],[1,4]]
 * Output: false
 * Explanation: It is not possible to obtain a valid graph with adding at most 2 edges.
 *
 * Constraints:
 *
 * 3 <= n <= 105
 * 2 <= edges.length <= 105
 * edges[i].length == 2
 * 1 <= ai, bi <= n
 * ai != bi
 * There are no repeated edges.
 */
public class AddEdgesToMakeDegreesOfAllNodesEven {

    public boolean isPossible(int n, List<List<Integer>> edges) {
        List<Set<Integer>> l = new ArrayList<>();
        int[] degree = new int[n + 1];
        for (int i = 0; i < n + 1; i++) l.add(new HashSet<>());
        for (List<Integer> x : edges) {
            l.get(x.get(0)).add(x.get(1));
            l.get(x.get(1)).add(x.get(0));
            degree[x.get(0)]++;
            degree[x.get(1)]++;
        }
        List<Integer> odd = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            if (degree[i] % 2 == 1) odd.add(i);
        }
        if (odd.size() == 0) return true;
        if (odd.size() == 2) {
            Set<Integer> s1 = l.get(odd.get(0));
            Set<Integer> s2 = l.get(odd.get(1));
            if (!s1.contains(odd.get(1))) return true;
            else {
                for (int i = 1; i < n + 1; i++) {
                    if (!s1.contains(i) && !s2.contains(i)) return true;
                }
            }
        }
        if (odd.size() == 4) {
            Set<Integer> s1 = l.get(odd.get(0));
            Set<Integer> s2 = l.get(odd.get(1));
            Set<Integer> s3 = l.get(odd.get(2));
            Set<Integer> s4 = l.get(odd.get(3));
            // !(1,2) && !(3,4)
            if (!s1.contains(odd.get(1)) && !s2.contains(odd.get(0)) && !s3.contains(odd.get(3)) && !s4.contains(odd.get(2)))
                return true;
            // !(1,3) && !(2,4)
            if (!s1.contains(odd.get(2)) && !s3.contains(odd.get(0)) && !s2.contains(odd.get(3)) && !s4.contains(odd.get(1)))
                return true;
            // !(1,4) && !(2,3)
            if (!s1.contains(odd.get(3)) && !s4.contains(odd.get(0)) && !s3.contains(odd.get(1)) && !s2.contains(odd.get(2)))
                return true;
        }
        return false;
    }

}
