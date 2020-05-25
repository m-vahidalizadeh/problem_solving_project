package leetcode.companies.google;

import java.util.Stack;

public class GOOGLBackSpace {

    public static void main(String[] args) {
/*
Example 1:
Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".

Example 2:
Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".

Example 3:
Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".

Example 4:
Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
 */
        GOOGLBackSpace googlBackSpace = new GOOGLBackSpace();
        System.out.println(googlBackSpace.backspaceCompare("ab#c", "ad#c"));
        System.out.println(googlBackSpace.backspaceCompare("ab##", "c#d#"));
        System.out.println(googlBackSpace.backspaceCompare("a##c", "#a#c"));
        System.out.println(googlBackSpace.backspaceCompare("a#c", "b"));
        System.out.println(googlBackSpace.backspaceCompare("y#fo##f", "y#f#o##f"));
    }

    public boolean backspaceCompare(String S, String T) {
        if (S.length() == 0 && T.length() == 0)
            return true;
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();
        char[] sChars = S.toCharArray();
        char[] tChars = T.toCharArray();
        for (char c : sChars) {
            if (!stackS.isEmpty() && c == '#') {
                stackS.pop();
            } else if (c != '#') {
                stackS.push(c);
            }
        }
        for (char c : tChars) {
            if (!stackT.isEmpty() && c == '#') {
                stackT.pop();
            } else if (c != '#') {
                stackT.push(c);
            }
        }
        if (stackS.size() != stackT.size()) {
            return false;
        }
        for (int i = 0; i < stackS.size(); i++) {
            if (stackS.pop() != stackT.pop()) {
                return false;
            }
        }
        return true;
    }

}
