package leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 291. Word Pattern II
 * Given a pattern and a string s, return true if s matches the pattern.
 *
 * A string s matches a pattern if there is some bijective mapping of single characters to strings such that if each character in pattern is replaced by the string it maps to, then the resulting string is s. A bijective mapping means that no two characters map to the same string, and no character maps to two different strings.
 *
 * Example 1:
 *
 * Input: pattern = "abab", s = "redblueredblue"
 * Output: true
 * Explanation: One possible mapping is as follows:
 * 'a' -> "red"
 * 'b' -> "blue"
 * Example 2:
 *
 * Input: pattern = "aaaa", s = "asdasdasdasd"
 * Output: true
 * Explanation: One possible mapping is as follows:
 * 'a' -> "asd"
 * Example 3:
 *
 * Input: pattern = "abab", s = "asdasdasdasd"
 * Output: true
 * Explanation: One possible mapping is as follows:
 * 'a' -> "a"
 * 'b' -> "sdasd"
 * Note that 'a' and 'b' cannot both map to "asd" since the mapping is a bijection.
 * Example 4:
 *
 * Input: pattern = "aabb", s = "xyzabcxzyabc"
 * Output: false
 *
 * Constraints:
 *
 * 0 <= pattern.length <= 20
 * 0 <= s.length <= 50
 * pattern and s consist of only lower-case English letters.
 */
public class WordPatternII {

    public boolean wordPatternMatch(String pattern, String s) {
        if (pattern == null || s == null) return false;
        Map<Character, String> map = new HashMap<>();
        Set<String> mapped = new HashSet<>();
        return doesMatch(pattern, s, map, mapped);
    }

    private boolean doesMatch(String pattern, String s, Map<Character, String> map, Set<String> mapped) {
        if (pattern.length() == 0) return s.length() == 0;
        char c = pattern.charAt(0);
        if (map.containsKey(c)) {
            String mappedString = map.get(c);
            if (s.length() < mappedString.length()) return false;
            if (!mappedString.equals(s.substring(0, mappedString.length()))) return false;
            return doesMatch(pattern.substring(1), s.substring(mappedString.length()), map, mapped);
        } else {
            for (int i = 1; i <= s.length(); i++) {
                String mappedString = s.substring(0, i);
                if (mapped.contains(mappedString)) continue;
                map.put(c, mappedString);
                mapped.add(mappedString);
                if (doesMatch(pattern.substring(1), s.substring(i), map, mapped)) return true;
                mapped.remove(mappedString);
                map.remove(c);
            }
        }
        return false;
    }

}
