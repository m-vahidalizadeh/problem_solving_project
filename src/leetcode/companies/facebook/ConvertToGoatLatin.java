package leetcode.companies.facebook;

import java.util.Set;

/**
 * 824. Goat Latin
 * Easy
 *
 * Topics
 *
 * Companies
 * You are given a string sentence that consist of words separated by spaces. Each word consists of lowercase and uppercase letters only.
 *
 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.) The rules of Goat Latin are as follows:
 *
 * If a word begins with a vowel ('a', 'e', 'i', 'o', or 'u'), append "ma" to the end of the word.
 * For example, the word "apple" becomes "applema".
 * If a word begins with a consonant (i.e., not a vowel), remove the first letter and append it to the end, then add "ma".
 * For example, the word "goat" becomes "oatgma".
 * Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 * For example, the first word gets "a" added to the end, the second word gets "aa" added to the end, and so on.
 * Return the final sentence representing the conversion from sentence to Goat Latin.
 *
 * Example 1:
 *
 * Input: sentence = "I speak Goat Latin"
 * Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 * Example 2:
 *
 * Input: sentence = "The quick brown fox jumped over the lazy dog"
 * Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 *
 * Constraints:
 *
 * 1 <= sentence.length <= 150
 * sentence consists of English letters and spaces.
 * sentence has no leading or trailing spaces.
 * All the words in sentence are separated by a single space.
 */
public class ConvertToGoatLatin {

    Set<Character> vowels = Set.of('a', 'e', 'o', 'u', 'i', 'A', 'E', 'O', 'U', 'I');

    public String toGoatLatin(String sentence) {
        StringBuilder sb = new StringBuilder();
        int seq = 1, i = 0;
        while (i < sentence.length()) {
            while (i < sentence.length() && sentence.charAt(i) == ' ') {
                sb.append(' ');
                i++;
            }
            if (i == sentence.length()) break;
            int j = i + 1;
            while (j < sentence.length() && sentence.charAt(j) != ' ') j++;
            sb.append(convertToGoat(sentence.substring(i, j), seq));
            // next round
            i = j;
            seq++;
        }
        return sb.toString();
    }

    private String convertToGoat(String word, int seq) {
        StringBuilder sb = new StringBuilder();
        if (vowels.contains(word.charAt(0))) {
            sb.append(word).append("ma");
        } else {
            sb.append(word.substring(1)).append(word.charAt(0)).append("ma");
        }
        for (int i = 0; i < seq; i++) sb.append('a');
        return sb.toString();
    }

}
