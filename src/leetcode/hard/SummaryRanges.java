package leetcode.hard;

import java.util.*;

/**
 * 352. Data Stream as Disjoint Intervals
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.
 * <p>
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 * <p>
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * <p>
 * Follow up:
 * <p>
 * What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 */
public class SummaryRanges {

    Set<Integer> data;
    int[][] cache;
    boolean changed;

    public SummaryRanges() {
        data = new HashSet<>();
        changed = false;
    }

    public void addNum(int val) {
        data.add(val);
        changed = true;
    }

    public int[][] getIntervals() {
        if (!changed) return cache;
        List<int[]> intervalList = new ArrayList<>();
        Integer[] dArr = data.toArray(Integer[]::new);
        Arrays.sort(dArr);
        int n = dArr.length;
        int s = 0;
        int e = 0;
        int prev = dArr[0];
        while (e < n) {
            while (e < n && (prev == dArr[e] || prev + 1 == dArr[e])) {
                prev = dArr[e];
                e++;
            }
            if (e == n) {
                intervalList.add(new int[]{dArr[s], dArr[e - 1]});
            } else {
                intervalList.add(new int[]{dArr[s], dArr[e - 1]});
                prev = dArr[e];
                s = e;
            }
        }
        int[][] result = new int[intervalList.size()][2];
        for (int i = 0; i < intervalList.size(); i++) result[i] = intervalList.get(i);
        cache = result;
        changed = false;
        return result;
    }

    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(1);
        summaryRanges.addNum(3);
        summaryRanges.addNum(7);
        summaryRanges.addNum(2);
        summaryRanges.addNum(6);
        int[][] result = summaryRanges.getIntervals();
        for (int[] interval : result) System.out.println(interval[0] + "-" + interval[1]);
    }

}
