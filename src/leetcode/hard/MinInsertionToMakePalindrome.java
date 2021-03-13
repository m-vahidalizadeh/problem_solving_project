package leetcode.hard;

import java.util.Arrays;

/**
 * 1312. Minimum Insertion Steps to Make a String Palindrome
 * Given a string s. In one step you can insert any character at any index of the string.
 *
 * Return the minimum number of steps to make s palindrome.
 *
 * A Palindrome String is one that reads the same backward as well as forward.
 *
 * Example 1:
 *
 * Input: s = "zzazz"
 * Output: 0
 * Explanation: The string "zzazz" is already palindrome we don't need any insertions.
 * Example 2:
 *
 * Input: s = "mbadm"
 * Output: 2
 * Explanation: String can be "mbdadbm" or "mdbabdm".
 * Example 3:
 *
 * Input: s = "leetcode"
 * Output: 5
 * Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 * Example 4:
 *
 * Input: s = "g"
 * Output: 0
 * Example 5:
 *
 * Input: s = "no"
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * All characters of s are lower case English letters.
 */
public class MinInsertionToMakePalindrome {

    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        for (int i = n - 1; 0 <= i; i--) {
            for (int j = i; j <= n - 1; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 2) dp[i][j] = 0;
                    else dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public int minInsertions2(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; 0 <= i; i--) {
            for (int j = i; j <= n - 1; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 2) dp[i][j] = j - i + 1;
                    else dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return n - dp[0][n - 1];
    }

}
