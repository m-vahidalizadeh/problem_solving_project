package leetcode.companies.google;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * 939. Minimum Area Rectangle
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.
 *
 * If there isn't any rectangle, return 0.
 *
 * Example 1:
 *
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 * Example 2:
 *
 * Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * Output: 2
 *
 * Note:
 *
 * 1 <= points.length <= 500
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * All points are distinct.
 */
public class MinRectangleArea {

    public int minAreaRect(int[][] points) {
        Set<Pair<Integer, Integer>> seen = new HashSet<>();
        int min = Integer.MAX_VALUE;
        for (int[] point : points) {
            int x1 = point[0];
            int y1 = point[1];
            for (Pair<Integer, Integer> point2 : seen) {
                Integer x2 = point2.getKey();
                Integer y2 = point2.getValue();
                if (seen.contains(new Pair<>(x2, y1)) && seen.contains(new Pair<>(x1, y2)))
                    min = Math.min(min, Math.abs(y1 - y2) * Math.abs(x1 - x2));
            }
            seen.add(new Pair(x1, y1));
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

}
