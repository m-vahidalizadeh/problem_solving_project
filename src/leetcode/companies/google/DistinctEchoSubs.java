package leetcode.companies.google;

import java.util.HashSet;
import java.util.Set;

/**
 * Return the number of distinct non-empty substrings of text that can be written as the concatenation of some string with itself (i.e. it can be written as a + a where a is some string).
 *
 * Example 1:
 *
 * Input: text = "abcabcabc"
 * Output: 3
 * Explanation: The 3 substrings are "abcabc", "bcabca" and "cabcab".
 * Example 2:
 *
 * Input: text = "leetcodeleetcode"
 * Output: 2
 * Explanation: The 2 substrings are "ee" and "leetcodeleetcode".
 *
 * Constraints:
 *
 * 1 <= text.length <= 2000
 * text has only lowercase English letters.
 */
public class DistinctEchoSubs {

    public int distinctEchoSubstrings(String text) {
        int n = text.length();
        int[][] dp = new int[n][n];
        int prime = 101;
        int mod = 1_000_000_007;
        for (int i = 0; i < n; i++) {
            long hash = 0;
            for (int j = i; j < n; j++) {
                hash = (hash * prime + text.charAt(j) - 'a' + 1) % mod;
                dp[i][j] = (int) hash;
            }
        }
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i; j + 1 + j - i < n; j++) {
                if (dp[i][j] == dp[j + 1][j + 1 + j - i]) res.add(dp[i][j]);
            }
        }
        return res.size();
    }

}
