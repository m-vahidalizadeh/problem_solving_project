package leetcode.companies.facebook;

/**
 * 10. Regular Expression Matching
 * Solved
 * Hard
 *
 * Topics
 *
 * Companies
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 *
 * '.' Matches any single character.​​​​
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
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * 1 <= p.length <= 20
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */
public class IsMatchPattern {

    public boolean isMatch(String s, String p) {
        return helper(s, p, 0, 0, new Boolean[s.length() + 1][p.length() + 1], s.length(), p.length());
    }

    private boolean helper(String s, String p, int si, int pi, Boolean[][] dp, int sn, int pn) {
        if (si >= sn && pi >= pn) return dp[si][pi] = true;
        if (pi >= pn) return dp[si][pi] = false;
        if (dp[si][pi] != null) return dp[si][pi];
        boolean match = si < sn && ((s.charAt(si) == p.charAt(pi)) || (p.charAt(pi) == '.'));
        if (pi + 1 < pn && p.charAt(pi + 1) == '*')
            return dp[si][pi] = helper(s, p, si, pi + 2, dp, sn, pn) || (match && helper(s, p, si + 1, pi, dp, sn, pn));
        return match && helper(s, p, si + 1, pi + 1, dp, sn, pn);
    }

}
