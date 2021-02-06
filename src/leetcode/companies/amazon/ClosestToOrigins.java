package leetcode.companies.amazon;

import java.util.*;

/**
 * 973. K Closest Points to Origin
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 * Note:
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
public class ClosestToOrigins {

    public class Coordinate {
        int x;
        int y;
        Double d;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
            this.d = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Coordinate> pq = new PriorityQueue<>((a, b) -> a.d.compareTo(b.d));
        int index = 0;
        int[][] res = new int[K][2];
        for (int[] point : points) pq.add(new Coordinate(point[0], point[1]));
        while (index < K) {
            Coordinate curr = pq.poll();
            res[index][0] = curr.x;
            res[index++][1] = curr.y;
        }
        return res;
    }

    public static void main(String[] args) {
        ClosestToOrigins c = new ClosestToOrigins();
        System.out.println(Arrays.toString(c.kClosest(new int[][]{{1, 3}, {-2, 2}}, 1)));
    }

}
