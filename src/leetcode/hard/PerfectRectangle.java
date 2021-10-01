package leetcode.hard;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * 391. Perfect Rectangle
 * Given an array rectangles where rectangles[i] = [xi, yi, ai, bi] represents an axis-aligned rectangle. The bottom-left point of the rectangle is (xi, yi) and the top-right point of it is (ai, bi).
 *
 * Return true if all the rectangles together form an exact cover of a rectangular region.
 *
 * Example 1:
 *
 * Input: rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
 * Output: true
 * Explanation: All 5 rectangles together form an exact cover of a rectangular region.
 * Example 2:
 *
 * Input: rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
 * Output: false
 * Explanation: Because there is a gap between the two rectangular regions.
 * Example 3:
 *
 * Input: rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[3,2,4,4]]
 * Output: false
 * Explanation: Because there is a gap in the top center.
 * Example 4:
 *
 * Input: rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
 * Output: false
 * Explanation: Because two of the rectangles overlap with each other.
 *
 * Constraints:
 *
 * 1 <= rectangles.length <= 2 * 104
 * rectangles[i].length == 4
 * -105 <= xi, yi, ai, bi <= 105
 */
public class PerfectRectangle {

    public boolean isRectangleCover(int[][] rectangles) {
        int area = 0;
        Rectangle fullRect = new Rectangle(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE});
        Set<Pair<Integer, Integer>> pointSet = new HashSet<>();
        for (int[] rect : rectangles) {
            Rectangle rectangle = new Rectangle(rect);
            area += rectangle.getArea();
            fullRect = new Rectangle(new int[]{Math.min(fullRect.x, rectangle.x), Math.min(fullRect.y, rectangle.y),
                    Math.max(fullRect.a, rectangle.a), Math.max(fullRect.b, rectangle.b)});
            for (Pair<Integer, Integer> point : rectangle.points) {
                if (pointSet.contains(point)) pointSet.remove(point);
                else pointSet.add(point);
            }
        }
        if (pointSet.size() != 4 || !pointSet.contains(fullRect.bl) || !pointSet.contains(fullRect.tl) ||
                !pointSet.contains(fullRect.br) || !pointSet.contains(fullRect.tr)) return false;
        return area == fullRect.getArea();
    }

    public class Rectangle {
        public Pair<Integer, Integer>[] points; // bottom-left,top-left,bottom-right,top-right
        int x;
        int y;
        int a;
        int b;
        Pair<Integer, Integer> bl;
        Pair<Integer, Integer> tl;
        Pair<Integer, Integer> br;
        Pair<Integer, Integer> tr;

        public Rectangle(int[] rect) {
            x = rect[0];
            y = rect[1];
            a = rect[2];
            b = rect[3];
            bl = new Pair<>(x, y);
            tl = new Pair<>(a, y);
            br = new Pair<>(x, b);
            tr = new Pair<>(a, b);
            points = new Pair[]{bl, tl, br, tr};
        }

        public int getArea() {
            return (a - x) * (b - y);
        }
    }

}
