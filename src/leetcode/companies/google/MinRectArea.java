package leetcode.companies.google;

import java.util.HashSet;
import java.util.Set;

/**
 * Minimum Area Rectangle
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.
 * <p>
 * If there isn't any rectangle, return 0.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 * Example 2:
 * <p>
 * Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * Output: 2
 * <p>
 * Note:
 * <p>
 * 1 <= points.length <= 500
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * All points are distinct.
 */
public class MinRectArea {

    // Just for hashing function, since x and y are less than or equals 40_000
    public static final int XFACTOR = 40_001;

    public int minAreaRect(int[][] points) {
        int n = points.length;
        int area = Integer.MAX_VALUE;
        Set<Integer> pointsSet = new HashSet<>();
        for (int[] point : points) pointsSet.add(point[0] * XFACTOR + point[1]);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Skip if these two are not diagonal points
                if (points[i][0] == points[j][0] || points[i][1] == points[j][1]) continue;
                if (pointsSet.contains(points[i][0] * XFACTOR + points[j][1])
                        && pointsSet.contains(points[j][0] * XFACTOR + points[i][1])) {
                    area = Math.min(area, Math.abs(points[i][0] - points[j][0]) * Math.abs(points[i][1] - points[j][1]));
                }
            }
        }
        return area < Integer.MAX_VALUE ? area : 0;
    }

    public static void main(String[] args) {
        MinRectArea m = new MinRectArea();
        System.out.println(m.minAreaRect(new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}, {2, 2}}));
        System.out.println(m.minAreaRect(new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}, {4, 1}, {4, 3}}));
    }

}
