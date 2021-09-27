package leetcode.easy;

/**
 * 125. Valid Palindrome
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
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            while (start < end && !(
                    Character.isDigit(s.charAt(start)) ||
                            Character.isAlphabetic(s.charAt(start))
            )) start++;
            while (start < end && !(
                    Character.isAlphabetic(s.charAt(end)) ||
                            Character.isDigit(s.charAt(end))
            )) end--;
            char cS = s.charAt(start);
            char cE = s.charAt(end);
            if (start < end && (
                    Character.isAlphabetic(cS) ||
                            Character.isAlphabetic(cE) ||
                            Character.isDigit(cS) ||
                            Character.isDigit(cE)
            )) {
                if (cS != cE) return false;
            }
            start++;
            end--;
        }
        return true;
    }

}
