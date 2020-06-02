package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom Sort String
 * S and T are strings composed of lowercase letters. In S, no letter occurs more than once.
 * <p>
 * S was sorted in some custom order previously. We want to permute the characters of T so that they match the order
 * that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.
 * <p>
 * Return any permutation of T (as a string) that satisfies this property.
 * <p>
 * Example :
 * Input:
 * S = "cba"
 * T = "abcd"
 * Output: "cbad"
 * Explanation:
 * "a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
 * Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 * <p>
 * Note:
 * <p>
 * S has length at most 26, and no character is repeated in S.
 * T has length at most 200.
 * S and T consist of lowercase letters only.
 */
public class CustomSortString {
    public String customSortString(String S, String T) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            int currFreq = frequencies.getOrDefault(c, 0);
            frequencies.put(c, currFreq + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            int freq = frequencies.getOrDefault(c, 0);
            for (int j = 0; j < freq; j++) {
                sb.append(c);
                frequencies.remove(c);
            }
        }
        if (frequencies.size() > 0) {
            for (Map.Entry<Character, Integer> e : frequencies.entrySet()) {
                for (int i = 0; i < e.getValue(); i++) sb.append(e.getKey());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        CustomSortString c = new CustomSortString();
        String S = "cba";
        String T = "abcd";
        System.out.println(c.customSortString(S, T));
    }
}
