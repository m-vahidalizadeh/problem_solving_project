package leetcode.companies.google;

/**
 * 394. Decode String
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 * Example 1:
 *
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 *
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 * Example 4:
 *
 * Input: s = "abc3[cd]xyz"
 * Output: "abccdcdcdxyz"
 *
 * Constraints:
 *
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 */
public class StringDecoding {

    int n;
    String s;

    public String decodeString(String s) {
        n = s.length();
        this.s = s;
        return rec(0, n - 1);
    }

    private String rec(int l, int r) {
        StringBuilder res = new StringBuilder();
        StringBuilder repetition = new StringBuilder();
        while (l <= r) {
            char c = s.charAt(l);
            if (Character.isLetter(c)) res.append(c);
            else if (c == '[') {
                int open = 1;
                int end = l + 1;
                while (open != 0) {
                    if (s.charAt(end) == ']') open--;
                    else if (s.charAt(end) == '[') open++;
                    end++;
                }
                String sub = rec(l + 1, end - 1);
                int rep = Integer.parseInt(repetition.toString());
                for (int i = 0; i < rep; i++) res.append(sub);
                l = end - 1;
                repetition = new StringBuilder();
            } else { // c is digit
                repetition.append(c);
            }
            l++;
        }
        return res.toString();
    }

}
