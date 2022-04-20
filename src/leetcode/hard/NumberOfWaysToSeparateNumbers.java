package leetcode.hard;

/**
 * 1977. Number of Ways to Separate Numbers
 * You wrote down many positive integers in a string called num. However, you realized that you forgot to add commas to seperate the different numbers. You remember that the list of integers was non-decreasing and that no integer had leading zeros.
 *
 * Return the number of possible lists of integers that you could have written down to get the string num. Since the answer may be large, return it modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: num = "327"
 * Output: 2
 * Explanation: You could have written down the numbers:
 * 3, 27
 * 327
 * Example 2:
 *
 * Input: num = "094"
 * Output: 0
 * Explanation: No numbers can have leading zeros and all numbers must be positive.
 * Example 3:
 *
 * Input: num = "0"
 * Output: 0
 * Explanation: No numbers can have leading zeros and all numbers must be positive.
 *
 * Constraints:
 *
 * 1 <= num.length <= 3500
 * num consists of digits '0' through '9'.
 */
public class NumberOfWaysToSeparateNumbers {

    int[][] lcp;
    long[][] dp;
    long[][] dps;
    long mod = 1_000_000_007;
    String num;
    int n;

    void calcLCP() {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (num.charAt(i) == num.charAt(j)) lcp[i][j] = lcp[i + 1][j + 1] + 1;
            }
        }
    }

    boolean compare(int i, int j, int len) {
        int common = lcp[i][j];
        if (common >= len) return true;
        if (num.charAt(i + common) < num.charAt(j + common)) return true;
        return false;
    }

    void calcResult() {
        for (int i = n - 1; i >= 0; i--) {
            if (num.charAt(i) == '0') continue; // leading zero
            long sum = 0;
            for (int j = n - 1; j >= i; j--) {
                if (j == n - 1) dp[i][j] = 1;
                else {
                    int len = j - i + 1;
                    int st = j + 1; // Start of second
                    int ed = st + len - 1; // End of second
                    dp[i][j] = 0;
                    if (ed < n && compare(i, st, len)) dp[i][j] = dp[st][ed];
                    if (ed + 1 < n) dp[i][j] = (dp[i][j] + dps[st][ed + 1]) % mod; // Second int longer than first one
                }
                dps[i][j] = sum = (sum + dp[i][j]) % mod;
            }
        }
    }

    public int numberOfCombinations(String num) {
        if (num.charAt(0) == '0') return 0;
        n = num.length();
        this.num = num;
        lcp = new int[n + 1][n + 1];
        dp = new long[n + 1][n + 1];
        dps = new long[n + 1][n + 1];
        calcLCP();
        calcResult();
        return (int) dps[0][0];
    }

}
