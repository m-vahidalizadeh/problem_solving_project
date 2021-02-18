package leetcode.hard;

/**
 * 940. Distinct Subsequences II
 * Given a string S, count the number of distinct, non-empty subsequences of S .
 *
 * Since the result may be large, return the answer modulo 10^9 + 7.
 *
 * Example 1:
 *
 * Input: "abc"
 * Output: 7
 * Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
 * Example 2:
 *
 * Input: "aba"
 * Output: 6
 * Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa" and "aba".
 * Example 3:
 *
 * Input: "aaa"
 * Output: 3
 * Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
 *
 * Note:
 *
 * S contains only lowercase letters.
 * 1 <= S.length <= 2000
 */
public class DistinctSubsII {

    public int distinctSubseqII(String S) {
        int mod = 1_000_000_007;
        int[] endWithLetter = new int[26]; // How many ended with this char so far
        int count = 1; // "" is counter at the beginning
        for (char c : S.toCharArray()) {
            int prevCount = count;
            count = (count * 2 % mod - endWithLetter[c - 'a'] + mod) % mod; // Make sure it is positive
            endWithLetter[c - 'a'] = prevCount; // Next time this will become count
        }
        return count - 1;
    }

}
