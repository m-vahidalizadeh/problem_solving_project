package leetcode.companies.amazon;

import java.util.*;

/**
 * 387. First Unique Character in a String
 * Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.
 * <p>
 * Examples:
 * <p>
 * s = "leetcode"
 * return 0.
 * <p>
 * s = "loveleetcode"
 * return 2.
 * <p>
 * Note: You may assume the string contains only lowercase English letters.
 */
public class FirstUniqueChar {

    public int firstUniqChar(String s) {
        Map<Character, Integer> count = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) count.put(s.charAt(i), count.getOrDefault(s.charAt(i), 0) + 1);
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        FirstUniqueChar f = new FirstUniqueChar();
        System.out.println(f.firstUniqChar("leetcode"));
        System.out.println(f.firstUniqChar("loveleetcode"));
        System.out.println(f.firstUniqChar("aadadaad"));
    }

}
