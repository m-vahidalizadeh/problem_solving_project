package leetcode.hard;

import java.util.Arrays;

/**
 * 1542. Find Longest Awesome Substring
 * Given a string s. An awesome substring is a non-empty substring of s such that we can make any number of swaps in order to make it palindrome.
 *
 * Return the length of the maximum length awesome substring of s.
 *
 * Example 1:
 *
 * Input: s = "3242415"
 * Output: 5
 * Explanation: "24241" is the longest awesome substring, we can form the palindrome "24142" with some swaps.
 * Example 2:
 *
 * Input: s = "12345678"
 * Output: 1
 * Example 3:
 *
 * Input: s = "213123"
 * Output: 6
 * Explanation: "213123" is the longest awesome substring, we can form the palindrome "231132" with some swaps.
 * Example 4:
 *
 * Input: s = "00"
 * Output: 2
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists only of digits.
 */
public class FindLongestAwesomeSubstring {

    public int longestAwesome(String s) {
        int mask = 0;
        int[] dp = new int[1024];
        int len = s.length();
        Arrays.fill(dp, len);
        int res = 0;
        dp[0] = -1; // For index 0, the length should be 1
        for (int i = 0; i < len; i++) {
            mask ^= (1 << (Character.getNumericValue(s.charAt(i))));
            res = Math.max(res, i - dp[mask]); // All digits frequencies are even
            for (int j = 0; j <= 9; j++)
                res = Math.max(res, i - dp[mask ^ (1 << j)]); // One digit frequency is odd the rest even
            dp[mask] = Math.min(dp[mask], i);
        }
        return res;
    }

}
