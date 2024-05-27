package leetcode.companies.facebook;

import javafx.util.Pair;

import java.util.*;

/**
 * 621. Task Scheduler
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * You are given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n. Each cycle or interval allows the completion of one task. Tasks can be completed in any order, but there's a constraint: identical tasks must be separated by at least n intervals due to cooling time.
 *
 * ​Return the minimum number of intervals required to complete all tasks.
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 *
 * Output: 8
 *
 * Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
 *
 * After completing task A, you must wait two cycles before doing A again. The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th cycle, you can do A again as 2 intervals have passed.
 *
 * Example 2:
 *
 * Input: tasks = ["A","C","A","B","D","B"], n = 1
 *
 * Output: 6
 *
 * Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
 *
 * With a cooling interval of 1, you can repeat a task after just one other task.
 *
 * Example 3:
 *
 * Input: tasks = ["A","A","A", "B","B","B"], n = 3
 *
 * Output: 10
 *
 * Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
 *
 * There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice between repetitions of these tasks.
 *
 * Constraints:
 *
 * 1 <= tasks.length <= 10^4
 * tasks[i] is an uppercase English letter.
 * 0 <= n <= 100
 */
public class LeastCPUIntervals {

    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> last = new HashMap<>();
        for (char task : tasks) map.put(task, map.getOrDefault(task, 0) + 1);
        PriorityQueue<Pair<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (char task : map.keySet()) {
            pq.add(new Pair<>(task, map.get(task)));
            last.put(task, -100);
        }
        int time = 0;
        while (map.size() > 0) {
            Set<Pair<Character, Integer>> set = new HashSet<>();
            while (!pq.isEmpty() && (!map.containsKey(pq.peek().getKey()) || time - last.get(pq.peek().getKey()) <= n)) {
                set.add(pq.poll());
            }
            if (!pq.isEmpty()) {
                while (!map.containsKey(pq.peek().getKey())) {
                    pq.poll();
                }
                Pair<Character, Integer> pair = pq.poll();
                char task = pair.getKey();
                int newFreq = map.get(task) - 1;
                if (newFreq == 0) map.remove(task);
                else {
                    map.put(task, newFreq);
                    pq.add(new Pair<>(task, newFreq));
                }
                last.put(task, time);
            }
            for (Pair<Character, Integer> pair : set) {
                if (map.containsKey(pair.getKey())) {
                    pq.add(pair);
                }
            }
            time++;
        }
        return time;
    }

}
