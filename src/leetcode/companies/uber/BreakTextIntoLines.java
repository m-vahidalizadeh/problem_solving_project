package leetcode.companies.uber;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. Text Justification
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 *
 * Note:
 *
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 *
 * Example 1:
 *
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * Example 2:
 *
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified because it contains only one word.
 * Example 3:
 *
 * Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *
 * Constraints:
 *
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] consists of only English letters and symbols.
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 */
public class BreakTextIntoLines {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int currLinelength = 0;
        int currLineWords = 0;
        int start = 0;
        int end = 0;
        while (end < words.length) {
            while (end < words.length && currLinelength + words[end].length() + currLineWords <= maxWidth) {
                currLinelength += words[end].length();
                currLineWords++;
                end++;
            }
            if (end != words.length) {
                int spaces = 0;
                int totalSpaces = 0;
                int extra = 0;
                if (currLineWords == 1) {
                    spaces = (maxWidth - currLinelength);
                } else {
                    totalSpaces = maxWidth - currLinelength;
                    spaces = Double.valueOf(Math.floor(((double) maxWidth - currLinelength) / (currLineWords - 1))).intValue();
                    extra = (maxWidth - currLinelength) % (currLineWords - 1);
                }
                StringBuilder sb = new StringBuilder();
                for (int i = start; i < end; i++) {
                    sb.append(words[i]);
                    if (i != end - 1) {
                        for (int j = 0; j < spaces; j++) {
                            sb.append(" ");
                            totalSpaces--;
                        }
                        if (extra > 0) {
                            sb.append(" ");
                            extra--;
                        }
                    }
                }
                if (currLineWords == 1) {
                    while (spaces > 0) {
                        spaces--;
                        sb.append(" ");
                    }
                }
                res.add(sb.toString());
            } else { // last line
                int spaces = (maxWidth - currLinelength);
                StringBuilder sb = new StringBuilder();
                for (int i = start; i < end; i++) {
                    sb.append(words[i]);
                    if (i != end - 1) {
                        spaces--;
                        sb.append(" ");
                    }
                }
                while (spaces > 0) {
                    spaces--;
                    sb.append(" ");
                }
                res.add(sb.toString());
            }
            start = end;
            currLinelength = 0;
            currLineWords = 0;
        }
        return res;
    }

}
