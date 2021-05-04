package leetcode.companies.facebook;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * Example 4:
 *
 * Input: s = "(a(b(c)d)"
 * Output: "a(b(c)d)"
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s[i] is one of  '(' , ')' and lowercase English letters.
 */
public class MinRemoveToMakeValid {

    public String minRemoveToMakeValid(String s) {
        Deque<Character> stack1 = new ArrayDeque<>();
        Deque<Character> stack2 = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                while (!stack1.isEmpty() && stack1.peek() != '(') stack2.push(stack1.pop());
                if (!stack1.isEmpty()) { // found
                    stack1.pop();
                    stack1.push('[');
                    while (!stack2.isEmpty()) stack1.push(stack2.pop());
                    stack1.push(']');
                } else {
                    while (!stack2.isEmpty()) stack1.push(stack2.pop());
                }
            } else stack1.push(c);
        }
        StringBuilder res = new StringBuilder();
        while (!stack1.isEmpty()) {
            char c = stack1.pop();
            if (c == '(') continue;
            if (c == '[') res.append('(');
            else if (c == ']') res.append(')');
            else res.append(c);
        }
        return res.reverse().toString();
    }

}
