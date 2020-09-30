package leetcode.companies.google;

import java.util.Stack;

public class Decompress {

    public String decompress(String compressed) {
        Stack<String> stack = new Stack<>();
        int n = compressed.length();
        for (int i = 0; i < n; i++) {
            char c = compressed.charAt(i);
            if (c == ']') {
                String word = "";
                while (!stack.peek().equals("[")) word = stack.pop() + word;
                stack.pop();
                String digit = "";
                while (!stack.isEmpty() && Character.isDigit(stack.peek().charAt(0))) digit = stack.pop() + digit;
                int d = Integer.parseInt(digit);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < d; j++) sb.append(word);
                stack.push(sb.toString());
            } else {
                stack.push(c + "");
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.insert(0, stack.pop());
        return sb.toString();
    }

    public static void main(String[] args) {
        Decompress d = new Decompress();
        System.out.println(d.decompress("3[abc]4[ab]c"));
        System.out.println(d.decompress("10[a]"));
        System.out.println(d.decompress("2[3[a]b]"));
    }

}
