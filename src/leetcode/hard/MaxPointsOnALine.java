package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 149. Max Points on a Line
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.
 *
 * Example 1:
 *
 * Input: points = [[1,1],[2,2],[3,3]]
 * Output: 3
 * Example 2:
 *
 * Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 *
 * Constraints:
 *
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * All the points are unique.
 */
public class MaxPointsOnALine {

    public int maxPoints(int[][] points) {
        int ans = 0;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            int[] p1 = points[i];
            int same = 0;
            Map<Double, Integer> slopes = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    int[] p2 = points[j];
                    if (p1 == p2) same++;
                    else {
                        double slope = getSlope(p1, p2);
                        slopes.put(slope, slopes.getOrDefault(slope, 0) + 1);
                    }
                }
            }
            int max1 = 0;
            if (slopes.size() > 0) {
                for (Integer val : slopes.values()) {
                    max1 = Math.max(max1, val);
                }
            }
            ans = Math.max(ans, max1 + same + 1);
        }
        return ans;
    }

    private double getSlope(int[] a, int[] b) {
        double yDiff = a[1] - b[1];
        double xDiff = a[0] - b[0];
        if (xDiff == 0) return Double.MAX_VALUE;
        return yDiff / xDiff;
    }

}
