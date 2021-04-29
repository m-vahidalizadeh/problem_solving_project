package leetcode.companies.facebook;

/**
 * Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Example 1:
 *
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * Example 2:
 *
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 *
 * Constraints:
 *
 * 1 <= s.length <= 2 * 105
 * s consists only of printable ASCII characters.
 */
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int n = s.length();
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            while (l < n && !('a' <= s.charAt(l) && s.charAt(l) <= 'z') && !('0' <= s.charAt(l) && s.charAt(l) <= '9'))
                l++;
            while (0 <= r && !('a' <= s.charAt(r) && s.charAt(r) <= 'z') && !('0' <= s.charAt(r) && s.charAt(r) <= '9'))
                r--;
            if (l == n && r == -1) return true;
            if (s.charAt(l) != s.charAt(r)) return false;
            r--;
            l++;
        }
        return true;
    }

}
