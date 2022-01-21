package leetcode.hard;

/**
 * 1163. Last Substring in Lexicographical Order
 * Given a string s, return the last substring of s in lexicographical order.
 *
 * Example 1:
 *
 * Input: s = "abab"
 * Output: "bab"
 * Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
 * Example 2:
 *
 * Input: s = "leetcode"
 * Output: "tcode"
 *
 * Constraints:
 *
 * 1 <= s.length <= 4 * 105
 * s contains only lowercase English letters.
 */
public class LastSubstringInLexicographicalOrder {

    public String lastSubstring(String s) {
        int i = 0;
        int j = 1;
        int offset = 0;
        int len = s.length();
        while (i + offset < len && j + offset < len) {
            char c = s.charAt(i + offset);
            char d = s.charAt(j + offset);
            if (c == d) offset++;
            else {
                if (c < d) i += offset + 1;
                else j += offset + 1;
                if (i == j) j++;
                offset = 0;
            }
        }
        return s.substring(i);
    }

}
