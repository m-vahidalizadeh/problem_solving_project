package leetcode.medium;

import java.util.PriorityQueue;

/**
 * K Closest Points to Origin
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * <p>
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * <p>
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 * <p>
 * Example 1:
 * <p>
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 * <p>
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
public class KClosestPointsToOrigin {

    public class Coordinate implements Comparable<Coordinate> {
        int x;
        int y;
        Double distance;

        public Coordinate(int x, int y, double distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Coordinate c) {
            return this.distance.compareTo(c.distance);
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        PriorityQueue<Coordinate> pq = new PriorityQueue<>();
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            double distance = Math.pow(x, 2) + Math.pow(y, 2);
            pq.add(new Coordinate(x, y, distance));
        }
        int[][] result = new int[K][2];
        for (int i = 0; i < K; i++) {
            Coordinate currCoordinate = pq.poll();
            result[i][0] = currCoordinate.x;
            result[i][1] = currCoordinate.y;
        }
        return result;
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin k = new KClosestPointsToOrigin();
        int[][] points1 = {{1, 3}, {-2, 2}};
        printCoordinates(k.kClosest(points1, 1));
        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        printCoordinates(k.kClosest(points2, 2));
    }

    private static void printCoordinates(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("[" + array[i][0] + "," + array[i][1] + "] ");
        }
        System.out.println();
    }

}
