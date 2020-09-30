package leetcode.companies.google;

import java.util.Stack;

/**
 * Your input is a compressed string of the format number[string] and the decompressed output form should be the string written number times. For example:
 * <p>
 * The input
 * <p>
 * 3[abc]4[ab]c
 * <p>
 * Would be output as
 * <p>
 * abcabcabcababababc
 * <p>
 * Other rules
 * <p>
 * Number can have more than one digit. For example, 10[a] is allowed, and just means aaaaaaaaaa
 * <p>
 * One repetition can occur inside another. For example, 2[3[a]b] decompresses into aaabaaab
 * <p>
 * Characters allowed as input include digits, small English letters and brackets [ ].
 * <p>
 * Digits are only to represent amount of repetitions.
 * <p>
 * Letters are just letters.
 * <p>
 * Brackets are only part of syntax of writing repeated substring.
 * <p>
 * Input is always valid, so no need to check its validity.
 */
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
