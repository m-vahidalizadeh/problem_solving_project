package leetcode.companies.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring
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
public class MinWindow {

    public class Window {
        int l;
        int r;
        int size;

        public Window(int l, int r) {
            this.l = l;
            this.r = r;
            this.size = r - l + 1;
        }
    }

    public String minWindow(String s, String t) {
        if (s.isBlank() || t.isBlank()) return "";
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        Map<Character, Integer> windowMap = new HashMap<>();
        int required = tMap.size();
        int formed = 0;
        int l = 0;
        int r = 0;
        int n = s.length();
        Window minWindow = null;
        while (r < n) {
            char c = s.charAt(r);
            if (tMap.containsKey(c)) {
                windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
                if (windowMap.get(c).intValue() == tMap.get(c).intValue()) formed++;
            }
            while (l <= r && formed == required) {
                c = s.charAt(l);
                int newSize = r - l + 1;
                if (minWindow == null || minWindow.size > newSize) minWindow = new Window(l, r);
                if (tMap.containsKey(c)) {
                    windowMap.put(c, windowMap.get(c) - 1);
                    if (windowMap.get(c) < tMap.get(c)) formed--;
                }
                l++;
            }
            r++;
        }
        return minWindow == null ? "" : s.substring(minWindow.l, minWindow.r + 1);
    }

}
