package leetcode.hard;

/**
 * 664. Strange Printer
 * There is a strange printer with the following two special requirements:
 *
 * The printer can only print a sequence of the same character each time.
 * At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
 * Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.
 *
 * Example 1:
 *
 * Input: "aaabbb"
 * Output: 2
 * Explanation: Print "aaa" first and then print "bbb".
 * Example 2:
 *
 * Input: "aba"
 * Output: 2
 * Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 * Hint: Length of the given string will not exceed 100.
 */
public class StrangePrinter {

    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        return rec(s, dp, 0, n - 1);
    }

    private int rec(String s, int[][] dp, int start, int end) {
        if (start > end) return 0;
        if (start == end) {
            dp[start][start] = 1;
            return 1;
        }
        if (dp[start][end] != 0) return dp[start][end];
        dp[start][end] = 1 + rec(s, dp, start + 1, end);
        for (int i = 0; i <= end; i++) {
            if (s.charAt(start) == s.charAt(i))
                dp[start][end] = Math.min(dp[start][end], rec(s, dp, start + 1, i - 1) + rec(s, dp, i, end));
        }
        return dp[start][end];
    }

}
