package leetcode.hard;

/**
 * 730. Count Different Palindromic Subsequences
 * Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.
 *
 * A subsequence of a string S is obtained by deleting 0 or more characters from S.
 *
 * A sequence is palindromic if it is equal to the sequence reversed.
 *
 * Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.
 *
 * Example 1:
 *
 * Input:
 * S = 'bccb'
 * Output: 6
 * Explanation:
 * The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
 * Note that 'bcb' is counted only once, even though it occurs twice.
 * Example 2:
 *
 * Input:
 * S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
 * Output: 104860361
 * Explanation:
 * There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
 * Note:
 *
 * The length of S will be in the range [1, 1000].
 * Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.
 */
public class CountPalindromicSubs {

    public int countPalindromicSubsequences(String S) {
        int n = S.length();
        if (n < 2) return n;
        int modulo = 1_000_000_007;
        long[][][] dp = new long[4][n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    char c = (char) ('a' + k);
                    if (i == j) dp[k][i][j] = S.charAt(i) == c ? 1 : 0;
                    else {
                        if (S.charAt(i) != c) dp[k][i][j] = dp[k][i + 1][j];
                        else if (S.charAt(j) != c) dp[k][i][j] = dp[k][i][j - 1];
                        else {
                            if (i + 1 == j) dp[k][i][j] = 2;
                            else {
                                dp[k][i][j] = 2;
                                for (int m = 0; m < 4; m++) {
                                    dp[k][i][j] += dp[m][i + 1][j - 1];
                                    dp[k][i][j] %= modulo;
                                }
                            }
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 4; i++) {
            res += dp[i][0][n - 1];
            res %= modulo;
        }
        res %= modulo;
        return res;
    }

    public static void main(String[] args) {
        CountPalindromicSubs c = new CountPalindromicSubs();
        System.out.println(c.countPalindromicSubsequences("bddaabdbbccdcdcbbdbddccbaaccabbcacbadbdadbccddccdbdbdbdabdbddcccadddaaddbcbcbabdcaccaacabdbdaccbaacc"));
    }

}
