package leetcode.companies.google;

import java.util.HashMap;
import java.util.Map;

/**
 * 159. Longest Substring with At Most Two Distinct Characters
 * Given a string s, return the length of the longest substring that contains at most two distinct characters.
 *
 * Example 1:
 *
 * Input: s = "eceba"
 * Output: 3
 * Explanation: The substring is "ece" which its length is 3.
 * Example 2:
 *
 * Input: s = "ccaabbb"
 * Output: 5
 * Explanation: The substring is "aabbb" which its length is 5.
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of English letters.
 */
public class TwoDistinctChars {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.isBlank()) return 0;
        Map<Character, Integer> window = new HashMap<>();
        int n = s.length();
        int l = 0;
        int r = 0;
        int max = 0;
        while (r < n) {
            window.put(s.charAt(r), window.getOrDefault(s.charAt(r), 0) + 1);
            while (window.size() > 2) {
                window.put(s.charAt(l), window.get(s.charAt(l)) - 1);
                if (window.get(s.charAt(l)) == 0) window.remove(s.charAt(l));
                l++;
            }
            max = Math.max(max, r - l + 1);
            r++;
        }
        return max;
    }

}
