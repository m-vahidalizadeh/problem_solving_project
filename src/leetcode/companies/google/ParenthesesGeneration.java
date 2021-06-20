package leetcode.companies.google;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 *
 * Constraints:
 *
 * 1 <= n <= 8
 */
public class ParenthesesGeneration {

    public List<String> generateParenthesis(int n) {
        return rec(0, 0, 0, 2 * n, n, new StringBuilder());
    }

    private List<String> rec(int open, int close, int total, int doubleN, int n, StringBuilder sb) {
        if (total == doubleN) return List.of(sb.toString());
        List<String> res = new ArrayList<>();
        if (open < n) {
            sb.append('(');
            res.addAll(rec(open + 1, close, total + 1, doubleN, n, sb));
            sb.deleteCharAt(sb.length() - 1);
        }
        if (open > close) {
            sb.append(')');
            res.addAll(rec(open, close + 1, total + 1, doubleN, n, sb));
            sb.deleteCharAt(sb.length() - 1);
        }
        return res;
    }

}
