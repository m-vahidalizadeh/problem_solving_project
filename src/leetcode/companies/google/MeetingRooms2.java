package leetcode.companies.google;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Meeting Rooms II
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [[7,10],[2,4]]
 * Output: 1
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MeetingRooms2 {

    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int max = 0;
        int curr = 0;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < intervals.length; i++) {
            int s = intervals[i][0];
            curr++;
            while (!pq.isEmpty() && pq.peek() <= s) {
                pq.poll();
                curr--;
            }
            max = Math.max(max, curr);
            pq.add(intervals[i][1]);
        }
        return max;
    }

}
