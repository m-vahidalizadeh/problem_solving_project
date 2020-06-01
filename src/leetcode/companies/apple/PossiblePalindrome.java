package leetcode.companies.apple;

import java.util.HashSet;
import java.util.Set;

/**
 * Palindrome Permutation
 * Given a string, determine if a permutation of the string could form a palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: "code"
 * Output: false
 * Example 2:
 * <p>
 * Input: "aab"
 * Output: true
 * Example 3:
 * <p>
 * Input: "carerac"
 * Output: true
 */
public class PossiblePalindrome {

    public class PalindromeRecognizer {
        Set<Character> chars;

        public PalindromeRecognizer() {
            chars = new HashSet<>();
        }

        public void addChar(char c) {
            if (chars.contains(c)) chars.remove(c);
            else chars.add(c);
        }

        public boolean isPossiblePalindrome() {
            return chars.size() <= 1;
        }
    }

    public boolean canPermutePalindrome(String s) {
        PalindromeRecognizer pr = new PalindromeRecognizer();
        for (int i = 0; i < s.length(); i++) pr.addChar(s.charAt(i));
        return pr.isPossiblePalindrome();
    }

    public static void main(String[] args) {
        PossiblePalindrome p = new PossiblePalindrome();
        System.out.println(p.canPermutePalindrome("code"));
        System.out.println(p.canPermutePalindrome("aab"));
        System.out.println(p.canPermutePalindrome("carerac"));
    }

}
