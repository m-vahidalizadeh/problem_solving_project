package leetcode.companies.amazon;

import java.util.*;

/**
 * Valid Parentheses
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 * <p>
 * Example 1:
 * <p>
 * Input: "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 * <p>
 * Input: "(]"
 * Output: false
 * Example 4:
 * <p>
 * Input: "([)]"
 * Output: false
 * Example 5:
 * <p>
 * Input: "{[]}"
 * Output: true
 */
public class ValidBrackets {

    public boolean isValid(String s) {
        Map<Character, Character> openingChars = new HashMap<Character, Character>() {{
            put('(', ')');
            put('{', '}');
            put('[', ']');
        }};
        char[] sChars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < sChars.length; i++) {
            char currentChar = sChars[i];
            if (openingChars.containsKey(currentChar)) stack.push(openingChars.get(currentChar));
            else if (stack.isEmpty() || !stack.pop().equals(currentChar)) return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidBrackets v = new ValidBrackets();
        System.out.println(v.isValid("([)]"));
        System.out.println(v.isValid("{[]}"));
    }

}
