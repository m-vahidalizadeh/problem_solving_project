package leetcode.hard;

/**
 * 1453. Maximum Number of Darts Inside of a Circular Dartboard
 * Alice is throwing n darts on a very large wall. You are given an array darts where darts[i] = [xi, yi] is the position of the ith dart that Alice threw on the wall.
 *
 * Bob knows the positions of the n darts on the wall. He wants to place a dartboard of radius r on the wall so that the maximum number of darts that Alice throws lies on the dartboard.
 *
 * Given the integer r, return the maximum number of darts that can lie on the dartboard.
 *
 * Example 1:
 *
 * Input: darts = [[-2,0],[2,0],[0,2],[0,-2]], r = 2
 * Output: 4
 * Explanation: Circle dartboard with center in (0,0) and radius = 2 contain all points.
 * Example 2:
 *
 * Input: darts = [[-3,0],[3,0],[2,6],[5,4],[0,9],[7,8]], r = 5
 * Output: 5
 * Explanation: Circle dartboard with center in (0,4) and radius = 5 contain all points except the point (7,8).
 *
 * Constraints:
 *
 * 1 <= darts.length <= 100
 * darts[i].length == 2
 * -104 <= xi, yi <= 104
 * 1 <= r <= 5000
 */
public class MaxNumOfDartsInsideOfACircularDartboard {

    public int numPoints(int[][] points, int r) {
        int n = points.length;
        int res = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double[] a = new double[]{points[i][0], points[i][1]};
                double[] b = new double[]{points[j][0], points[j][1]};
                double[][] centers = findCenters(a, b, (double) r);
                for (double[] center : centers) {
                    if (!Double.isNaN(center[0]) && !Double.isNaN(center[1])) {
                        int cur = 2;
                        for (int k = 0; k < n; k++) {
                            if (k != i && k != j) {
                                if (dist(center, new double[]{points[k][0], points[k][1]}) <= r) cur++;
                            }
                        }
                        res = Math.max(res, cur);
                    }
                }
            }
        }
        return res;
    }

    private double[][] findCenters(double[] a, double[] b, double r) {
        double[] mid = new double[]{(a[0] + b[0]) / 2, (a[1] + b[1]) / 2};
        double[][] res = new double[2][2];
        double opsLen = Math.sqrt(r * r - Math.pow(dist(a, mid), 2));
        double angle = Math.atan2(b[1] - a[1], b[0] - a[0]);
        res[0][0] = mid[0] + opsLen * Math.sin(angle);
        res[0][1] = mid[1] - opsLen * Math.cos(angle);
        res[1][0] = mid[0] - opsLen * Math.sin(angle);
        res[1][1] = mid[1] + opsLen * Math.cos(angle);
        return res;
    }

    private double dist(double[] a, double[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

}
