package leetcode.hard;

/**
 * 479. Largest Palindrome Product
 * Given an integer n, return the largest palindromic integer that can be represented as the product of two n-digits integers. Since the answer can be very large, return it modulo 1337.
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 987
 * Explanation: 99 x 91 = 9009, 9009 % 1337 = 987
 * Example 2:
 *
 * Input: n = 1
 * Output: 9
 *
 * Constraints:
 *
 * 1 <= n <= 8
 * Accepted
 * 18,877
 * Submissions
 * 62,572
 */
public class LargestPalindromeProduct {

    public int largestPalindrome(int n) {
        if (n == 1) return 9;
        long max = (long) Math.pow(10, n) - 1; // n digits * n digits = 2n-1 or 2n digits
        long min = max / 10;
        for (long left = max; left > min; left--) {
            String right = new StringBuilder(left + "").reverse().toString();
            String palindromeString = left + right;
            long palindrome = Long.parseLong(palindromeString);// make a palindrome from n digits + reverse(n digits)
            for (long i = max; i > min; i--) { // check if you can write it as n digits * n digits
                long j = palindrome / i;
                if (j > i) break;
                if (palindrome % i == 0) return (int) (palindrome % 1337);
            }
        }
        return -1;
    }

}
