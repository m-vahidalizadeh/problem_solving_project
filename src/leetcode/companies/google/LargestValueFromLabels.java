package leetcode.companies.google;

import java.util.*;

/**
 * Largest Values From Labels
 * We have a set of items: the i-th item has value values[i] and label labels[i].
 * <p>
 * Then, we choose a subset S of these items, such that:
 * <p>
 * |S| <= num_wanted
 * For every label L, the number of items in S with label L is <= use_limit.
 * Return the largest possible sum of the subset S.
 * <p>
 * Example 1:
 * <p>
 * Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
 * Output: 9
 * Explanation: The subset chosen is the first, third, and fifth item.
 * Example 2:
 * <p>
 * Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
 * Output: 12
 * Explanation: The subset chosen is the first, second, and third item.
 * Example 3:
 * <p>
 * Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
 * Output: 16
 * Explanation: The subset chosen is the first and fourth item.
 * Example 4:
 * <p>
 * Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
 * Output: 24
 * Explanation: The subset chosen is the first, second, and fourth item.
 * <p>
 * Note:
 * <p>
 * 1 <= values.length == labels.length <= 20000
 * 0 <= values[i], labels[i] <= 20000
 * 1 <= num_wanted, use_limit <= values.length
 */
public class LargestValueFromLabels {

    public class Group {
        int label;
        PriorityQueue<Integer> values;

        public Group(int label) {
            this.label = label;
            this.values = new PriorityQueue<>(Comparator.reverseOrder());
        }
    }

    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        Map<Integer, Group> groupsMap = new HashMap<>();
        int n = values.length;
        for (int i = 0; i < n; i++) {
            Group currGroup;
            if (groupsMap.containsKey(labels[i])) currGroup = groupsMap.get(labels[i]);
            else currGroup = new Group(labels[i]);
            currGroup.values.add(values[i]);
            groupsMap.put(labels[i], currGroup);
        }
        PriorityQueue<Group> pq = new PriorityQueue<>((g1, g2) -> g2.values.peek() - g1.values.peek());
        pq.addAll(groupsMap.values());
        Map<Integer, Integer> usedLabels = new HashMap<>();
        int sum = 0;
        while (!pq.isEmpty() && num_wanted > 0) {
            Group currGroup = pq.poll();
            if (!currGroup.values.isEmpty()) {
                int currValue = currGroup.values.poll();
                if (!currGroup.values.isEmpty()) pq.add(currGroup);
                if (usedLabels.getOrDefault(currGroup.label, 0) < use_limit) {
                    sum += currValue;
                    usedLabels.put(currGroup.label, usedLabels.getOrDefault(currGroup.label, 0) + 1);
                    num_wanted--;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        LargestValueFromLabels l = new LargestValueFromLabels();
        System.out.println(l.largestValsFromLabels(new int[]{5, 4, 3, 2, 1}, new int[]{1, 1, 2, 2, 3}, 3, 1));
        System.out.println(l.largestValsFromLabels(new int[]{5, 4, 3, 2, 1}, new int[]{1, 3, 3, 3, 2}, 3, 2));
        System.out.println(l.largestValsFromLabels(new int[]{9, 8, 8, 7, 6}, new int[]{0, 0, 0, 1, 1}, 3, 1));
        System.out.println(l.largestValsFromLabels(new int[]{9, 8, 8, 7, 6}, new int[]{0, 0, 0, 1, 1}, 3, 2));
    }

}
