package leetcode.companies.google;

import java.util.ArrayList;
import java.util.List;

/**
 * Expressive Words
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
 * <p>
 * For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.
 * <p>
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
 * <p>
 * Given a list of query words, return the number of words that are stretchy.
 * <p>
 * Example:
 * Input:
 * S = "heeellooo"
 * words = ["hello", "hi", "helo"]
 * Output: 1
 * Explanation:
 * We can extend "e" and "o" in the word "hello" to get "heeellooo".
 * We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 * <p>
 * Constraints:
 * <p>
 * 0 <= len(S) <= 100.
 * 0 <= len(words) <= 100.
 * 0 <= len(words[i]) <= 100.
 * S and all words in words consist only of lowercase letters
 */
public class StretchyWords {

    public class WordFreq {
        List<Character> letters;
        List<Integer> freqs;
        int n;

        public WordFreq(String s) {
            this.letters = new ArrayList<>();
            this.freqs = new ArrayList<>();
            int n = s.length();
            int i = 0;
            while (i < n) {
                char c = s.charAt(i);
                int r = 1;
                i++;
                while (i < n && s.charAt(i) == c) {
                    r++;
                    i++;
                }
                letters.add(c);
                freqs.add(r);
            }
            this.n = letters.size();
        }
    }

    public int expressiveWords(String S, String[] words) {
        WordFreq pattern = new WordFreq(S);
        int count = 0;
        for (String word : words) {
            WordFreq curr = new WordFreq(word);
            if (reachable(pattern, curr)) count++;
        }
        return count;
    }

    private boolean reachable(WordFreq pattern, WordFreq curr) {
        if (pattern.n != curr.n) return false;
        for (int i = 0; i < pattern.n; i++) {
            char pC = pattern.letters.get(i);
            int pR = pattern.freqs.get(i);
            char cC = curr.letters.get(i);
            int cR = curr.freqs.get(i);
            if (pC != cC) return false;
            if (pR < 3) {
                if (cR != pR) return false;
            } else {
                if (cR > pR) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        StretchyWords s = new StretchyWords();
        System.out.println(s.expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"}));
    }

}
