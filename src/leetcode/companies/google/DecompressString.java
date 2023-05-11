package leetcode.companies.google;

/**
 * In this exercise, you're going to decompress a compressed string.
 *
 * Your input is a compressed string of the format number[string] and the decompressed output form should be the string written number times. For example:
 *
 * The input
 *
 * 3[abc]4[ab]c
 *
 * Would be output as
 *
 * abcabcabcababababc
 *
 * Other rules
 *
 * Number can have more than one digit. For example, 10[a] is allowed, and just means aaaaaaaaaa
 *
 * One repetition can occur inside another. For example, 2[3[a]b] decompresses into aaabaaab
 *
 * Characters allowed as input include digits, small English letters and brackets [ ].
 *
 * Digits are only to represent amount of repetitions.
 *
 * Letters are just letters.
 *
 * Brackets are only part of syntax of writing repeated substring.
 *
 * Input is always valid, so no need to check its validity.
 *
 * Learning objectives
 *
 * This question gives you the chance to practice with strings, recursion, algorithm, compilers, automata, and loops. It’s also an opportunity to work on coding with better efficiency.
 */
public class DecompressString {

    public static void main(String[] args) {
        System.out.println(decompress("2[3[a]b]"));
    }

    private static String decompress(String compressed) {
        if (!compressed.contains("[")) return compressed;
        int s = 0, e = 0, n = compressed.length();
        StringBuilder sb = new StringBuilder();
        while (s < n) {
            while (Character.isDigit(compressed.charAt(e))) e++;
            if (s < e) {
                int rep = Integer.parseInt(compressed.substring(s, e));
                e++;
                s = e;
                int open = 1;
                while (open != 0) {
                    if (compressed.charAt(e) == '[') open++;
                    else if (compressed.charAt(e) == ']') open--;
                    e++;
                }
                String res = decompress(compressed.substring(s, e - 1));
                for (int i = 0; i < rep; i++) sb.append(res);
                s = e;
            } else {
                while (e < n && Character.isAlphabetic(compressed.charAt(e))) e++;
                sb.append(compressed.substring(s, e));
                s = e;
            }
        }
        return sb.toString();
    }

}
