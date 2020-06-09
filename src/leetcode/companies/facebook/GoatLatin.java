package leetcode.companies.facebook;

import java.util.Set;

/**
 * Goat Latin
 * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.
 * <p>
 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)
 * <p>
 * The rules of Goat Latin are as follows:
 * <p>
 * If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
 * For example, the word 'apple' becomes 'applema'.
 * <p>
 * If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
 * For example, the word "goat" becomes "oatgma".
 * <p>
 * Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 * For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
 * Return the final sentence representing the conversion from S to Goat Latin.
 * <p>
 * Example 1:
 * <p>
 * Input: "I speak Goat Latin"
 * Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 * Example 2:
 * <p>
 * Input: "The quick brown fox jumped over the lazy dog"
 * Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 * <p>
 * Notes:
 * <p>
 * S contains only uppercase, lowercase and spaces. Exactly one space between each word.
 * 1 <= S.length <= 150.
 */
public class GoatLatin {

    public final static Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

    public String toGoatLatin(String S) {
        if (S.isBlank()) return S;
        char[] sChars = S.toCharArray();
        int n = sChars.length;
        int counter = 1;
        StringBuffer sb = new StringBuffer();
        int start = 0;
        int end = 0;
        while (end < n) {
            while (end < n && sChars[end] != ' ') end++;
            if (VOWELS.contains(sChars[start])) {
                for (int i = start; i < end; i++) sb.append(sChars[i]);
                sb.append("ma");
            } else {
                for (int i = start + 1; i < end; i++) sb.append(sChars[i]);
                sb.append(sChars[start]);
                sb.append("ma");
            }
            for (int i = 0; i < counter; i++) sb.append('a');
            for (int i = end; i < n; i++) {
                if (sChars[i] == ' ') {
                    sb.append(' ');
                    end++;
                } else break;
            }
            start = end;
            counter++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        GoatLatin g = new GoatLatin();
        System.out.println(g.toGoatLatin("I speak   Goat Latin"));
        System.out.println(g.toGoatLatin("The quick brown fox jumped over the lazy dog"));
    }

}
