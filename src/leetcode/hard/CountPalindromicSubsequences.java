package leetcode.hard;

/**
 * 2484. Count Palindromic Subsequences
 * Given a string of digits s, return the number of palindromic subsequences of s having length 5. Since the answer may be very large, return it modulo 109 + 7.
 *
 * Note:
 *
 * A string is palindromic if it reads the same forward and backward.
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 *
 * Example 1:
 *
 * Input: s = "103301"
 * Output: 2
 * Explanation:
 * There are 6 possible subsequences of length 5: "10330","10331","10301","10301","13301","03301".
 * Two of them (both equal to "10301") are palindromic.
 * Example 2:
 *
 * Input: s = "0000000"
 * Output: 21
 * Explanation: All 21 subsequences are "00000", which is palindromic.
 * Example 3:
 *
 * Input: s = "9999900000"
 * Output: 2
 * Explanation: The only two palindromic subsequences are "99999" and "00000".
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of digits.
 */
public class CountPalindromicSubsequences {

    public int countPalindromes(String s) {
        final int mod = 1_000_000_007;
        long ans = 0;
        for (int x = 0; x <= 9; x++) {
            for (int y = 0; y <= 9; y++) {
                int[] pattern = new int[]{x, y, 0, y, x};
                long[] dp = new long[6];
                dp[5] = 1;
                for (int i = 0; i < s.length(); i++)
                    for (int j = 0; j < 5; j++)
                        if (s.charAt(i) == pattern[j] + '0' || j == 2) dp[j] = (dp[j] + dp[j + 1]) % mod;
                ans = (ans + dp[0]) % mod;
            }
        }
        return (int) ans;
    }

}
