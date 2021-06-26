package leetcode.hard;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 850. Rectangle Area II
 * We are given a list of (axis-aligned) rectangles. Each rectangle[i] = [xi1, yi1, xi2, yi2] , where (xi1, yi1) are the coordinates of the bottom-left corner, and (xi2, yi2) are the coordinates of the top-right corner of the ith rectangle.
 *
 * Find the total area covered by all rectangles in the plane. Since the answer may be too large, return it modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
 * Output: 6
 * Explanation: As illustrated in the picture.
 * Example 2:
 *
 * Input: rectangles = [[0,0,1000000000,1000000000]]
 * Output: 49
 * Explanation: The answer is 1018 modulo (109 + 7), which is (109)2 = (-7)2 = 49.
 *
 * Constraints:
 *
 * 1 <= rectangles.length <= 200
 * rectanges[i].length = 4
 * 0 <= rectangles[i][j] <= 109
 * The total area covered by all rectangles will never exceed 263 - 1 and thus will fit in a 64-bit signed integer.
 */
public class RectAreaII {

    int MOD = 1_000_000_007;

    public int rectangleArea(int[][] rectangles) {
        long totalArea = 0;
        List<int[]> events = new ArrayList<>();
        for (int[] rect : rectangles) {
            int x1 = rect[0];
            int y1 = rect[1];
            int x2 = rect[2];
            int y2 = rect[3];
            events.add(new int[]{x1, 0, y1, y2});
            events.add(new int[]{x2, 1, y1, y2});
        }
        events.sort((a, b) -> a[0] - b[0]);
        List<Pair<Integer, Integer>> intervals = new ArrayList<>();
        int prevX = 0;
        for (int[] event : events) {
            int curX = event[0];
            int type = event[1];
            int y1 = event[2];
            int y2 = event[3];
            totalArea = (totalArea + getArea(curX - prevX, intervals)) % MOD;
            if (type == 1) intervals.remove(new Pair<>(y1, y2));
            else {
                intervals.add(new Pair<>(y1, y2));
                intervals.sort((a, b) -> a.getKey() - b.getKey());
            }
            prevX = curX;
        }
        return (int) totalArea;
    }

    private long getArea(long width, List<Pair<Integer, Integer>> intervals) {
        long area = 0;
        int prevY = 0;
        for (Pair<Integer, Integer> interval : intervals) {
            int bottom = Math.max(interval.getKey(), prevY);
            int top = interval.getValue();
            if (top > bottom) {
                area += (top - bottom) * width;
                prevY = top;
            }
        }
        return area % MOD;
    }

    public static void main(String[] args) {
        RectAreaII r = new RectAreaII();
        System.out.println(r.rectangleArea(new int[][]{{0, 0, 1000000000, 1000000000}}));
    }

}
