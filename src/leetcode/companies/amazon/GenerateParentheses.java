package leetcode.companies.amazon;

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
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        rec(0, 0, 0, 2 * n, n, "", result);
        return result;
    }

    private void rec(int o, int c, int sum, int doubleN, int n, String curr, List<String> result) {
        if (sum == doubleN) result.add(curr);
        else {
            if (o < n) rec(o + 1, c, sum + 1, doubleN, n, curr + '(', result);
            if (c < o) rec(o, c + 1, sum + 1, doubleN, n, curr + ')', result);
        }
    }

}
