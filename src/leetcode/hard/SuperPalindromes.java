package leetcode.hard;

/**
 * 906. Super Palindromes
 * Let's say a positive integer is a super-palindrome if it is a palindrome, and it is also the square of a palindrome.
 *
 * Given two positive integers left and right represented as strings, return the number of super-palindromes integers in the inclusive range [left, right].
 *
 * Example 1:
 *
 * Input: left = "4", right = "1000"
 * Output: 4
 * Explanation: 4, 9, 121, and 484 are superpalindromes.
 * Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.
 * Example 2:
 *
 * Input: left = "1", right = "2"
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= left.length, right.length <= 18
 * left and right consist of only digits.
 * left and right cannot have leading zeros.
 * left and right represent integers in the range [1, 1018 - 1].
 * left is less than or equal to right.
 */
public class SuperPalindromes {

    public int superpalindromesInRange(String left, String right) {
        int count = 0;
        long l = Long.parseLong(left);
        long r = Long.parseLong(right);
        for (int i = 0; i < 100_000; i++) { // Odd case 12321 10^18-> sqrt -> 10^9 -> palindrome half+half -> 10^5
            String s1 = i + "";
            String s2 = new StringBuilder(s1.substring(0, s1.length() - 1)).reverse().toString();
            String s = s1 + s2;
            long num = Long.parseLong(s) * Long.parseLong(s);
            if (num > r) break;
            if (l <= num && (num + "").equals((new StringBuilder(num + "")).reverse().toString())) count++;
        }
        for (int i = 0; i < 100_000; i++) { // Even case 1221
            String s1 = i + "";
            String s2 = new StringBuilder(s1).reverse().toString();
            String s = s1 + s2;
            long num = Long.parseLong(s) * Long.parseLong(s);
            if (num > r) break;
            if (l <= num && (num + "").equals((new StringBuilder(num + "")).reverse().toString())) count++;
        }
        return count;
    }

}
