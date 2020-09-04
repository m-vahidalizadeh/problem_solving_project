package leetcode.companies.google;

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

    List<String> result;
    int twiceN;

    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        twiceN = 2 * n;
        char[] combination = new char[twiceN];
        addCombinations(combination, 0, n, 0, 0);
        return result;
    }

    private void addCombinations(char[] combination, int i, int n, int open, int close) {
        if (i == twiceN) {
            result.add(String.valueOf(combination, 0, twiceN));
        } else {
            if (open > close) {
                combination[i] = ')';
                addCombinations(combination, i + 1, n, open, close + 1);
            }
            if (open < n) {
                combination[i] = '(';
                addCombinations(combination, i + 1, n, open + 1, close);
            }
        }
    }

    public static void main(String[] args) {
        GenerateParentheses g = new GenerateParentheses();
        System.out.println(g.generateParenthesis(3));
    }

}
