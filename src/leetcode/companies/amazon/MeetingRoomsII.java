package leetcode.companies.amazon;

import java.util.Map;
import java.util.TreeMap;

/**
 * 253. Meeting Rooms II
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: 1
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int[] interval : intervals) {
            int s = interval[0];
            int e = interval[1];
            tm.put(s, tm.getOrDefault(s, 0) + 1);
            tm.put(e, tm.getOrDefault(e, 0) - 1);
        }
        int max = 0;
        int count = 0;
        for (Map.Entry<Integer, Integer> e : tm.entrySet()) {
            count += e.getValue();
            max = Math.max(max, count);
        }
        return max;
    }

    public static void main(String[] args) {
        MeetingRoomsII m = new MeetingRoomsII();
        System.out.println(m.minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        System.out.println(m.minMeetingRooms(new int[][]{{7, 10}, {2, 4}}));
    }

}
