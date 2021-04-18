package leetcode.companies.facebook;

/**
 * 53. Meeting Rooms II
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * Example 2:
 *
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * 0 <= starti < endi <= 106
 */
public class MinMeetingRooms {

    public int minMeetingRooms(int[][] intervals) {
        int[] meetings = new int[1_000_001];
        for (int[] interval : intervals) {
            meetings[interval[0]]++;
            meetings[interval[1]]--;
        }
        int max = 0;
        int curr = 0;
        for (int i = 0; i <= 1_000_000; i++) {
            curr += meetings[i];
            max = Math.max(max, curr);
        }
        return max;
    }

}
