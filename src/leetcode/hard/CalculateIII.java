package leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 772. Basic Calculator III
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, '+', '-', '*', '/' operators, open '(' and closing parentheses ')' and empty spaces ' '. The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Example 1:
 *
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: s = " 6-4 / 2 "
 * Output: 4
 * Example 3:
 *
 * Input: s = "2*(5+5*2)/3+(6/2+8)"
 * Output: 21
 * Example 4:
 *
 * Input: s = "(2+6* 3+5- (3*14/7+2)*5)+3"
 * Output: -12
 * Example 5:
 *
 * Input: s = "0"
 * Output: 0
 *
 * Constraints:
 *
 * 1 <= s <= 104
 * s consists of digits, '+', '-', '*', '/', '(', ')' and ' '.
 * s is a valid expression.
 */
public class CalculateIII {

    public int calculate(String s) {
        Deque<Character> q = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c != ' ') q.add(c);
        }
        q.add(' ');
        return rec(q);
    }

    private int rec(Deque<Character> q) {
        int num = 0;
        int sum = 0;
        int prev = 0;
        char prevOp = '+';
        while (!q.isEmpty()) {
            char c = q.poll();
            if ('0' <= c && c <= '9') num = num * 10 + c - '0';
            else if (c == '(') num = rec(q);
            else {
                if (prevOp == '+') {
                    sum += prev;
                    prev = num;
                } else if (prevOp == '-') {
                    sum += prev;
                    prev = -num;
                } else if (prevOp == '*') prev *= num;
                else if (prevOp == '/') prev /= num;
                if (c == ')') break;
                prevOp = c;
                num = 0;
            }
        }
        return sum + prev;
    }

}
