package leetcode.companies.google;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 1896. Minimum Cost to Change the Final Value of Expression
 * You are given a valid boolean expression as a string expression consisting of the characters '1','0','&' (bitwise AND operator),'|' (bitwise OR operator),'(', and ')'.
 *
 * For example, "()1|1" and "(1)&()" are not valid while "1", "(((1))|(0))", and "1|(0&(1))" are valid expressions.
 * Return the minimum cost to change the final value of the expression.
 *
 * For example, if expression = "1|1|(0&0)&1", its value is 1|1|(0&0)&1 = 1|1|0&1 = 1|0&1 = 1&1 = 1. We want to apply operations so that the new expression evaluates to 0.
 * The cost of changing the final value of an expression is the number of operations performed on the expression. The types of operations are described as follows:
 *
 * Turn a '1' into a '0'.
 * Turn a '0' into a '1'.
 * Turn a '&' into a '|'.
 * Turn a '|' into a '&'.
 * Note: '&' does not take precedence over '|' in the order of calculation. Evaluate parentheses first, then in left-to-right order.
 *
 * Example 1:
 *
 * Input: expression = "1&(0|1)"
 * Output: 1
 * Explanation: We can turn "1&(0|1)" into "1&(0&1)" by changing the '|' to a '&' using 1 operation.
 * The new expression evaluates to 0.
 * Example 2:
 *
 * Input: expression = "(0&0)&(0&0&0)"
 * Output: 3
 * Explanation: We can turn "(0&0)&(0&0&0)" into "(0|1)|(0&0&0)" using 3 operations.
 * The new expression evaluates to 1.
 * Example 3:
 *
 * Input: expression = "(0|(1|0&1))"
 * Output: 1
 * Explanation: We can turn "(0|(1|0&1))" into "(0|(0|0&1))" using 1 operation.
 * The new expression evaluates to 0.
 *
 * Constraints:
 *
 * 1 <= expression.length <= 105
 * expression only contains '1','0','&','|','(', and ')'
 * All parentheses are properly matched.
 * There will be no empty parentheses (i.e: "()" is not a substring of expression).
 */
public class MinCostToFlipEval {

    Map<Integer, Integer> map;

    public int minOperationsToFlip(String expression) {
        map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        int n = expression.length();
        for (int i = 0; i < n; i++) {
            if (expression.charAt(i) == '(') stack.push(i);
            else if (expression.charAt(i) == ')') map.put(i, stack.pop());
        }
        return dfs(0, n - 1, expression)[1];
    }

    private int[] dfs(int s, int e, String expression) { // returns {value,cost of changing}
        if (s == e) return new int[]{Character.getNumericValue(expression.charAt(s)), 1};
        int s2 = expression.charAt(e) == ')' ? map.get(e) : e;
        if (s == s2) return dfs(s + 1, e - 1, expression);
        char op = expression.charAt(s2 - 1);
        int[] par1 = dfs(s, s2 - 2, expression);
        int[] par2 = dfs(s2, e, expression);
        if (op == '&') {
            if (par1[0] == 0 && par2[0] == 0)
                return new int[]{0, Math.min(par1[1] + par2[1], Math.min(1 + par1[1], 1 + par2[1]))};
            else if (par1[0] == 1 && par2[0] == 1) return new int[]{1, Math.min(par1[1], par2[1])};
            else return new int[]{0, 1};
        } else if (op == '|') {
            if (par1[0] == 0 && par2[0] == 0) return new int[]{0, Math.min(par1[1], par2[1])};
            else if (par1[0] == 1 && par2[0] == 1)
                return new int[]{1, Math.min(par1[1] + par2[1], Math.min(1 + par1[1], 1 + par2[1]))};
            else return new int[]{1, 1};
        }
        return null;
    }

}
