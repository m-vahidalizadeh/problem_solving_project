package leetcode.companies.google;

/**
 * Expressive Words
 * <p>
 * Solution
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
public class ExpressiveWords {

    public int expressiveWords(String S, String[] words) {
        S = getString(S);
        for (int i = 0; i < words.length; i++) words[i] = getString(words[i]);
        int counter = 0;
        for (String word : words) {
            if (satisfy(S, word)) counter++;
        }
        return counter;
    }

    private boolean satisfy(String s, String word) {
        if (s.length() != word.length()) return false;
        int l = 0, r = 0, i = 0;
        int n = s.length();
        int m = word.length();
        while (l < n) {
            while (l < n && s.charAt(r) != word.charAt(i)) l += 2;
            if (l == n) return false;
            r = l;
            while (i < m && r < n && word.charAt(i) == s.charAt(r) && charSatisfy(s, word, r, i)) {
                r += 2;
                i += 2;
            }
            if (i == m) return true;
            i = 0;
            l += 2;
        }
        return false;
    }

    private boolean charSatisfy(String s, String word, int r, int i) {
        int charRPlusOne = Integer.parseInt(String.valueOf(s.charAt(r + 1)));
        int charIPlusOne = Integer.parseInt(String.valueOf(word.charAt(i + 1)));
        return ((charRPlusOne >= 3 && charRPlusOne >= charIPlusOne))
                ||
                ((charRPlusOne < 3 && charRPlusOne == charIPlusOne));
    }

    private String getString(String S) {
        StringBuilder sb = new StringBuilder();
        int l = 1;
        char prev = ' ';
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == prev) l++;
            else {
                if (' ' != prev) sb.append(prev).append(l);
                prev = S.charAt(i);
                l = 1;
            }
        }
        if (l > 0) sb.append(prev).append(l);
        return sb.toString();
    }

    public static void main(String[] args) {
        ExpressiveWords e = new ExpressiveWords();
        System.out.println(e.expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"}));
        System.out.println(e.expressiveWords("abcd", new String[]{"abc"}));
    }

}
