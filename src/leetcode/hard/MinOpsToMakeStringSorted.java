package leetcode.hard;

/**
 * 1830. Minimum Number of Operations to Make String Sorted
 * You are given a string s (0-indexed). You are asked to perform the following operation on s until you get a sorted string:
 *
 * Find the largest index i such that 1 <= i < s.length and s[i] < s[i - 1].
 * Find the largest index j such that i <= j < s.length and s[k] < s[i - 1] for all the possible values of k in the range [i, j] inclusive.
 * Swap the two characters at indices i - 1 and j.
 * Reverse the suffix starting at index i.
 * Return the number of operations needed to make the string sorted. Since the answer can be too large, return it modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: s = "cba"
 * Output: 5
 * Explanation: The simulation goes as follows:
 * Operation 1: i=2, j=2. Swap s[1] and s[2] to get s="cab", then reverse the suffix starting at 2. Now, s="cab".
 * Operation 2: i=1, j=2. Swap s[0] and s[2] to get s="bac", then reverse the suffix starting at 1. Now, s="bca".
 * Operation 3: i=2, j=2. Swap s[1] and s[2] to get s="bac", then reverse the suffix starting at 2. Now, s="bac".
 * Operation 4: i=1, j=1. Swap s[0] and s[1] to get s="abc", then reverse the suffix starting at 1. Now, s="acb".
 * Operation 5: i=2, j=2. Swap s[1] and s[2] to get s="abc", then reverse the suffix starting at 2. Now, s="abc".
 * Example 2:
 *
 * Input: s = "aabaa"
 * Output: 2
 * Explanation: The simulation goes as follows:
 * Operation 1: i=3, j=4. Swap s[2] and s[4] to get s="aaaab", then reverse the substring starting at 3. Now, s="aaaba".
 * Operation 2: i=4, j=4. Swap s[3] and s[4] to get s="aaaab", then reverse the substring starting at 4. Now, s="aaaab".
 * Example 3:
 *
 * Input: s = "cdbea"
 * Output: 63
 * Example 4:
 *
 * Input: s = "leetcodeleetcodeleetcode"
 * Output: 982157772
 *
 * Constraints:
 *
 * 1 <= s.length <= 3000
 * s consists only of lowercase English letters.
 */
public class MinOpsToMakeStringSorted {

    int MOD = 1_000_000_007;

    public int makeStringSorted(String s) {
        int n = s.length();
        long[] fact = new long[n];
        long[] invFact = new long[n];
        fact[0] = 1;
        invFact[0] = getInverse(1, MOD - 2);
        for (int i = 1; i < n; i++) {
            fact[i] = (i * fact[i - 1]) % MOD;
            invFact[i] = getInverse(fact[i], MOD - 2);
        }
        int[] counts = new int[26];
        long res = 0;
        int total = 0;
        for (int sIndex = n - 1; sIndex >= 0; sIndex--) {
            char c = s.charAt(sIndex);
            int charIndex = c - 'a';
            counts[charIndex]++;
            for (int i = 0; i < charIndex; i++) {
                if (counts[i] > 0) {
                    counts[i]--;
                    long ops = fact[total];
                    for (int j = 0; j < 26; j++) {
                        if (counts[j] > 0) ops = (ops * invFact[counts[j]]) % MOD;
                    }
                    res = (res + ops) % MOD;
                    counts[i]++;
                }
            }
            total++;
        }
        return (int) (res % MOD);
    }

    private long getInverse(long x, long y) {
        if (y == 0) return 1;
        long p = getInverse(x, y / 2) % MOD;
        p = (p * p) % MOD;
        return y % 2 == 0 ? p : (x * p) % MOD;
    }

}
