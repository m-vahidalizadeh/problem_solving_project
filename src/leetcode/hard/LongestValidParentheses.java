package leetcode.hard;

/**
 * 32. Longest Valid Parentheses
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 *
 * Constraints:
 *
 * 0 <= s.length <= 3 * 104
 * s[i] is '(', or ')'.
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        return Math.max(findMax(true, s), findMax(false, s)); // 1) move from 0 to n-1 2) move from n-1 to 0
    }

    private int findMax(boolean forward, String s) {
        int n = s.length();
        int max = 0;
        int open = 0; // counts the open parentheses
        int close = 0; // counts the close parentheses
        for (int i = forward ? 0 : n - 1; forward ? i < n : i >= 0; i += forward ? 1 : -1) { // move forward or backward
            char c = s.charAt(i); // character to be investigated
            if (c == '(') open++; // we see another open parentheses
            else close++; // we see another close parentheses
            if (open == close) max = Math.max(max, open + close); // found a balance situation, consider it
            else if (forward ? open < close : close < open) { // there are parentheses that cause imbalance- reset
                open = 0;
                close = 0;
            }
        }
        return max;
    }

}
