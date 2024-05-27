package leetcode.companies.facebook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 301. Remove Invalid Parentheses
 * Solved
 * Hard
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 *
 * Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 * Example 2:
 *
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 * Example 3:
 *
 * Input: s = ")("
 * Output: [""]
 *
 * Constraints:
 *
 * 1 <= s.length <= 25
 * s consists of lowercase English letters and parentheses '(' and ')'.
 * There will be at most 20 parentheses in s.
 */
public class RemoveLeastInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {
        int rml = 0, rmr = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                rml++;
            } else if (c == ')') {
                if (rml > 0) rml--;
                else rmr++;
            }
        }
        Set<String> res = new HashSet<>();
        dfs(s, 0, res, new StringBuilder(), rml, rmr, 0);
        return new ArrayList<>(res);
    }

    private void dfs(String s, int i, Set<String> res, StringBuilder sb, int rml, int rmr, int open) {
        if (rml < 0 || rmr < 0 || open < 0) return;
        if (i == s.length()) {
            if (rml == 0 && rmr == 0 && open == 0) res.add(sb.toString());
        } else {
            char c = s.charAt(i);
            int len = sb.length();
            if (c == '(') {
                dfs(s, i + 1, res, sb, rml - 1, rmr, open);
                dfs(s, i + 1, res, sb.append(c), rml, rmr, open + 1);
            } else if (c == ')') {
                dfs(s, i + 1, res, sb, rml, rmr - 1, open);
                dfs(s, i + 1, res, sb.append(c), rml, rmr, open - 1);
            } else {
                dfs(s, i + 1, res, sb.append(c), rml, rmr, open);
            }
            sb.setLength(len);
        }
    }

}
