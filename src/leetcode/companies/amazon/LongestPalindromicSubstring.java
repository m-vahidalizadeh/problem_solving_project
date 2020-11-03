package leetcode.companies.amazon;

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
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        String result = "";
        int size = 0;
        if (s.isBlank()) return result;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            String opt1 = expand(s, i, i + 1, n);
            String opt2 = expand(s, i, i, n);
            int s1 = opt1.length();
            int s2 = opt2.length();
            if (s1 > s2) {
                if (s1 > size) {
                    size = s1;
                    result = opt1;
                }
            } else {
                if (s2 > size) {
                    size = s2;
                    result = opt2;
                }
            }
        }
        return result;
    }

    private String expand(String s, int l, int r, int n) {
        while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring l = new LongestPalindromicSubstring();
        System.out.println(l.longestPalindrome("babad"));
        System.out.println(l.longestPalindrome("cbbd"));
        System.out.println(l.longestPalindrome("a"));
        System.out.println(l.longestPalindrome("ac"));
    }

}
