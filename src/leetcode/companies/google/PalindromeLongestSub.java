package leetcode.companies.google;

/**
 * 5. Longest Palindromic Substring
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 * Example 3:
 *
 * Input: s = "a"
 * Output: "a"
 * Example 4:
 *
 * Input: s = "ac"
 * Output: "a"
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters (lower-case and/or upper-case),
 */
public class PalindromeLongestSub {

    int n;

    public String longestPalindrome(String s) {
        n = s.length();
        int[] maxSub = new int[]{0, 0};
        for (int i = 0; i < n; i++) {
            int[] res = getMaxPal(s, i, i);
            if (res[1] - res[0] > maxSub[1] - maxSub[0]) maxSub = res;
            if (i < n - 1) {
                res = getMaxPal(s, i, i + 1);
                if (res[1] - res[0] > maxSub[1] - maxSub[0]) maxSub = res;
            }
        }
        return s.substring(maxSub[0], maxSub[1] + 1);
    }

    private int[] getMaxPal(String s, int l, int r) {
        int[] res = new int[]{-1, -1};
        while (true) {
            if (s.charAt(l) == s.charAt(r)) res = new int[]{l, r};
            else break;
            l--;
            r++;
            if (l < 0 || r >= n) break;
        }
        return res;
    }

}
