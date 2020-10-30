package leetcode.companies.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 167. Two Sum II - Input array is sorted
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 *
 * Note:
 *
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 *
 * Example 1:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 * Example 2:
 *
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Example 3:
 *
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 *
 * Constraints:
 *
 * 2 <= nums.length <= 3 * 104
 * -1000 <= nums[i] <= 1000
 * nums is sorted in increasing order.
 * -1000 <= target <= 1000
 */
public class MergeIntervals {

    public class Interval {
        int s;
        int e;

        public Interval(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[][]{};
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<Interval> list = new ArrayList<>();
        Interval currInterval = null;
        for (int[] interval : intervals) {
            int s = interval[0];
            int e = interval[1];
            if (currInterval == null) {
                currInterval = new Interval(s, e);
            } else if (s <= currInterval.e) {
                currInterval = new Interval(Math.min(s, currInterval.s), Math.max(e, currInterval.e));
            } else {
                list.add(currInterval);
                currInterval = new Interval(s, e);
            }
        }
        list.add(currInterval);
        int[][] result = new int[list.size()][2];
        int i = 0;
        for (Interval interval : list) {
            result[i][0] = interval.s;
            result[i++][1] = interval.e;
        }
        return result;
    }

    public static void main(String[] args) {
        MergeIntervals m = new MergeIntervals();
        print2DArray(m.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}));
        print2DArray(m.merge(new int[][]{{1, 4}, {4, 5}}));
    }

    private static void print2DArray(int[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                System.out.print(input[i][j] + " ");
            }
            System.out.println();
        }
    }

}
