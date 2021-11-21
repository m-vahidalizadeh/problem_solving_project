package leetcode.hard;

import java.util.Stack;

/**
 * 224. Basic Calculator
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Example 1:
 *
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 * '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 * '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 * There will be no two consecutive operators in the input.
 * Every number and running calculation will fit in a signed 32-bit integer.
 */
public class BasicCalculator {

    public int calculate(String s) {
        int operand = 0;
        int n = 0;
        Stack<Object> stack = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                operand = (int) (Math.pow(10, n) * Character.getNumericValue(ch)) + operand;
                n++;
            } else if (ch != ' ') {
                if (n != 0) {
                    stack.push(operand);
                    n = 0;
                    operand = 0;
                }
                if (ch == '(') {
                    int res = evalExp(stack);
                    stack.pop();
                    stack.push(res);
                } else stack.push(ch);
            }
        }
        if (n != 0) stack.push(operand);
        return evalExp(stack);
    }

    private int evalExp(Stack<Object> stack) {
        if (stack.isEmpty() || !(stack.peek() instanceof Integer)) stack.push(0); // 1+2- -> 1+2-0
        int res = (int) stack.pop();
        while (!stack.isEmpty() && ((char) stack.peek()) != ')') {
            char sign = (char) stack.pop();
            if (sign == '+') res += (int) stack.pop();
            else res -= (int) stack.pop();
        }
        return res;
    }

}
