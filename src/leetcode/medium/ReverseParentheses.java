package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1190. Reverse Substrings Between Each Pair of Parentheses
 * You are given a string s that consists of lower case English letters and brackets.
 * <p>
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 * <p>
 * Your result should not contain any brackets.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "(abcd)"
 * Output: "dcba"
 * Example 2:
 * <p>
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * Explanation: The substring "love" is reversed first, then the whole string is reversed.
 * Example 3:
 * <p>
 * Input: s = "(ed(et(oc))el)"
 * Output: "leetcode"
 * Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
 * Example 4:
 * <p>
 * Input: s = "a(bcdefghijkl(mno)p)q"
 * Output: "apmnolkjihgfedcbq"
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 2000
 * s only contains lower case English characters and parentheses.
 * It's guaranteed that all parentheses are balanced.
 */
public class ReverseParentheses {

    public String reverseParentheses(String s) {
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && !stack.peek().equals("(")) sb.append(reverse(stack.pop()));
                stack.pop();
                stack.push(sb.toString());
            } else stack.push(c + "");
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.insert(0, stack.pop());
        return sb.toString();
    }

    private String reverse(String input) {
        int n = input.length();
        if (n == 1) return input;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) result.insert(0, input.charAt(i));
        return result.toString();
    }

    public static void main(String[] args) {
        ReverseParentheses r = new ReverseParentheses();
        System.out.println(r.reverseParentheses("(abcd)"));
        System.out.println(r.reverseParentheses("(u(love)i)"));
        System.out.println(r.reverseParentheses("(ed(et(oc))el)"));
        System.out.println(r.reverseParentheses("a(bcdefghijkl(mno)p)q"));
    }

}
