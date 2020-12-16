package leetcode.medium;

/**
 * 856. Score of Parentheses
 * Given a balanced parentheses string S, compute the score of the string based on the following rule:
 *
 * () has score 1
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: 1
 * Example 2:
 *
 * Input: "(())"
 * Output: 2
 * Example 3:
 *
 * Input: "()()"
 * Output: 2
 * Example 4:
 *
 * Input: "(()(()))"
 * Output: 6
 *
 * Note:
 *
 * S is a balanced parentheses string, containing only ( and ).
 * 2 <= S.length <= 50
 */
public class ScoreOfParentheses {

    String s;

    public int scoreOfParentheses(String S) {
        int n = S.length();
        s = S;
        return rec(0, n - 1);
    }

    private int rec(int i, int end) {
        if (i > end) return 0;
        if (s.charAt(i) != '(') return rec(i + 1, end);
        int balance = 1;
        int j = i + 1;
        while (j <= end) {
            if (s.charAt(j) == '(') balance++;
            else if (s.charAt(j) == ')') balance--;
            if (balance == 0) break;
            j++;
        }
        if (j <= end) {
            if (i + 1 == j) return 1 + rec(j + 1, end);
            else return 2 * rec(i + 1, j - 1) + rec(j + 1, end);
        } else {
            return rec(i + 1, end);
        }
    }

}
