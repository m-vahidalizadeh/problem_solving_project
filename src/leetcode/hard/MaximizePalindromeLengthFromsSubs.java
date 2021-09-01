package leetcode.hard;

/**
 * 1771. Maximize Palindrome Length From Subsequences
 * You are given two strings, word1 and word2. You want to construct a string in the following manner:
 *
 * Choose some non-empty subsequence subsequence1 from word1.
 * Choose some non-empty subsequence subsequence2 from word2.
 * Concatenate the subsequences: subsequence1 + subsequence2, to make the string.
 * Return the length of the longest palindrome that can be constructed in the described manner. If no palindromes can be constructed, return 0.
 *
 * A subsequence of a string s is a string that can be made by deleting some (possibly none) characters from s without changing the order of the remaining characters.
 *
 * A palindrome is a string that reads the same forward as well as backward.
 *
 * Example 1:
 *
 * Input: word1 = "cacb", word2 = "cbba"
 * Output: 5
 * Explanation: Choose "ab" from word1 and "cba" from word2 to make "abcba", which is a palindrome.
 * Example 2:
 *
 * Input: word1 = "ab", word2 = "ab"
 * Output: 3
 * Explanation: Choose "ab" from word1 and "a" from word2 to make "aba", which is a palindrome.
 * Example 3:
 *
 * Input: word1 = "aa", word2 = "bb"
 * Output: 0
 * Explanation: You cannot construct a palindrome from the described method, so return 0.
 *
 * Constraints:
 *
 * 1 <= word1.length, word2.length <= 1000
 * word1 and word2 consist of lowercase English letters.
 */
public class MaximizePalindromeLengthFromsSubs {

    public int longestPalindrome(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        String word = word1 + word2;
        int n = word.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (word.charAt(i) == word.charAt(j)) {
                    if (j - i < 2) dp[i][j] = j - i + 1;
                    else dp[i][j] = 2 + dp[i + 1][j - 1];
                } else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        int res = 0;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (word1.charAt(i) == word2.charAt(j)) res = Math.max(res, 2 + dp[i + 1][n1 + j - 1]);
            }
        }
        return res;
    }

}
