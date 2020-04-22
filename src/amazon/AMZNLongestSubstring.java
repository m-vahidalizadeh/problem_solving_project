package amazon;

import java.util.HashSet;
import java.util.Set;

public class AMZNLongestSubstring {

    public static void main(String[] args) {
/*
Example 1:
Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        char[] sChars = s.toCharArray();
        int n = sChars.length;
        for (int i = 0; i < n; i++) {
            Set<Character> occuredChars = new HashSet<>();
            occuredChars.add(sChars[i]);
            int length = 1;
            int j = i + 1;
            while (j < n && !occuredChars.contains(sChars[j])) {
                length++;
                occuredChars.add(sChars[j]);
                j++;
            }
            if (length > max) {
                max = length;
            }
        }
        return max;
    }

}
