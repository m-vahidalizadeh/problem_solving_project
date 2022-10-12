package leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2398. Maximum Number of Robots Within Budget
 * You have n robots. You are given two 0-indexed integer arrays, chargeTimes and runningCosts, both of length n. The ith robot costs chargeTimes[i] units to charge and costs runningCosts[i] units to run. You are also given an integer budget.
 *
 * The total cost of running k chosen robots is equal to max(chargeTimes) + k * sum(runningCosts), where max(chargeTimes) is the largest charge cost among the k robots and sum(runningCosts) is the sum of running costs among the k robots.
 *
 * Return the maximum number of consecutive robots you can run such that the total cost does not exceed budget.
 *
 * Example 1:
 *
 * Input: chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
 * Output: 3
 * Explanation:
 * It is possible to run all individual and consecutive pairs of robots within budget.
 * To obtain answer 3, consider the first 3 robots. The total cost will be max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 which is less than 25.
 * It can be shown that it is not possible to run more than 3 consecutive robots within budget, so we return 3.
 * Example 2:
 *
 * Input: chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
 * Output: 0
 * Explanation: No robot can be run that does not exceed the budget, so we return 0.
 *
 * Constraints:
 *
 * chargeTimes.length == runningCosts.length == n
 * 1 <= n <= 5 * 104
 * 1 <= chargeTimes[i], runningCosts[i] <= 105
 * 1 <= budget <= 1015
 */
public class MaximumNumberOfRobotsWithinBudget {

    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        long sum = 0;
        int start = 0, max = 0; // max: the maximum consecutive robots
        Deque<Integer> queue = new ArrayDeque<>(); // First has the max charge time
        for (int end = 0; end < chargeTimes.length; end++) {
            sum += runningCosts[end]; // Add the running cost of the end item to the sum
            while (!queue.isEmpty() && chargeTimes[queue.getLast()] <= chargeTimes[end]) queue.removeLast(); // Remove the middle charge time items. Only stop when the charge time is greater than the current one or the queue is empty.
            queue.addLast(end); // Add the new charge time (end of the current window)
            while (!queue.isEmpty() && (chargeTimes[queue.getFirst()] + (end - start + 1) * sum) > budget) { // Check the cost against the budget
                if (queue.getFirst() == start) queue.removeFirst(); // If the start item has the max charge time in the current window, remove it and consider the next max in the queue.
                sum -= runningCosts[start++]; // Remove the running cost of the removed item from sum and make sure you add one to the start of the current window (start++).
            }
            max = Math.max(max, end - start + 1); // Keep track of the max sliding window size
        }
        return max; // Return the max consecutive robots that can be run considering the current budget.
    }

}
