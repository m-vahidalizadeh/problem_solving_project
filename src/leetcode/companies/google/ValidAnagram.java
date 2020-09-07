package leetcode.companies.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Valid Anagram
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "rat", t = "car"
 * Output: false
 * Note:
 * You may assume the string contains only lowercase alphabets.
 * <p>
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> sFreq = getFreqMap(s);
        Map<Character, Integer> tFreq = getFreqMap(t);
        for (Map.Entry<Character, Integer> e : sFreq.entrySet()) {
            char c = e.getKey();
            int r = e.getValue();
            if (!tFreq.containsKey(c) || r != tFreq.get(c)) return false;
        }
        return true;
    }

    private Map<Character, Integer> getFreqMap(String s) {
        Map<Character, Integer> result = new HashMap<>();
        for (char c : s.toCharArray()) result.put(c, result.getOrDefault(c, 0) + 1);
        return result;
    }

    public static void main(String[] args) {
        ValidAnagram v = new ValidAnagram();
        System.out.println(v.isAnagram("aacc", "ccac"));
    }

}
