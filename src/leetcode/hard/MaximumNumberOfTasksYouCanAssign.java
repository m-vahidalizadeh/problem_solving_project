package leetcode.hard;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 2071. Maximum Number of Tasks You Can Assign
 * You have n tasks and m workers. Each task has a strength requirement stored in a 0-indexed integer array tasks, with the ith task requiring tasks[i] strength to complete. The strength of each worker is stored in a 0-indexed integer array workers, with the jth worker having workers[j] strength. Each worker can only be assigned to a single task and must have a strength greater than or equal to the task's strength requirement (i.e., workers[j] >= tasks[i]).
 *
 * Additionally, you have pills magical pills that will increase a worker's strength by strength. You can decide which workers receive the magical pills, however, you may only give each worker at most one magical pill.
 *
 * Given the 0-indexed integer arrays tasks and workers and the integers pills and strength, return the maximum number of tasks that can be completed.
 *
 * Example 1:
 *
 * Input: tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1
 * Output: 3
 * Explanation:
 * We can assign the magical pill and tasks as follows:
 * - Give the magical pill to worker 0.
 * - Assign worker 0 to task 2 (0 + 1 >= 1)
 * - Assign worker 1 to task 1 (3 >= 2)
 * - Assign worker 2 to task 0 (3 >= 3)
 * Example 2:
 *
 * Input: tasks = [5,4], workers = [0,0,0], pills = 1, strength = 5
 * Output: 1
 * Explanation:
 * We can assign the magical pill and tasks as follows:
 * - Give the magical pill to worker 0.
 * - Assign worker 0 to task 0 (0 + 5 >= 5)
 * Example 3:
 *
 * Input: tasks = [10,15,30], workers = [0,10,10,10,10], pills = 3, strength = 10
 * Output: 2
 * Explanation:
 * We can assign the magical pills and tasks as follows:
 * - Give the magical pill to worker 0 and worker 1.
 * - Assign worker 0 to task 0 (0 + 10 >= 10)
 * - Assign worker 1 to task 1 (10 + 10 >= 15)
 * Example 4:
 *
 * Input: tasks = [5,9,8,5,9], workers = [1,6,4,2,6], pills = 1, strength = 5
 * Output: 3
 * Explanation:
 * We can assign the magical pill and tasks as follows:
 * - Give the magical pill to worker 2.
 * - Assign worker 1 to task 0 (6 >= 5)
 * - Assign worker 2 to task 2 (4 + 5 >= 8)
 * - Assign worker 4 to task 3 (6 >= 5)
 *
 * Constraints:
 *
 * n == tasks.length
 * m == workers.length
 * 1 <= n, m <= 5 * 104
 * 0 <= pills <= m
 * 0 <= tasks[i], workers[j], strength <= 109
 */
public class MaximumNumberOfTasksYouCanAssign {

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int l = 0;
        int r = tasks.length - 1;
        int res = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(tasks, workers, pills, strength, mid)) {
                res = mid;
                l = mid + 1;
            } else r = mid - 1;
        }
        return res + 1;
    }

    private boolean check(int[] tasks, int[] workers, int pills, int strength, int mid) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int j = workers.length - 1;
        for (int i = mid; i >= 0; i--) {
            while (j >= 0 && workers[j] >= tasks[i]) add(treeMap, workers[j--]);
            Integer ceil = treeMap.ceilingKey(tasks[i]);
            if (ceil != null) del(treeMap, ceil);
            else {
                if (pills > 0) {
                    while (j >= 0 && (workers[j] + strength >= tasks[i])) add(treeMap, workers[j--]);
                    ceil = treeMap.ceilingKey(tasks[i] - strength);
                    if (ceil != null) {
                        pills--;
                        del(treeMap, ceil);
                    } else return false;
                } else return false;
            }
        }
        return true;
    }

    private void add(TreeMap<Integer, Integer> treeMap, int worker) {
        treeMap.put(worker, treeMap.getOrDefault(worker, 0) + 1);
    }

    private void del(TreeMap<Integer, Integer> treemap, int worker) {
        treemap.put(worker, treemap.get(worker) - 1);
        if (treemap.get(worker) == 0) treemap.remove(worker);
    }

}
