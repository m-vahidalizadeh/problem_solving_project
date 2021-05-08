package leetcode.hard;

/**
 * 1444. Number of Ways of Cutting a Pizza
 * Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts.
 *
 * For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.
 *
 * Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.
 *
 * Example 1:
 *
 * Input: pizza = ["A..","AAA","..."], k = 3
 * Output: 3
 * Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
 * Example 2:
 *
 * Input: pizza = ["A..","AA.","..."], k = 3
 * Output: 1
 * Example 3:
 *
 * Input: pizza = ["A..","A..","..."], k = 1
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= rows, cols <= 50
 * rows == pizza.length
 * cols == pizza[i].length
 * 1 <= k <= 10
 * pizza consists of characters 'A' and '.' only.
 */
public class CuttingApplePizza {

    Long[][][] cache;
    int[][] sum;
    int n;
    int m;
    int mod;

    public int ways(String[] pizza, int k) {
        n = pizza.length;
        m = pizza[0].length();
        cache = new Long[n + 1][m + 1][k];
        mod = 1_000_000_007;
        sum = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                sum[i][j] = sum[i][j + 1] + sum[i + 1][j] - sum[i + 1][j + 1] + (pizza[i].charAt(j) == 'A' ? 1 : 0);
            }
        }
        return (int) (rec(0, 0, k - 1) % mod);
    }

    private long rec(int currI, int currJ, int k) {
        if (cache[currI][currJ][k] != null) return cache[currI][currJ][k];
        if (sum[currI][currJ] == 0) return 0;
        if (k == 0) return 1;
        long res = 0;
        for (int i = currI + 1; i < n; i++) {
            if (sum[currI][currJ] - sum[i][currJ] > 0) res += rec(i, currJ, k - 1);
        }
        for (int j = currJ + 1; j < m; j++) {
            if (sum[currI][currJ] - sum[currI][j] > 0) res += rec(currI, j, k - 1);
        }
        cache[currI][currJ][k] = res;
        return res;
    }

}
