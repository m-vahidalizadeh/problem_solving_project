package leetcode.companies.google;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 *
 * Constraints:
 *
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 *
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
public class MinWindowSub {

    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || s.length() < t.length()) return "";
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        int left = 0;
        int minLeft = 0;
        int minLength = s.length() + 1;
        int count = 0;
        for (int right = 0; right < s.length(); right++) {
            char cRight = s.charAt(right);
            if (map.containsKey(cRight)) {
                map.put(cRight, map.get(cRight) - 1);
                if (map.get(cRight) >= 0) count++;
                while (count == t.length()) {
                    if (right - left + 1 < minLength) {
                        minLeft = left;
                        minLength = right - left + 1;
                    }
                    char cLeft = s.charAt(left);
                    if (map.containsKey(cLeft)) {
                        map.put(cLeft, map.get(cLeft) + 1);
                        if (map.get(cLeft) > 0) count--;
                    }
                    left++;
                }
            }
        }
        if (minLength > s.length()) return "";
        return s.substring(minLeft, minLeft + minLength);
    }

}
