package leetcode.companies.bloomberg;

/**
 * Largest Rectangle in Histogram
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 * Example:
 *
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */
public class MyPower {

    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double result = 1;
        for (long i = N; i > 0; i /= 2) {
            if (i % 2 != 0) result *= x;
            x *= x;
        }
        return result;
    }

    public static void main(String[] args) {
        MyPower m = new MyPower();
        double x = 2.0;
        int n = -2147483648;
        System.out.println(m.myPow(x, n));
    }

}
