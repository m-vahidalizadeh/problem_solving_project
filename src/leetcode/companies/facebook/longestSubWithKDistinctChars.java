package leetcode.companies.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * 340. Longest Substring with At Most K Distinct Characters
 * Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.
 *
 * Example 1:
 *
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: The substring is "ece" with length 3.
 * Example 2:
 *
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: The substring is "aa" with length 2.
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 104
 * 0 <= k <= 50
 */
public class longestSubWithKDistinctChars {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        Map<Character, Integer> window = new HashMap<>();
        if (k == 0) return 0;
        var l = 0;
        var r = 0;
        var max = 0;
        while (r < n) {
            var currChar = s.charAt(r);
            window.put(currChar, window.getOrDefault(currChar, 0) + 1);
            while (window.size() > k) {
                var lChar = s.charAt(l);
                window.put(lChar, window.get(lChar) - 1);
                if (window.get(lChar) == 0) window.remove(lChar);
                l++;
            }
            max = Math.max(max, r - l + 1);
            // Next
            r++;
        }
        return max;
    }

}
