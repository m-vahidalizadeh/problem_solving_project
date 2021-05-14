package leetcode.hard;

/**
 * 1092. Shortest Common Supersequence
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  If multiple answers exist, you may return any of them.
 *
 * (A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.)
 *
 * Example 1:
 *
 * Input: str1 = "abac", str2 = "cab"
 * Output: "cabac"
 * Explanation:
 * str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
 * str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
 * The answer provided is the shortest such string that satisfies these properties.
 *
 * Note:
 *
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of lowercase English letters.
 */
public class ShortestCommonSupersequence {

    public String shortestCommonSupersequence(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        char[] lcs = lcs(s1, s2).toCharArray();
        StringBuilder res = new StringBuilder();
        int i = 0;
        int j = 0;
        for (char c : lcs) {
            while (i < s1.length && s1[i] != c) res.append(s1[i++]);
            while (j < s2.length && s2[j] != c) res.append(s2[j++]);
            res.append(c);
            i++;
            j++;
        }
        while (i < s1.length) res.append(s1[i++]);
        while (j < s2.length) res.append(s2[j++]);
        return res.toString();
    }

    private String lcs(char[] s1, char[] s2) {
        String[][] dp = new String[s1.length + 1][s2.length + 1];
        for (int i = 0; i <= s1.length; i++) dp[i][0] = "";
        for (int j = 0; j <= s2.length; j++) dp[0][j] = "";
        for (int i = 1; i <= s1.length; i++) {
            for (int j = 1; j <= s2.length; j++) {
                if (s1[i - 1] == s2[j - 1]) dp[i][j] = dp[i - 1][j - 1] == null ? "" : dp[i - 1][j - 1] + s1[i - 1];
                else dp[i][j] = dp[i][j - 1].length() > dp[i - 1][j].length() ? dp[i][j - 1] : dp[i - 1][j];
            }
        }
        return dp[s1.length][s2.length];
    }

}
