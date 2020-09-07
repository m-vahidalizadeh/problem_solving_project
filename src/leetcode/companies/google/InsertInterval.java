package leetcode.companies.google;

import java.util.Stack;

/**
 * Insert Interval
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if (n == 0) return new int[][]{newInterval};
        Stack<int[]> stack = new Stack<>();
        boolean inserted = false;
        for (int[] interval : intervals) {
            if (!inserted && newInterval[0] <= interval[0]) {
                if (!stack.isEmpty() && newInterval[0] <= stack.peek()[1]) {
                    int[] temp = stack.pop();
                    stack.push(new int[]{temp[0], Math.max(temp[1], newInterval[1])});
                } else {
                    stack.push(newInterval);
                }
                inserted = true;
            }
            if (stack.isEmpty()) stack.push(interval);
            else if (interval[0] <= stack.peek()[1]) {
                int[] temp = stack.pop();
                stack.push(new int[]{temp[0], Math.max(temp[1], interval[1])});
            } else {
                stack.push(interval);
            }
        }
        // Insert at last
        if (!inserted) {
            if (newInterval[0] <= intervals[n - 1][1]) {
                int[] temp = stack.pop();
                stack.push(new int[]{temp[0], Math.max(temp[1], newInterval[1])});
            } else {
                stack.push(newInterval);
            }
        }
        int[][] result = new int[stack.size()][2];
        int index = result.length - 1;
        while (!stack.isEmpty()) result[index--] = stack.pop();
        return result;
    }

    public static void main(String[] args) {
        InsertInterval i = new InsertInterval();
        i.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5});
        i.insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8});
        i.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{-10, 2});
        i.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{8, 20});
    }

}
