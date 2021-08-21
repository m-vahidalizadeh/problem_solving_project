package leetcode.companies.google;

import java.util.*;

/**
 * 1153. String Transforms Into Another String
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.
 *
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 *
 * Return true if and only if you can transform str1 into str2.
 *
 * Example 1:
 *
 * Input: str1 = "aabcc", str2 = "ccdee"
 * Output: true
 * Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
 * Example 2:
 *
 * Input: str1 = "leetcode", str2 = "codeleet"
 * Output: false
 * Explanation: There is no way to transform str1 to str2.
 *
 * Constraints:
 *
 * 1 <= str1.length == str2.length <= 104
 * str1 and str2 contain only lowercase English letters.
 */
public class CanConvert {

    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) return true;
        Set<Character> toSet = new HashSet<>();
        Map<Character, Character> fromToMap = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            char from = str1.charAt(i);
            char to = str2.charAt(i);
            if (fromToMap.containsKey(from)) {
                if (fromToMap.get(from) != to) return false;
            } else {
                fromToMap.put(from, to);
                toSet.add(to);
            }
        }
        return toSet.size() < 26; // Even-if the str1 and str2 are equal now, we need a temp char (nothing is mapped to it) for the transformations like ab->ba
    }

}
