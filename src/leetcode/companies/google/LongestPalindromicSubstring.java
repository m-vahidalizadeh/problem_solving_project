package leetcode.companies.google;

/**
 * Longest Palindromic Substring
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * <p>
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        String max = "";
        if (s == null) return max;
        int n = s.length();
        if (n == 0) return max;
        for (int i = 0; i < n; i++) {
            int l = i;
            int r = i;
            while (true) {
                if (l - 1 >= 0 && r + 1 < n && s.charAt(l - 1) == s.charAt(r + 1)) {
                    l--;
                    r++;
                } else break;
            }
            if (r - l + 1 > max.length()) max = s.substring(l, r + 1);
            l = i;
            r = i + 1;
            if (r < n && s.charAt(l) == s.charAt(r)) {
                while (true) {
                    if (l - 1 >= 0 && r + 1 < n && s.charAt(l - 1) == s.charAt(r + 1)) {
                        l--;
                        r++;
                    } else break;
                }
                if (r - l + 1 > max.length()) max = s.substring(l, r + 1);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring l = new LongestPalindromicSubstring();
        System.out.println(l.longestPalindrome("babad"));
        System.out.println(l.longestPalindrome("cbbd"));
        System.out.println(l.longestPalindrome("aaabaaaa"));
    }

}
