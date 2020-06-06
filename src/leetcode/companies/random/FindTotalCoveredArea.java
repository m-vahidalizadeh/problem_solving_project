package leetcode.companies.random;

/**
 * Rectangle Area
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * <p>
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * <p>
 * Rectangle Area
 * <p>
 * Example:
 * <p>
 * Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
 * Output: 45
 * Note:
 * <p>
 * Assume that the total area is never beyond the maximum possible value of int.
 */
public class FindTotalCoveredArea {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (A <= E && B <= F && G <= C && H <= D) return getArea(A, B, C, D);
        if (E <= A && F <= B && C <= G && D <= H) return getArea(E, F, G, H);
        return getArea(A, B, C, D) + getArea(E, F, G, H) - getIntersectArea(A, B, C, D, E, F, G, H);
    }

    private int getArea(int x1, int y1, int x2, int y2) {
        return Math.abs((x1 - x2) * (y1 - y2));
    }

    private int getIntersectArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (G < A || C < E || F > D || H < B) return 0;
        return getArea(Math.max(A, E), Math.max(F, B), Math.min(C, G), Math.min(D, H));
    }

    public static void main(String[] args) {
        FindTotalCoveredArea f = new FindTotalCoveredArea();
        System.out.println(f.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }

}
