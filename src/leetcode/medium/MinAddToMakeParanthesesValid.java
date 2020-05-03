package leetcode.medium;

/**
 * Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any
 * positions ) so that the resulting parentheses string is valid.
 * <p>
 * Formally, a parentheses string is valid if and only if:
 * <p>
 * It is the empty string, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.
 */
public class MinAddToMakeParanthesesValid {

    public static void main(String[] args) {
        /*
Example 1:
Input: "())"
Output: 1

Example 2:
Input: "((("
Output: 3

Example 3:
Input: "()"
Output: 0

Example 4:
Input: "()))(("
Output: 4
         */
        MinAddToMakeParanthesesValid minAddToMakeParanthesesValid = new MinAddToMakeParanthesesValid();
        System.out.println(minAddToMakeParanthesesValid.minAddToMakeValid("())"));
        System.out.println(minAddToMakeParanthesesValid.minAddToMakeValid("((("));
        System.out.println(minAddToMakeParanthesesValid.minAddToMakeValid("()"));
        System.out.println(minAddToMakeParanthesesValid.minAddToMakeValid("()))(("));
    }

    public int minAddToMakeValid(String S) {
        int result = 0;
        int balance = 0;
        int n = S.length();
        if (n == 0)
            return 0;
        char[] sChars = S.toCharArray();
        for (int i = 0; i < n; i++) {
            balance += sChars[i] == '(' ? 1 : -1;
            if (balance == -1) {
                result++;
                balance++;
            }
        }
        return balance + result;
    }

}
