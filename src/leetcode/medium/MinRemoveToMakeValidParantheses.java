package leetcode.medium;

import java.util.Stack;

/**
 * Minimum Remove to Make Valid Parentheses
 * Given a string s of '(' , ')' and lowercase English characters.
 * <p>
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 * <p>
 * Formally, a parentheses string is valid if and only if:
 * <p>
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 * <p>
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 * <p>
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * Example 4:
 * <p>
 * Input: s = "(a(b(c)d)"
 * Output: "a(b(c)d)"
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^5
 * s[i] is one of  '(' , ')' and lowercase English letters.
 */
public class MinRemoveToMakeValidParantheses {

    public String minRemoveToMakeValid(String s) {
        Stack<Character> stack = getStack(s);
        return getResult(stack);
    }

    private String getResult(Stack<Character> stack) {
        StringBuilder sb = new StringBuilder();
        int open = 0, close = 0;
        while (!stack.isEmpty()) {
            char c = stack.pop();
            switch (c) {
                case '(':
                    if (open < close) {
                        sb.insert(0, c);
                        open++;
                    }
                    break;
                case ')':
                    sb.insert(0, c);
                    close++;
                    break;
                default:
                    sb.insert(0, c);
            }
        }
        return sb.toString();
    }

    private Stack<Character> getStack(String s) {
        Stack<Character> stack = new Stack<>();
        int open = 0, close = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                    stack.push(c);
                    open++;
                    break;
                case ')':
                    if (close < open) {
                        stack.push(c);
                        close++;
                    }
                    break;
                default:
                    stack.push(c);
            }
        }
        return stack;
    }

    public static void main(String[] args) {
        MinRemoveToMakeValidParantheses m = new MinRemoveToMakeValidParantheses();
        System.out.println(m.minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(m.minRemoveToMakeValid("a)b(c)d"));
        System.out.println(m.minRemoveToMakeValid("))(("));
        System.out.println(m.minRemoveToMakeValid("(a(b(c)d)"));
    }

}
