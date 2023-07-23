package leetcode.hard;

import java.util.LinkedList;

/**
 * 1924. Erect the Fence II
 * You are given a 2D integer array trees where trees[i] = [xi, yi] represents the location of the ith tree in the garden.
 *
 * You are asked to fence the entire garden using the minimum length of rope possible. The garden is well-fenced only if all the trees are enclosed and the rope used forms a perfect circle. A tree is considered enclosed if it is inside or on the border of the circle.
 *
 * More formally, you must form a circle using the rope with a center (x, y) and radius r where all trees lie inside or on the circle and r is minimum.
 *
 * Return the center and radius of the circle as a length 3 array [x, y, r]. Answers within 10-5 of the actual answer will be accepted.
 *
 * Example 1:
 *
 * Input: trees = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * Output: [2.00000,2.00000,2.00000]
 * Explanation: The fence will have center = (2, 2) and radius = 2
 * Example 2:
 *
 * Input: trees = [[1,2],[2,2],[4,2]]
 * Output: [2.50000,2.00000,1.50000]
 * Explanation: The fence will have center = (2.5, 2) and radius = 1.5
 *
 * Constraints:
 *
 * 1 <= trees.length <= 3000
 * trees[i].length == 2
 * 0 <= xi, yi <= 3000
 */
public class ErectTheFenceII {

    public double[] outerTrees(int[][] trees) {
        return welzl(trees, new LinkedList<>(), 0);
    }

    private double[] welzl(int[][] p, LinkedList<int[]> r, int offset) {
        if (offset == p.length || r.size() == 3) return trivial(r);
        double[] disk = welzl(p, r, offset + 1);
        if (inside(disk, p[offset])) return disk;
        r.addLast(p[offset]);
        disk = welzl(p, r, offset + 1);
        r.removeLast();
        return disk;
    }

    private double[] trivial(LinkedList<int[]> r) {
        if (r.isEmpty()) return null;
        if (r.size() == 1) return new double[]{r.get(0)[0], r.get(0)[1], 0};
        if (r.size() == 2) return getDiskFromTwoPoints(r.get(0), r.get(1));
        double[] disk01 = getDiskFromTwoPoints(r.get(0), r.get(1));
        if (inside(disk01, r.get(2))) return disk01;
        double[] disk02 = getDiskFromTwoPoints(r.get(0), r.get(2));
        if (inside(disk02, r.get(1))) return disk02;
        double[] disk12 = getDiskFromTwoPoints(r.get(1), r.get(2));
        if (inside(disk12, r.get(0))) return disk12;
        return getDiskFromThreePointsOnTheBoundary(r.get(0), r.get(1), r.get(2));
    }

    private double[] getDiskFromTwoPoints(int[] p1, int[] p2) {
        double x1 = p1[0], y1 = p1[1], x2 = p2[0], y2 = p2[1], r2 = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        return new double[]{(x1 + x2) / 2.0, (y1 + y2) / 2.0, Math.sqrt(r2) / 2.0};
    }

    private double[] getDiskFromThreePointsOnTheBoundary(int[] p1, int[] p2, int[] p3) {
        double[] center = getCenter(p2[0] - p1[0], p2[1] - p1[1], p3[0] - p1[0], p3[1] - p1[1]);
        center[0] += p1[0];
        center[1] += p1[1];
        double r2 = (center[0] - p1[0]) * (center[0] - p1[0]) + (center[1] - p1[1]) * (center[1] - p1[1]);
        return new double[]{center[0], center[1], Math.sqrt(r2)};
    }

    private double[] getCenter(double bx, double by, double cx, double cy) {
        double b = bx * bx + by * by, c = cx * cx + cy * cy, d = bx * cy - by * cx;
        return new double[]{(cy * b - by * c) / (2 * d), (bx * c - cx * b) / (2 * d)};
    }

    private boolean inside(double[] circle, int[] point) {
        if (circle == null) return false;
        double r = circle[2] * circle[2];
        double dist = (circle[0] - point[0]) * (circle[0] - point[0]) + (circle[1] - point[1]) * (circle[1] - point[1]);
        return dist <= r;
    }

}
