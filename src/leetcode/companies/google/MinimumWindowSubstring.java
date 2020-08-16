package leetcode.companies.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Minimum Window Substring
 * <p>
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * Example:
 * <p>
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 * <p>
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n == 0 || m == 0) return "";
        Map<Character, Integer> freqT = getFrequency(t, m);
        int l = 0, r = 0;
        int required = freqT.size();
        int formed = 0;
        Map<Character, Integer> freqWindow = new HashMap<>();
        int minLength = -1;
        int minL = 0;
        int minR = 0;
        while (r < n) {
            char c = s.charAt(r);
            freqWindow.put(c, freqWindow.getOrDefault(c, 0) + 1);
            if (freqT.containsKey(c) && freqWindow.get(c).intValue() == freqT.get(c).intValue()) formed++;
            while (l <= r && formed == required) {
                c = s.charAt(l);
                if (minLength == -1 || (r - l + 1) < minLength) {
                    minLength = r - l + 1;
                    minL = l;
                    minR = r;
                }
                freqWindow.put(c, freqWindow.get(c) - 1);
                if (freqT.containsKey(c) && freqWindow.get(c) < freqT.get(c)) formed--;
                l++;
            }
            r++;
        }
        return minLength == -1 ? "" : s.substring(minL, minR + 1);
    }

    private Map<Character, Integer> getFrequency(String s, int n) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < n; i++) {
            result.put(s.charAt(i), result.getOrDefault(s.charAt(i), 0) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        MinimumWindowSubstring m = new MinimumWindowSubstring();
        System.out.println(m.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(m.minWindow("a", "a"));
    }

}
