package leetcode.companies.google;

import java.util.Stack;

/**
 * Decode String
 * Given an encoded string, return its decoded string.
 * <p>
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * <p>
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * <p>
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * <p>
 * Example 1:
 * <p>
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 * <p>
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 * <p>
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 * Example 4:
 * <p>
 * Input: s = "abc3[cd]xyz"
 * Output: "abccdcdcdxyz"
 */
public class DecodeString {

    public String decodeString(String s) {
        int n = s.length();
        if (n == 0) return "";
        int i = 0;
        Stack<String> stack = new Stack<>();
        while (i < n) {
            String c = s.substring(i, i + 1);
            if ("[".equals(c) || Character.isAlphabetic(s.charAt(i))) {
                stack.push(c);
                i++;
            } else if ("]".equals(c)) {
                StringBuilder sb = new StringBuilder();
                while (!stack.peek().equals("[")) sb.insert(0, stack.pop());
                stack.pop();
                int num = Integer.parseInt(stack.pop());
                StringBuilder sb2 = new StringBuilder();
                for (int j = 0; j < num; j++) sb2.append(sb);
                stack.push(sb2.toString());
                i++;
            } else {
                // number
                StringBuilder sb = new StringBuilder();
                while ('[' != s.charAt(i)) sb.append(s.charAt(i++));
                stack.push(sb.toString());
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) result.insert(0, stack.pop());
        return result.toString();
    }

    public static void main(String[] args) {
        DecodeString d = new DecodeString();
        System.out.println(d.decodeString("3[a]2[bc]"));
        System.out.println(d.decodeString("3[a2[c]]"));
        System.out.println(d.decodeString("2[abc]3[cd]ef"));
        System.out.println(d.decodeString("abc3[cd]xyz"));
        System.out.println(d.decodeString("100[leetcode]"));
    }

}
