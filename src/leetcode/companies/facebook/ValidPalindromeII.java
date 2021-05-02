package leetcode.companies.facebook;

/**
 * 680. Valid Palindrome II
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 *
 * Input: "aba"
 * Output: True
 * Example 2:
 *
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 *
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class ValidPalindromeII {

    public boolean validPalindrome(String s) {
        int n = s.length();
        for (int i = 0, j = n - 1; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int l, int r) {
        for (int i = l, j = r; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

}
