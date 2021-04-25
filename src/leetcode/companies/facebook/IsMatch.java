package leetcode.companies.facebook;

/**
 * 10. Regular Expression Matching
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where:
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Example 1:
 *
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 *
 * Input: s = "aab", p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 * Example 5:
 *
 * Input: s = "mississippi", p = "mis*is*p*."
 * Output: false
 *
 * Constraints:
 *
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */
public class IsMatch {

    Boolean[][] dp;
    int sN;
    int pN;
    String s;
    String p;

    public boolean isMatch(String s, String p) {
        this.sN = s.length();
        this.pN = p.length();
        this.dp = new Boolean[this.sN + 1][this.pN + 1]; // We use this for the caching
        this.s = s;
        this.p = p;
        return rec(0, 0); // Start with both indexes point at 0
    }

    private boolean rec(int sI, int pI) {
        if (sI >= sN && pI >= pN) return dp[sI][pI] = true; // Matching is done successfully
        if (pI >= pN) return dp[sI][pI] = false; // Pattern index is at the end but string index is not-not a match
        if (dp[sI][pI] != null) return dp[sI][pI]; // Does the result exist in the cache?
        boolean match = sI < sN && ((s.charAt(sI) == p.charAt(pI)) || (p.charAt(pI) == '.')); // Check curr chars
        boolean result = false;
        if (pI + 1 < pN && p.charAt(pI + 1) == '*') { // If next char in pattern is *
            result = (rec(sI, pI + 2)) || (match && rec(sI + 1, pI)); // Don't use * || Use star
        } else if (match) { // If the curr is a match
            result = rec(sI + 1, pI + 1); // check the rest
        }
        return dp[sI][pI] = result; // Cache the result and return
    }

}
