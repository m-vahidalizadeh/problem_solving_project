package leetcode.companies.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Example 4:
 * <p>
 * Input: s = ""
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubstringWithoutRepeatingChars {

    public int lengthOfLongestSubstring(String s) {
        int l = 0;
        int r = 0;
        int n = s.length();
        int max = 0;
        Map<Character, Integer> window = new HashMap<>();
        while (l < n && r < n) {
            char c = s.charAt(r);
            window.put(c, window.getOrDefault(c, 0) + 1);
            while (window.getOrDefault(c, 0) > 1 && l < n) {
                int newCount = window.get(s.charAt(l)) - 1;
                if (newCount == 0) window.remove(s.charAt(l));
                else window.put(s.charAt(l), newCount);
                l++;
            }
            r++;
            max = Math.max(max, window.size());
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingChars l = new LongestSubstringWithoutRepeatingChars();
        System.out.println(l.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(l.lengthOfLongestSubstring("bbbbb"));
        System.out.println(l.lengthOfLongestSubstring("pwwkew"));
        System.out.println(l.lengthOfLongestSubstring("dvdf"));
    }

}
