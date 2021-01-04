package leetcode.hard;

import java.util.*;

/**
 * 1203. Sort Items by Groups Respecting Dependencies
 * There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to and it's equal to -1 if the i-th item belongs to no group. The items and the groups are zero indexed. A group can have no item belonging to it.
 *
 * Return a sorted list of the items such that:
 *
 * The items that belong to the same group are next to each other in the sorted list.
 * There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in the sorted array (to the left of the i-th item).
 * Return any solution if there is more than one solution and return an empty list if there is no solution.
 *
 * Example 1:
 *
 * Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
 * Output: [6,3,4,1,5,2,0,7]
 * Example 2:
 *
 * Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
 * Output: []
 * Explanation: This is the same as example 1 except that 4 needs to be before 6 in the sorted list.
 *
 * Constraints:
 *
 * 1 <= m <= n <= 3*10^4
 * group.length == beforeItems.length == n
 * -1 <= group[i] <= m-1
 * 0 <= beforeItems[i].length <= n-1
 * 0 <= beforeItems[i][j] <= n-1
 * i != beforeItems[i][j]
 * beforeItems[i] does not contain duplicates elements.
 */
public class TopologicalSortByGroups {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        int[] inDegreeItems = new int[n];
        Map<Integer, List<Integer>> groups = new HashMap<>();
        Map<Integer, Integer> inDegreeGroup = new HashMap<>();
        Map<Integer, List<Integer>> dep = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int grp = group[i];
            groups.putIfAbsent(grp, new ArrayList<>());
            List<Integer> list = groups.get(grp);
            list.add(i);
            inDegreeGroup.put(grp, 0);
        }
        for (int i = 0; i < n; i++) {
            for (int beforeItem : beforeItems.get(i)) {
                dep.putIfAbsent(beforeItem, new ArrayList<>());
                List<Integer> list = dep.get(beforeItem);
                list.add(i);
                inDegreeItems[i]++;
                if (group[i] != group[beforeItem]) inDegreeGroup.put(group[i], inDegreeGroup.get(group[i]) + 1);
            }
        }
        List<Integer> resList = new ArrayList<>();
        Deque<Integer> groupQ = new ArrayDeque<>();
        for (Map.Entry<Integer, Integer> e : inDegreeGroup.entrySet()) {
            if (e.getValue() == 0) groupQ.add(e.getKey());
        }
        Set<Integer> visited = new HashSet<>();
        while (!groupQ.isEmpty()) {
            int grp = groupQ.poll();
            Deque<Integer> itemsQ = new ArrayDeque<>();
            for (int item : groups.get(grp)) {
                if (!visited.contains(item) && inDegreeItems[item] == 0) {
                    itemsQ.add(item);
                    visited.add(item);
                }
            }
            while (!itemsQ.isEmpty()) {
                int item = itemsQ.poll();
                resList.add(item);
                if (!dep.containsKey(item)) continue;
                for (int nei : dep.get(item)) {
                    inDegreeItems[nei]--;
                    if (group[nei] == grp) {
                        if (inDegreeItems[nei] == 0) itemsQ.add(nei);
                    } else {
                        int otherGrp = group[nei];
                        int newInDegree = inDegreeGroup.get(otherGrp) - 1;
                        inDegreeGroup.put(otherGrp, newInDegree);
                        if (newInDegree == 0) groupQ.add(otherGrp);
                    }
                }
            }
        }
        if (resList.size() < n) return new int[]{};
        int[] result = new int[n];
        for (int i = 0; i < n; i++) result[i] = resList.get(i);
        return result;
    }

}
