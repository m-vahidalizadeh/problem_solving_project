package leetcode.companies.bloomberg;

/**
 * Sqrt(x)
 * Implement int sqrt(int x).
 * <p>
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 * <p>
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 * <p>
 * Example 1:
 * <p>
 * Input: 4
 * Output: 2
 * Example 2:
 * <p>
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 * the decimal part is truncated, 2 is returned.
 */
public class CalculateSQRT {

    public int mySqrt(int x) {
        if (x < 2) return x;
//        return sqrtRec(Math.log(x), 0, x);
        return sqrtNewton(x);
    }

    /**
     * Calculates the square root of x based on the Newton's approach.
     *
     * @param x The x
     * @return The square root of x
     */
    private int sqrtNewton(int x) {
        double x0 = x;
        double x1 = (x0 + x / x0) / 2.0;
        while (Math.abs(x1 - x0) >= 1) {
            x0 = x1;
            x1 = (x0 + x / x0) / 2.0;
        }
        return Double.valueOf(x1).intValue();
    }

    /**
     * Calculates the square root of x by using recursion and binary search.
     *
     * @param logX  The logarithm of x
     * @param start The start number
     * @param end   The end number
     * @return The square root of x
     */
    private int sqrtRec(double logX, int start, int end) {
        int mid = (start + end) / 2;
        int midPlusOne = mid + 1;
        double a = logX / Math.log(mid);
        double b = logX / Math.log(midPlusOne);
        if (a < 2) return sqrtRec(logX, start, mid - 1);
        else if (a >= 2 && b < 2) return mid;
        else return sqrtRec(logX, mid + 1, end);
    }

    public static void main(String[] args) {
        CalculateSQRT c = new CalculateSQRT();
        System.out.println(c.mySqrt(4));
    }

}
