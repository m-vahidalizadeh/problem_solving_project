package leetcode.companies.random;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest Substring with At Most K Distinct Characters
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 * Example 2:
 * <p>
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 */
public class FindLongestSubWithKDistinct {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int a = 0, max = 0, n = s.length();
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int b = 0; b < n; b++) {
            char bChar = s.charAt(b);
            freqMap.put(bChar, freqMap.getOrDefault(bChar, 0) + 1);
            while (freqMap.size() > k) {
                char aChar = s.charAt(a++);
                int newVal = freqMap.get(aChar) - 1;
                if (newVal == 0) freqMap.remove(aChar);
                else freqMap.put(aChar, newVal);
            }
            max = Math.max(max, b - a + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        FindLongestSubWithKDistinct f = new FindLongestSubWithKDistinct();
        System.out.println(f.lengthOfLongestSubstringKDistinct("eceba", 2));
    }

}
