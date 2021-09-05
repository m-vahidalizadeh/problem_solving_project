package leetcode.companies.google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 205. Isomorphic Strings
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 104
 * t.length == s.length
 * s and t consist of any valid ascii character.
 */
public class IsIsomorphic {

    int n;

    public boolean isIsomorphic(String s, String t) {
        n = s.length();
        return rec(s, 0, t, 0, new HashMap<>(), new HashSet<>());
    }

    private boolean rec(String s, int sI, String t, int tI, Map<Character, Character> map, Set<Character> set) {
        if (sI == n) return true;
        char sC = s.charAt(sI);
        char tC = t.charAt(tI);
        if (map.containsKey(sC)) {
            if (map.get(sC) != tC) return false;
            else return rec(s, sI + 1, t, tI + 1, map, set);
        } else {
            if (set.contains(tC)) return false;
            else {
                map.put(sC, tC);
                set.add(tC);
                return rec(s, sI + 1, t, tI + 1, map, set);
            }
        }
    }

}
