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
public class StringDecode {

    public String decodeString(String s) {
        return rec(s, 0, s.length() - 1);
    }

    private String rec(String s, int l, int r) {
        if (r < l) return "";
        StringBuilder sb = new StringBuilder();
        StringBuilder sbDigit = new StringBuilder();
        while (l <= r) {
            char c = s.charAt(l);
            if ('a' <= c && c <= 'z') sb.append(c);
            else if (c == '[') {
                int repeat = Integer.parseInt(sbDigit.toString());
                sbDigit = new StringBuilder();
                int open = 1;
                int closeI = l;
                while (open != 0) {
                    closeI++;
                    if (s.charAt(closeI) == '[') open++;
                    else if (s.charAt(closeI) == ']') open--;
                }
                String res = rec(s, l + 1, closeI);
                for (int i = 0; i < repeat; i++) sb.append(res);
                l = closeI;
            } else if (Character.isDigit(c)) sbDigit.append(c);
            l++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StringDecode s = new StringDecode();
        System.out.println(s.decodeString("3[a]2[bc]"));
    }

}
