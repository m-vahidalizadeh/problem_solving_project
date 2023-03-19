package leetcode.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 2589. Minimum Time to Complete All Tasks
 * There is a computer that can run an unlimited number of tasks at the same time. You are given a 2D integer array tasks where tasks[i] = [starti, endi, durationi] indicates that the ith task should run for a total of durationi seconds (not necessarily continuous) within the inclusive time range [starti, endi].
 *
 * You may turn on the computer only when it needs to run a task. You can also turn it off if it is idle.
 *
 * Return the minimum time during which the computer should be turned on to complete all tasks.
 *
 * Example 1:
 *
 * Input: tasks = [[2,3,1],[4,5,1],[1,5,2]]
 * Output: 2
 * Explanation:
 * - The first task can be run in the inclusive time range [2, 2].
 * - The second task can be run in the inclusive time range [5, 5].
 * - The third task can be run in the two inclusive time ranges [2, 2] and [5, 5].
 * The computer will be on for a total of 2 seconds.
 * Example 2:
 *
 * Input: tasks = [[1,3,2],[2,5,3],[5,6,2]]
 * Output: 4
 * Explanation:
 * - The first task can be run in the inclusive time range [2, 3].
 * - The second task can be run in the inclusive time ranges [2, 3] and [5, 5].
 * - The third task can be run in the two inclusive time range [5, 6].
 * The computer will be on for a total of 4 seconds.
 *
 * Constraints:
 *
 * 1 <= tasks.length <= 2000
 * tasks[i].length == 3
 * 1 <= starti, endi <= 2000
 * 1 <= durationi <= endi - starti + 1
 */
public class MinTimeToCompleteAllTasks {

    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (t1, t2) -> t1[1] == t2[1] ? t1[0] - t2[0] : t1[1] - t2[1]);
        int answer = 0;
        Set<Integer> used = new HashSet<>();
        for (int[] task : tasks) {
            int usedTime = 0;
            for (int i = task[0]; i <= task[1]; i++)
                if (used.contains(i)) usedTime++;
            int leftTime = task[2] - usedTime;
            for (int i = task[1]; leftTime > 0; i--) {
                if (used.add(i)) {
                    leftTime--;
                    answer++;
                }
            }
        }
        return answer;
    }

}
