package leetcode.companies.google;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 56. Merge Intervals
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class IntervalsMerge {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        int n = intervals.length;
        int currStart = intervals[n - 1][0];
        int currEnd = intervals[n - 1][1];
        LinkedList<int[]> resList = new LinkedList<>();
        for (int i = n - 2; i >= 0; i--) {
            if (intervals[i][1] < currStart) {
                resList.addFirst(new int[]{currStart, currEnd});
                currStart = intervals[i][0];
                currEnd = intervals[i][1];
            } else currStart = Math.min(intervals[i][0], currStart);
        }
        resList.addFirst(new int[]{currStart, currEnd});
        int[][] res = new int[resList.size()][2];
        int i = 0;
        for (int[] interval : resList) res[i++] = interval;
        return res;
    }

}
