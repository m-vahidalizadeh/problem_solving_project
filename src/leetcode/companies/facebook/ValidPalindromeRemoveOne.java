package leetcode.companies.facebook;

/**
 * 680. Valid Palindrome II
 * Solved
 * Easy
 *
 * Topics
 *
 * Companies
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 *
 * Example 1:
 *
 * Input: s = "aba"
 * Output: true
 * Example 2:
 *
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * Example 3:
 *
 * Input: s = "abc"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 */
public class ValidPalindromeRemoveOne {

    public boolean validPalindrome(String s) {
        if (s.length() <= 1) return true;
        return isPal(s, 0, s.length() - 1, true);
    }

    private boolean isPal(String s, int i, int j, boolean canRemove) {
        if (i > j) return true;
        if (s.charAt(i) == s.charAt(j)) return isPal(s, i + 1, j - 1, canRemove);
        else if (canRemove) return isPal(s, i + 1, j, false) || isPal(s, i, j - 1, false);
        else return false;
    }

}
