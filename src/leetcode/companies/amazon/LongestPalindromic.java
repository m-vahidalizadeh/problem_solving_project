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
public class LongestPalindromic {

    public String longestPalindrome(String s) {
        int n = s.length();
        int[] max = new int[]{0, 0};
        for (int i = 0; i < n; i++) {
            int[] result = getMaxPal(s, i, i, n);
            if (result[1] - result[0] > max[1] - max[0]) max = result;
            if (i < n - 1) result = getMaxPal(s, i, i + 1, n);
            if (result[1] - result[0] > max[1] - max[0]) max = result;
        }
        return s.substring(max[0], max[1] + 1);
    }

    private int[] getMaxPal(String s, int l, int r, int n) {
        int[] res = new int[]{-1, -1};
        while (true) {
            if (s.charAt(l) == s.charAt(r)) {
                res[0] = l;
                res[1] = r;
            } else break;
            l--;
            r++;
            if (l < 0 || r >= n) break;
        }
        return res;
    }

    public static void main(String[] args) {
        LongestPalindromic l = new LongestPalindromic();
        System.out.println(l.longestPalindrome("ac"));
    }

}
