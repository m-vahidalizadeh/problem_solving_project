package leetcode.hard;

/**
 * 1216. Valid Palindrome III
 * Given a string s and an integer k, return true if s is a k-palindrome.
 *
 * A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 *
 * Example 1:
 *
 * Input: s = "abcdeca", k = 2
 * Output: true
 * Explanation: Remove 'b' and 'e' characters.
 * Example 2:
 *
 * Input: s = "abbababa", k = 1
 * Output: true
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of only lowercase English letters.
 * 1 <= k <= s.length
 */
public class ValidPalindromeIII {

    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) dp[i][j] = j - i < 2 ? 0 : dp[i + 1][j - 1];
                else dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
            }
        }
        return dp[0][n - 1] <= k;
    }

}
