package leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * 1499. Max Value of Equation
 * You are given an array points containing the coordinates of points on a 2D plane, sorted by the x-values, where points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length. You are also given an integer k.
 *
 * Return the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k and 1 <= i < j <= points.length.
 *
 * It is guaranteed that there exists at least one pair of points that satisfy the constraint |xi - xj| <= k.
 *
 * Example 1:
 *
 * Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
 * Output: 4
 * Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if we calculate the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points also satisfy the condition and give a value of 10 + -10 + |5 - 6| = 1.
 * No other pairs satisfy the condition, so we return the max of 4 and 1.
 * Example 2:
 *
 * Input: points = [[0,0],[3,0],[9,2]], k = 3
 * Output: 3
 * Explanation: Only the first two points have an absolute difference of 3 or less in the x-values, and give the value of 0 + 0 + |0 - 3| = 3.
 *
 * Constraints:
 *
 * 2 <= points.length <= 105
 * points[i].length == 2
 * -108 <= xi, yi <= 108
 * 0 <= k <= 2 * 108
 * xi < xj for all 1 <= i < j <= points.length
 * xi form a strictly increasing sequence.
 */
public class EquationMaxVal {

    public int findMaxValueOfEquation(int[][] points, int k) { // yi+yj+xj-xi (xj>xi) = yi-xi + xj+yj
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]); // {yi-xi,xi}
        int res = Integer.MIN_VALUE;
        for (int[] point : points) {
            while (!pq.isEmpty() && pq.peek()[1] < point[0] - k) pq.poll();
            if (!pq.isEmpty()) res = Math.max(res, pq.peek()[0] + point[0] + point[1]);
            pq.add(new int[]{point[1] - point[0], point[0]});
        }
        return res;
    }

    public int findMaxValueOfEquation2(int[][] points, int k) { // yi+yj+xj-xi (xj>xi) = yi-xi + xj+yj
        Deque<int[]> pq = new ArrayDeque<>();
        int res = Integer.MIN_VALUE;
        for (int[] point : points) {
            while (!pq.isEmpty() && pq.getFirst()[1] < point[0] - k) pq.removeFirst();
            if (!pq.isEmpty()) res = Math.max(res, pq.getFirst()[0] + point[0] + point[1]);
            int yMinusX = point[1] - point[0];
            pq.add(new int[]{yMinusX, point[0]});
            while (!pq.isEmpty() && pq.getLast()[0] <= yMinusX) pq.removeLast();
            pq.addLast(new int[]{yMinusX, point[0]});
        }
        return res;
    }

}
