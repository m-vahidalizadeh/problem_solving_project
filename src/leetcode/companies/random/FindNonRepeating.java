package leetcode.companies.random;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * First Unique Character in a String
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * <p>
 * Examples:
 * <p>
 * s = "leetcode"
 * return 0.
 * <p>
 * s = "loveleetcode",
 * return 2.
 * <p>
 * Note: You may assume the string contain only lowercase English letters.
 */
public class FindNonRepeating {

    public int firstUniqChar(String s) {
        Map<Character, Integer> occurMap = new HashMap<>();
        Set<Character> repeatedSet = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char currChar = s.charAt(i);
            if (occurMap.containsKey(currChar)) {
                repeatedSet.add(currChar);
            } else {
                occurMap.put(currChar, i);
            }
        }
        int firstIndex = n;
        for (Map.Entry<Character, Integer> e : occurMap.entrySet()) {
            if (!repeatedSet.contains(e.getKey())) {
                int index = occurMap.get(e.getKey());
                if (index < firstIndex) firstIndex = index;
            }
        }
        return firstIndex == n ? -1 : firstIndex;
    }

}
