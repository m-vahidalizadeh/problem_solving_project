package leetcode.companies.facebook;

/**
 * 125. Valid Palindrome
 * Solved
 * Easy
 *
 * Topics
 *
 * Companies
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
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
 * Example 3:
 *
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 *
 * Constraints:
 *
 * 1 <= s.length <= 2 * 10^5
 * s consists only of printable ASCII characters.
 */
public class IsPalindromeAlphaNumeric {

    public boolean isPalindrome(String s) {
        if (s.isBlank()) return true;
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            while (i < s.length() && !Character.isAlphabetic(s.charAt(i)) && !Character.isDigit(s.charAt(i))) i++;
            while (j >= 0 && !Character.isAlphabetic(s.charAt(j)) && !Character.isDigit(s.charAt(j))) j--;
            if (i == s.length() && j == -1) return true;
            if (Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j))) {
                i++;
                j--;
            } else break;
        }
        return i > j;
    }

}
