package leetcode.hard;

import java.util.*;

/**
 * 301. Remove Invalid Parentheses
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 *
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 *
 * Input: ")("
 * Output: [""]
 */
public class RemoveInvalidPars {

    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        rec(s, 0, 0, res, new char[]{'(', ')'});
        return res;
    }

    private void rec(String s, int l, int r, List<String> res, char[] pars) {
        int stackCounter = 0;
        int n = s.length();
        for (; r < n; r++) {
            char c = s.charAt(r);
            if (c == pars[0]) stackCounter++;
            else if (c == pars[1]) stackCounter--;
            if (stackCounter < 0) break;
        }
        if (stackCounter < 0) {
            for (; l <= r; l++) {
                char c = s.charAt(l);
                if (c != pars[1]) continue;
                if (l > 1 && c == s.charAt(l - 1)) continue;
                rec(s.substring(0, l) + s.substring(l + 1), l, r, res, pars);
            }
        } else if (stackCounter > 0) {
            rec(new StringBuilder(s).reverse().toString(), 0, 0, res, new char[]{')', '('});
        } else {
            res.add(pars[0] == '(' ? s : new StringBuilder(s).reverse().toString());
        }
    }

}
