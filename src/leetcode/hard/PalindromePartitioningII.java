package leetcode.hard;

/**
 * 132. Palindrome Partitioning II
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * Example 2:
 *
 * Input: s = "a"
 * Output: 0
 * Example 3:
 *
 * Input: s = "ab"
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= s.length <= 2000
 * s consists of lower-case English letters only.
 */
public class PalindromePartitioningII {

    boolean[][] isPal;
    Integer[][] cache;

    public int minCut(String s) {
        int n = s.length();
        isPal = new boolean[n][n];
        cache = new Integer[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) isPal[i][j] = (j - i <= 2) || isPal[i + 1][j - 1];
            }
        }
        return rec(0, n - 1);
    }

    private int rec(int s, int e) {
        if (isPal[s][e]) return 0; // No more cut needed
        if (cache[s][e] != null) return cache[s][e];
        int res = Integer.MAX_VALUE;
        for (int i = s; i <= e; i++) {
            if (isPal[s][i]) res = Math.min(res, 1 + rec(i + 1, e)); // if s..i is palindrome, cut it between i and i+1
        }
        return cache[s][e] = res;
    }

}
