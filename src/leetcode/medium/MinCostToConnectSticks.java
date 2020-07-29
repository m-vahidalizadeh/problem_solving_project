package leetcode.medium;

import java.util.PriorityQueue;

/**
 * Minimum Cost to Connect Sticks
 * You have some sticks with positive integer lengths.
 * <p>
 * You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  You perform this action until there is one stick remaining.
 * <p>
 * Return the minimum cost of connecting all the given sticks into one stick in this way.
 * <p>
 * Example 1:
 * <p>
 * Input: sticks = [2,4,3]
 * Output: 14
 * Example 2:
 * <p>
 * Input: sticks = [1,8,3,5]
 * Output: 30
 * <p>
 * Constraints:
 * <p>
 * 1 <= sticks.length <= 10^4
 * 1 <= sticks[i] <= 10^4
 */
public class MinCostToConnectSticks {

    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int cost = 0;
        for (int stick : sticks) minHeap.add(stick);
        while (minHeap.size() > 1) {
            int sum = minHeap.poll() + minHeap.poll();
            cost += sum;
            minHeap.add(sum);
        }
        return cost;
    }

}
