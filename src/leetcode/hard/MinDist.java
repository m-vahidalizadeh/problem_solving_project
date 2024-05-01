package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 3102. Minimize Manhattan Distances
 * Hard
 *
 * Topics
 *
 * Hint
 * You are given a array points representing integer coordinates of some points on a 2D plane, where points[i] = [xi, yi].
 *
 * The distance between two points is defined as their
 * Manhattan distance
 * .
 * Return the minimum possible value for maximum distance between any two points by removing exactly one point.
 *
 * Example 1:
 *
 * Input: points = [[3,10],[5,15],[10,2],[4,4]]
 *
 * Output: 12
 *
 * Explanation:
 *
 * The maximum distance after removing each point is the following:
 *
 * After removing the 0th point the maximum distance is between points (5, 15) and (10, 2), which is |5 - 10| + |15 - 2| = 18.
 * After removing the 1st point the maximum distance is between points (3, 10) and (10, 2), which is |3 - 10| + |10 - 2| = 15.
 * After removing the 2nd point the maximum distance is between points (5, 15) and (4, 4), which is |5 - 4| + |15 - 4| = 12.
 * After removing the 3rd point the maximum distance is between points (5, 15) and (10, 2), which is |5 - 10| + |15 - 2| = 18.
 * 12 is the minimum possible maximum distance between any two points after removing exactly one point.
 *
 * Example 2:
 *
 * Input: points = [[1,1],[1,1],[1,1]]
 *
 * Output: 0
 *
 * Explanation:
 *
 * Removing any of the points results in the maximum distance between any two points of 0.
 *
 * Constraints:
 *
 * 3 <= points.length <= 10^5
 * points[i].length == 2
 * 1 <= points[i][0], points[i][1] <= 10^8
 */
public class MinDist {

    public int minimumDistance(int[][] points) {
        int minMaxDist = Integer.MAX_VALUE, n = points.length;
        List<int[]> v1 = new ArrayList<>(), v2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            v1.add(new int[]{points[i][0] + points[i][1], i});
            v2.add(new int[]{points[i][0] - points[i][1], i});
        }
        v1.sort((a, b) -> a[0] - b[0]);
        v2.sort((a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            int[] v1Max = v1.get(n - 1)[1] == i ? v1.get(n - 2) : v1.get(n - 1);
            int[] v2Max = v2.get(n - 1)[1] == i ? v2.get(n - 2) : v2.get(n - 1);
            int[] v1Min = v1.get(0)[1] == i ? v1.get(1) : v1.get(0);
            int[] v2Min = v2.get(0)[1] == i ? v2.get(1) : v2.get(0);
            minMaxDist = Math.min(minMaxDist, Math.max(v1Max[0] - v1Min[0], v2Max[0] - v2Min[0]));
        }
        return minMaxDist;
    }

}
