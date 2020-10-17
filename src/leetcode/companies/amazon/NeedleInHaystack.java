package leetcode.companies.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * 28. Implement strStr()
 * Implement strStr().
 * <p>
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Clarification:
 * <p>
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * <p>
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 * <p>
 * Example 1:
 * <p>
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 * <p>
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Example 3:
 * <p>
 * Input: haystack = "", needle = ""
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * 0 <= haystack.length, needle.length <= 5 * 104
 * haystack and needle consist of only lower-case English characters.
 */
public class NeedleInHaystack {

    public int strStr(String haystack, String needle) {
        if (haystack.isBlank()) return needle.isBlank() ? 0 : -1;
        if (needle.isBlank()) return 0;
        int n = haystack.length();
        int l = needle.length();
        if (n < l) return -1;
        Map<Character, Integer> hashCodes = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) hashCodes.put(c, c + "".hashCode());
        long needleCode = 0;
        for (char c : needle.toCharArray()) needleCode += hashCodes.get(c);
        long[] codeArray = new long[n];
        codeArray[0] = hashCodes.get(haystack.charAt(0));
        for (int i = 1; i < n; i++) codeArray[i] = codeArray[i - 1] + hashCodes.get(haystack.charAt(i));
        for (int i = l - 1; i < n; i++) {
            long prev = i - l < 0 ? 0 : codeArray[i - l];
            long temp = codeArray[i] - prev;
            if (temp == needleCode) {
                if (needle.equals(haystack.substring(i - l + 1, i + 1))) return i - l + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        NeedleInHaystack n = new NeedleInHaystack();
        System.out.println(n.strStr("hello", "ll"));
        System.out.println(n.strStr("aaaaa", "bba"));
        System.out.println(n.strStr("", ""));
        System.out.println(n.strStr("", "a"));
        System.out.println(n.strStr("a", ""));
        System.out.println(n.strStr("mississippi", "pi"));
    }

}
