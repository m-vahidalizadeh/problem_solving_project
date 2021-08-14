package leetcode.hard;

/**
 * 1960. Maximum Product of the Length of Two Palindromic Substrings
 * You are given a 0-indexed string s and are tasked with finding two non-intersecting palindromic substrings of odd length such that the product of their lengths is maximized.
 *
 * More formally, you want to choose four integers i, j, k, l such that 0 <= i <= j < k <= l < s.length and both the substrings s[i...j] and s[k...l] are palindromes and have odd lengths. s[i...j] denotes a substring from index i to index j inclusive.
 *
 * Return the maximum possible product of the lengths of the two non-intersecting palindromic substrings.
 *
 * A palindrome is a string that is the same forward and backward. A substring is a contiguous sequence of characters in a string.
 *
 * Example 1:
 *
 * Input: s = "ababbb"
 * Output: 9
 * Explanation: Substrings "aba" and "bbb" are palindromes with odd length. product = 3 * 3 = 9.
 * Example 2:
 *
 * Input: s = "zaaaxbbby"
 * Output: 9
 * Explanation: Substrings "aaa" and "bbb" are palindromes with odd length. product = 3 * 3 = 9.
 *
 * Constraints:
 *
 * 2 <= s.length <= 105
 * s consists of lowercase English letters.
 */
public class MaxProduct {

    public long maxProduct(String s) {
        int n = s.length();
        if (n == 2) return 1;
        int[] mana = manachers(s);
        long[] left = new long[n];
        int max = 1;
        left[0] = max;
        for (int i = 1; i < n; i++) {
            if (mana[i - (max + 1) / 2] > max) max += 2;
            left[i] = max;
        }
        max = 1;
        long[] right = new long[n];
        right[n - 1] = max;
        for (int i = n - 2; i >= 0; i--) {
            if (mana[i + (max + 1) / 2] > max) max += 2;
            right[i] = max;
        }
        long res = 1;
        for (int i = 1; i < n; i++) res = Math.max(res, left[i - 1] * right[i]);
        return res;
    }

    private int[] manachers(String s) {
        int len = s.length();
        int[] p = new int[len];
        int c = 0;
        int r = 0;
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            int mirror = 2 * c - i;
            if (i < r) p[i] = Math.min(r - i, p[mirror]);
            int end = i + (1 + p[i]);
            int start = i - (1 + p[i]);
            while (end < len && start >= 0 && s.charAt(end) == s.charAt(start)) {
                p[i]++;
                end++;
                start--;
            }
            if (i + p[i] > r) {
                c = i;
                r = i + p[i];
            }
        }
        for (int i = 0; i < len; i++) p[i] = 2 * p[i] + 1;
        return p;
    }

}
