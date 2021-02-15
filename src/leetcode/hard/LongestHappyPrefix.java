package leetcode.hard;

/**
 * 1392. Longest Happy Prefix
 * A string is called a happy prefix if is a non-empty prefix which is also a suffix (excluding itself).
 *
 * Given a string s. Return the longest happy prefix of s .
 *
 * Return an empty string if no such prefix exists.
 *
 * Example 1:
 *
 * Input: s = "level"
 * Output: "l"
 * Explanation: s contains 4 prefix excluding itself ("l", "le", "lev", "leve"), and suffix ("l", "el", "vel", "evel"). The largest prefix which is also suffix is given by "l".
 * Example 2:
 *
 * Input: s = "ababab"
 * Output: "abab"
 * Explanation: "abab" is the largest prefix which is also suffix. They can overlap in the original string.
 * Example 3:
 *
 * Input: s = "leetcodeleet"
 * Output: "leet"
 * Example 4:
 *
 * Input: s = "a"
 * Output: ""
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s contains only lowercase English letters.
 */
public class LongestHappyPrefix {

    public String longestPrefix(String s) {
        int n = s.length();
        if (n <= 1) return "";
        int[] kmp = new int[n];
        int i = 1;
        int j = 0;
        while (i < n) {
            if (s.charAt(j) == s.charAt(i)) kmp[i++] = ++j;
            else if (j > 0) j = kmp[j - 1];
            else i++;
        }
        return s.substring(0, kmp[n - 1]);
    }

}
