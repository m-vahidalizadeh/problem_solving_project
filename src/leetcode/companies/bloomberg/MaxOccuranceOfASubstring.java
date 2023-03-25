package leetcode.companies.bloomberg;

import java.util.HashMap;
import java.util.Map;

/**
 * 1297. Maximum Number of Occurrences of a Substring
 * Given a string s, return the maximum number of ocurrences of any substring under the following rules:
 *
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 *
 * Example 1:
 *
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 ocurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
 * Example 2:
 *
 * Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * Output: 2
 * Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s consists of only lowercase English letters.
 */
public class MaxOccuranceOfASubstring {

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int l = 0, r = -1, res = 0;
        Map<String, Integer> occurMap = new HashMap<>();
        Map<Character, Integer> currWindowChars = new HashMap<>();
        while (r < s.length() - 1) {
            r++;
            char charR = s.charAt(r);
            currWindowChars.put(charR, currWindowChars.getOrDefault(charR, 0) + 1);
            while (currWindowChars.size() > maxLetters || (r - l + 1) > maxSize) {
                char charL = s.charAt(l);
                int newFreq = currWindowChars.get(charL) - 1;
                currWindowChars.put(charL, newFreq);
                if (newFreq == 0) currWindowChars.remove(charL);
                l++;
            }
            if ((r - l + 1) >= minSize) {
                int k = l;
                while (r - k + 1 >= minSize) {
                    String currSub = s.substring(k, r + 1);
                    int newOccur = occurMap.getOrDefault(currSub, 0) + 1;
                    occurMap.put(currSub, newOccur);
                    res = Math.max(res, newOccur);
                    k++;
                }
            }
        }
        return res;
    }

}
