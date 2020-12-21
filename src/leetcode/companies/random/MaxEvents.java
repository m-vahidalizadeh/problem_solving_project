package leetcode.companies.random;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1353. Maximum Number of Events That Can Be Attended
 * Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
 *
 * You can attend an event i at any day d where startTimei <= d <= endTimei. Notice that you can only attend one event at any time d.
 *
 * Return the maximum number of events you can attend.
 *
 * Example 1:
 *
 * Input: events = [[1,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: You can attend all the three events.
 * One way to attend them all is as shown.
 * Attend the first event on day 1.
 * Attend the second event on day 2.
 * Attend the third event on day 3.
 * Example 2:
 *
 * Input: events= [[1,2],[2,3],[3,4],[1,2]]
 * Output: 4
 * Example 3:
 *
 * Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
 * Output: 4
 * Example 4:
 *
 * Input: events = [[1,100000]]
 * Output: 1
 * Example 5:
 *
 * Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
 * Output: 7
 *
 * Constraints:
 *
 * 1 <= events.length <= 105
 * events[i].length == 2
 * 1 <= startDayi <= endDayi <= 105
 */
public class MaxEvents {

    public int maxEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int attended = 0;
        int currDay = 0;
        int i = 0;
        while (!pq.isEmpty() || i < n) {
            if (pq.isEmpty()) currDay = events[i][0];
            while (i < n && events[i][0] == currDay) {
                pq.add(events[i][1]);
                i++;
            }
            pq.poll();
            attended++;
            currDay++;
            while (!pq.isEmpty() && pq.peek() < currDay) pq.poll();
        }
        return attended;
    }

    public static void main(String[] args) {
        MaxEvents m = new MaxEvents();
        System.out.println(m.maxEvents(new int[][]{{1, 2}, {2, 2}, {3, 3}, {3, 4}, {3, 4}}));
    }

}
