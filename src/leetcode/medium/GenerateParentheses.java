package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate Parentheses
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class GenerateParentheses {

    public class Result {
        List<String> strings;

        public Result() {
            strings = new ArrayList<>();
        }
    }

    public List<String> generateParenthesis(int n) {
        char[] str = new char[2 * n];
        Result result = new Result();
        generateRec(str, 0, n, 0, 0, result);
        return result.strings;
    }

    private void generateRec(char[] str, int pos, int n, int open, int close, Result result) {
        if (close == n) {
            StringBuilder sb = new StringBuilder();
            for (char c : str) sb.append(c);
            result.strings.add(sb.toString());
        } else {
            if (open > close) {
                str[pos] = ')';
                generateRec(str, pos + 1, n, open, close + 1, result);
            }
            if (open < n) {
                str[pos] = '(';
                generateRec(str, pos + 1, n, open + 1, close, result);
            }
        }
    }

}
