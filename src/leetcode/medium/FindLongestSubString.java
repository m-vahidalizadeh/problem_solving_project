package leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1371. Find the Longest Substring Containing Vowels in Even Counts
 * Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.
 *
 * Example 1:
 *
 * Input: s = "eleetminicoworoep"
 * Output: 13
 * Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.
 * Example 2:
 *
 * Input: s = "leetcodeisgreat"
 * Output: 5
 * Explanation: The longest substring is "leetc" which contains two e's.
 * Example 3:
 *
 * Input: s = "bcbcbc"
 * Output: 6
 * Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 x 10^5
 * s contains only lowercase English letters.
 */
public class FindLongestSubString {

    public int findTheLongestSubstring(String s) {
        Map<Character, Integer> vowelMap = new HashMap<>() {{
            put('a', 1);
            put('e', 2);
            put('i', 4);
            put('o', 8);
            put('u', 16);
        }};
        int mask = 0;
        int res = 0;
        int[] dp = new int[32];
        Arrays.fill(dp,-1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            mask ^= vowelMap.getOrDefault(c, 0);
            if (mask != 0 && dp[mask] == -1) dp[mask] = i;
            res = Math.max(res, i - dp[mask]);
        }
        return res;
    }

    public static void main(String[] args) {
        FindLongestSubString f = new FindLongestSubString();
        System.out.println(f.findTheLongestSubstring("eleetminicoworoep"));
    }

}
