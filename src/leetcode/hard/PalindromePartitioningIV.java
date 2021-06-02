package leetcode.hard;

/**
 * 1745. Palindrome Partitioning IV
 * Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings. Otherwise, return false.​​​​​
 *
 * A string is said to be palindrome if it the same string when reversed.
 *
 * Example 1:
 *
 * Input: s = "abcbdd"
 * Output: true
 * Explanation: "abcbdd" = "a" + "bcb" + "dd", and all three substrings are palindromes.
 * Example 2:
 *
 * Input: s = "bcbddxy"
 * Output: false
 * Explanation: s cannot be split into 3 palindromes.
 *
 *
 * Constraints:
 *
 * 3 <= s.length <= 2000
 * s consists only of lowercase English letters.
 */
public class PalindromePartitioningIV {

    public boolean checkPartitioning(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        // Solution 1
//        for (int l = 1; l <= n; l++) {
//            for (int i = 0; i <= n - l; i++) {
//                int j = i + l - 1;
//                if (s.charAt(i) == s.charAt(j)) isPal[i][j] = j - i < 2 || isPal[i + 1][j - 1];
//            }
//        }
        // Solution 2
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) isPal[i][j] = j - i < 2 || isPal[i + 1][j - 1];
            }
        }
        for (int i = 0; i <= n - 3; i++) {
            if (isPal[0][i]) {
                for (int j = i + 1; j <= n - 2; j++) {
                    if (isPal[i + 1][j] && isPal[j + 1][n - 1]) return true;
                }
            }
        }
        return false;
    }

}
