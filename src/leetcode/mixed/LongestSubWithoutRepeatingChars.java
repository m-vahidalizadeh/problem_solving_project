package leetcode.mixed;

import java.util.HashMap;
import java.util.Map;

public class LongestSubWithoutRepeatingChars {

    public static void main(String[] args) {
/*
Example 1:
Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
        LongestSubWithoutRepeatingChars longestSubWithoutRepeatingChars = new LongestSubWithoutRepeatingChars();
//        System.out.println(longestSubWithoutRepeatingChars.lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(longestSubWithoutRepeatingChars.lengthOfLongestSubstring("bbbbb"));
//        System.out.println(longestSubWithoutRepeatingChars.lengthOfLongestSubstring("pwwkew"));
        System.out.println(longestSubWithoutRepeatingChars.lengthOfLongestSubstring("abcabcbb"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        int max = 0;
        char[] sChars = s.toCharArray();
        int n = sChars.length;
        Map<Character, Integer> locations = new HashMap<>();
        int a = 0;
        locations.put(sChars[a], 0);
        int b = 1;
        while (a < n && b < n) {
            char currentChar = sChars[b];
            if (locations.containsKey(currentChar)) {
                int tempMax = b - a;
                if (tempMax > max)
                    max = tempMax;
                a = locations.get(currentChar) + 1;
                b = a + 1;
                locations = new HashMap<>();
                locations.put(sChars[a], a);
            } else {
                locations.put(currentChar, b);
                b++;
            }
        }
        int tempMax = b - a;
        if (tempMax > max)
            return tempMax;
        return max;
    }

}
