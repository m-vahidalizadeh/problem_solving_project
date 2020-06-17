package leetcode.companies.random;

import java.util.Set;

/**
 * Valid Palindrome
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * <p>
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 * <p>
 * Input: "race a car"
 * Output: false
 */
public class IsPalindrome {

    Set<Character> nums = Set.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    public boolean isPalindrome(String s) {
        int n = s.length();
        int a = 0;
        int b = n - 1;
        while (a <= b) {
            while (a < n && !Character.isLetter(s.charAt(a)) && !nums.contains(s.charAt(a))) a++;
            while (0 <= b && !Character.isLetter(s.charAt(b)) && !nums.contains(s.charAt(b))) b--;
            if (a == b) return true;
            else if (a < b && Character.toLowerCase(s.charAt(a)) != Character.toLowerCase(s.charAt(b))) return false;
            else {
                a++;
                b--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsPalindrome i = new IsPalindrome();
        System.out.println(i.isPalindrome("0P"));
    }

}
