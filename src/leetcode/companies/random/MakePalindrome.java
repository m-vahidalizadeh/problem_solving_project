package leetcode.companies.random;

/**
 * Valid Palindrome II
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: "aba"
 * Output: True
 * Example 2:
 * <p>
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * <p>
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class MakePalindrome {

    public boolean validPalindrome(String s) {
        return isPalindrome(s, 0, s.length() - 1, 1, s.length());
    }

    private boolean isPalindrome(String s, int a, int b, int rem, int n) {
        if (a >= b) return true;
        if (s.charAt(a) != s.charAt(b)) {
            if (rem == 1) {
                return isPalindrome(s.substring(0, a) + s.substring(a + 1, n), 0, n - 2, 0, n)
                        ||
                        isPalindrome(s.substring(0, b) + s.substring(b + 1, n), 0, n - 2, 0, n);
            } else {
                return false;
            }
        } else {
            return isPalindrome(s, a + 1, b - 1, rem, n);
        }
    }

    public static void main(String[] args) {
        MakePalindrome m = new MakePalindrome();
        System.out.println(m.validPalindrome("aba"));
        System.out.println(m.validPalindrome("kbca"));
    }

}
