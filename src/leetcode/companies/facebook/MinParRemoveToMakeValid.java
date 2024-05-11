package leetcode.companies.facebook;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 *
 * Hint
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
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s[i] is either '(' , ')', or lowercase English letter.
 */
public class MinParRemoveToMakeValid {

    public String minRemoveToMakeValid(String s) {
        int n = s.length();
        int[] occ = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) occ[i] = occ[i + 1] + (s.charAt(i) == ')' ? 1 : 0);
        StringBuilder sb = new StringBuilder();
        int balance = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ')' && balance == 0) continue;
            if (c == '(' && occ[i + 1] <= balance) continue;
            if (c == '(') balance++;
            if (c == ')') balance--;
            sb.append(c);
        }
        return sb.toString();
    }

}
