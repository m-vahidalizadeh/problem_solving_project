package leetcode.companies.google;

import java.util.ArrayList;
import java.util.List;

/**
 * 1592. Rearrange Spaces Between Words
 * You are given a string text of words that are placed among some number of spaces. Each word consists of one or more lowercase English letters and are separated by at least one space. It's guaranteed that text contains at least one word.
 *
 * Rearrange the spaces so that there is an equal number of spaces between every pair of adjacent words and that number is maximized. If you cannot redistribute all the spaces equally, place the extra spaces at the end, meaning the returned string should be the same length as text.
 *
 * Return the string after rearranging the spaces.
 *
 * Example 1:
 *
 * Input: text = "  this   is  a sentence "
 * Output: "this   is   a   sentence"
 * Explanation: There are a total of 9 spaces and 4 words. We can evenly divide the 9 spaces between the words: 9 / (4-1) = 3 spaces.
 * Example 2:
 *
 * Input: text = " practice   makes   perfect"
 * Output: "practice   makes   perfect "
 * Explanation: There are a total of 7 spaces and 3 words. 7 / (3-1) = 3 spaces plus 1 extra space. We place this extra space at the end of the string.
 * Example 3:
 *
 * Input: text = "hello   world"
 * Output: "hello   world"
 * Example 4:
 *
 * Input: text = "  walks  udp package   into  bar a"
 * Output: "walks  udp  package  into  bar  a "
 * Example 5:
 *
 * Input: text = "a"
 * Output: "a"
 *
 * Constraints:
 *
 * 1 <= text.length <= 100
 * text consists of lowercase English letters and ' '.
 * text contains at least one word.
 */
public class ReorderSpaces {

    public String reorderSpaces(String text) {
        int numSpaces = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') numSpaces++;
        }
        if (numSpaces == 0) return text;
        List<String> words = getWords(text);
        if (words.size() == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(words.get(0));
            for (int i = 0; i < numSpaces; i++) sb.append(' ');
            return sb.toString();
        }
        int betweenWords = numSpaces / (words.size() - 1);
        int endSpaces = numSpaces % (words.size() - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            if (i == words.size() - 1) {
                sb.append(words.get(i));
                for (int j = 0; j < endSpaces; j++) sb.append(' ');
            } else { // i<words.length-1
                sb.append(words.get(i));
                for (int j = 0; j < betweenWords; j++) sb.append(' ');
            }
        }
        return sb.toString();
    }

    private List<String> getWords(String text) {
        int i = 0;
        List<String> words = new ArrayList<>();
        while (i < text.length()) {
            if (text.charAt(i) != ' ') {
                int j = i;
                while (j < text.length() && text.charAt(j) != ' ') j++;
                words.add(text.substring(i, j));
                i = j;
            } else i++;
        }
        return words;
    }

    public static void main(String[] args) {
        ReorderSpaces r = new ReorderSpaces();
        System.out.println(r.reorderSpaces("  this   is  a sentence "));
    }

}
