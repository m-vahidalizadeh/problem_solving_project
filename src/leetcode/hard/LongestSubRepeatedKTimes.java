package leetcode.hard;

import java.util.*;

/**
 * 2014. Longest Subsequence Repeated k Times
 * You are given a string s of length n, and an integer k. You are tasked to find the longest subsequence repeated k times in string s.
 *
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 *
 * A subsequence seq is repeated k times in the string s if seq * k is a subsequence of s, where seq * k represents a string constructed by concatenating seq k times.
 *
 * For example, "bba" is repeated 2 times in the string "bababcba", because the string "bbabba", constructed by concatenating "bba" 2 times, is a subsequence of the string "bababcba".
 * Return the longest subsequence repeated k times in string s. If multiple such subsequences are found, return the lexicographically largest one. If there is no such subsequence, return an empty string.
 *
 * Example 1:
 *
 * example 1
 * Input: s = "letsleetcode", k = 2
 * Output: "let"
 * Explanation: There are two longest subsequences repeated 2 times: "let" and "ete".
 * "let" is the lexicographically largest one.
 * Example 2:
 *
 * Input: s = "bb", k = 2
 * Output: "b"
 * Explanation: The longest subsequence repeated 2 times is "b".
 * Example 3:
 *
 * Input: s = "ab", k = 2
 * Output: ""
 * Explanation: There is no subsequence repeated 2 times. Empty string is returned.
 * Example 4:
 *
 * Input: s = "bbabbabbbbabaababab", k = 3
 * Output: "bbbb"
 * Explanation: The longest subsequence "bbbb" is repeated 3 times in "bbabbabbbbabaababab".
 *
 * Constraints:
 *
 * n == s.length
 * 2 <= n, k <= 2000
 * 2 <= n < k * 8
 * s consists of lowercase English letters.
 */
public class LongestSubRepeatedKTimes {

    public String longestSubsequenceRepeatedK(String s, int k) {
        String res = "";
        Queue<String> q = new LinkedList<>();
        q.add("");
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        List<Character> availableChars = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            if (freqMap.getOrDefault(c, 0) >= k) availableChars.add(c);
        }
        while (!q.isEmpty()) {
            int size = q.size();
            for (int a = 0; a < size; a++) {
                String curr = q.poll();
                for (char c : availableChars) {
                    String next = curr + c;
                    if (isSub(s, next, k)) {
                        res = next;
                        q.add(next);
                    }
                }
            }
        }
        return res;
    }

    private boolean isSub(String s, String sub, int k) {
        int j = 0;
        int repeat = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == sub.charAt(j)) {
                j++;
                if (j == sub.length()) {
                    repeat++;
                    if (repeat == k) return true;
                    j = 0;
                }
            }
        }
        return false;
    }

}
