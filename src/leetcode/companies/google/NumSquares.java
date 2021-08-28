package leetcode.companies.google;

import java.util.ArrayList;
import java.util.List;

/**
 * 279. Perfect Squares
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 *
 * A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 *
 * Constraints:
 *
 * 1 <= n <= 104
 */
public class NumSquares {

    List<Integer> squares;
    Integer[][] dp;

    public int numSquares(int n) {
        int sqrt = (int) Math.sqrt(n);
        if (sqrt * sqrt == n) return 1;
        squares = new ArrayList<>();
        for (int i = 1; i <= sqrt; i++) {
            squares.add(i * i);
        }
        dp = new Integer[squares.size()][n + 1];
        return rec(squares.size() - 1, n);
    }

    private int rec(int i, int n) {
        if (n == 0) return 0;
        if (i < 0) return Integer.MAX_VALUE / 2;
        if (n < squares.get(i)) return rec(i - 1, n);
        if (dp[i][n] != null) return dp[i][n];
        int min = Integer.MAX_VALUE / 2;
        for (int j = i; j >= 0; j--) {
            min = Math.min(min, 1 + rec(j, n - squares.get(j)));
        }
        return dp[i][n] = min;
    }

    public static void main(String[] args) {
        NumSquares n = new NumSquares();
        System.out.println(n.numSquares(12));
    }

}
