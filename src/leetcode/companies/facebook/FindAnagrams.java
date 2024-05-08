package leetcode.companies.facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. Find All Anagrams in a String
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 * Example 1:
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 * Constraints:
 *
 * 1 <= s.length, p.length <= 3 * 10^4
 * s and p consist of lowercase English letters.
 */
public class FindAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int[] target = new int[26];
        int[] window = new int[26];
        for (int i = 0; i < p.length(); i++) {
            window[s.charAt(i) - 'a']++;
            target[p.charAt(i) - 'a']++;
        }
        if (areEquals(window, target)) res.add(0);
        for (int i = p.length(), start = 1; i < s.length(); i++, start++) {
            window[s.charAt(start - 1) - 'a']--;
            window[s.charAt(i) - 'a']++;
            if (areEquals(window, target)) res.add(start);
        }
        return res;
    }

    private boolean areEquals(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

}
