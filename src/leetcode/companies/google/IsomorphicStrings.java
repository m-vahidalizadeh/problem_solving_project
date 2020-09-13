package leetcode.companies.google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Isomorphic Strings
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 * <p>
 * Input: s = "paper", t = "title"
 * Output: true
 * Note:
 * You may assume both s and t have the same length.
 */
public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        Set<Character> used = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char sC = s.charAt(i);
            char tC = t.charAt(i);
            if (map.containsKey(sC)) {
                if (map.get(sC) != tC) return false;
            } else {
                if (used.contains(tC)) return false;
                map.put(sC, tC);
                used.add(tC);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsomorphicStrings i = new IsomorphicStrings();
        System.out.println(i.isIsomorphic("egg", "add"));
        System.out.println(i.isIsomorphic("foo", "bar"));
        System.out.println(i.isIsomorphic("paper", "title"));
        System.out.println(i.isIsomorphic("ab", "aa"));
    }

}
