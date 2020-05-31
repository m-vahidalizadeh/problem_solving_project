package leetcode.companies.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Merge Intervals
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class MergeIntervals {

    class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    class Gaps {
        List<Interval> intervals;
        Interval currentInterval;

        Gaps() {
            intervals = new ArrayList<>();
        }

        public void add(Interval interval) {
            if (currentInterval == null) {
                currentInterval = new Interval(interval.start, interval.end);
            } else {
                if (interval.start <= currentInterval.end) {
                    currentInterval.end = Math.max(interval.end, currentInterval.end);
                } else {
                    intervals.add(currentInterval);
                    currentInterval = new Interval(interval.start, interval.end);
                }
            }
        }

        public List<Interval> getIntervals() {
            if (currentInterval != null) intervals.add(currentInterval);
            return intervals;
        }
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Gaps gaps = new Gaps();
        for (int i = 0; i < intervals.length; i++) gaps.add(new Interval(intervals[i][0], intervals[i][1]));
        List<Interval> allGaps = gaps.getIntervals();
        int allGapsSize = allGaps.size();
        int[][] result = new int[allGapsSize][2];
        for (int i = 0; i < allGapsSize; i++) {
            Interval intervalI = allGaps.get(i);
            result[i][0] = intervalI.start;
            result[i][1] = intervalI.end;
        }
        return result;
    }

    public static void main(String[] args) {
        MergeIntervals m = new MergeIntervals();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        printIntervals(m.merge(intervals));
        int[][] intervals2 = {{1, 4}, {0, 2}, {3, 5}};
        printIntervals(m.merge(intervals2));
        int[][] intervals3 = {{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}};
        printIntervals(m.merge(intervals3));
    }

    private static void printIntervals(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            System.out.print(intervals[i][0] + "-" + intervals[i][1] + " ");
        }
        System.out.println();
    }

}
