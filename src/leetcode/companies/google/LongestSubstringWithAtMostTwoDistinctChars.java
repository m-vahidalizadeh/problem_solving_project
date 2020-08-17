package leetcode.companies.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest Substring with At Most Two Distinct Characters
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 * Example 2:
 * <p>
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 */
public class LongestSubstringWithAtMostTwoDistinctChars {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if (n == 0) return 0;
        Map<Character, Integer> freqWindow = new HashMap<>();
        int l = 0, r = 0, max = 0;
        while (l < n) {
            char cr = s.charAt(r);
            freqWindow.put(cr, freqWindow.getOrDefault(cr, 0) + 1);
            while (l < n && freqWindow.size() > 2) {
                char cl = s.charAt(l);
                int newVal = freqWindow.get(cl) - 1;
                if (newVal == 0) freqWindow.remove(cl);
                else freqWindow.put(cl, newVal);
                l++;
            }
            if (l == n) return max;
            max = Math.max(r - l + 1, max);
            if (++r == n) {
                l++;
                r = l;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubstringWithAtMostTwoDistinctChars l = new LongestSubstringWithAtMostTwoDistinctChars();
        System.out.println(l.lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(l.lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }

}
