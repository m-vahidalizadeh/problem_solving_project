package leetcode.companies.google;

import java.util.Stack;

public class DuplicateRemoval {

    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for (char c : S.toCharArray()) {
            if (!stack.isEmpty() && c == stack.peek()) stack.pop();
            else stack.push(c);
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) result.insert(0, stack.pop());
        return result.toString();
    }

    public static void main(String[] args) {
        DuplicateRemoval d = new DuplicateRemoval();
        System.out.println(d.removeDuplicates("abbaca"));
    }

}
