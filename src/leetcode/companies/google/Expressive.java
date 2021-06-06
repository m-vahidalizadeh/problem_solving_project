package leetcode.companies.google;

/**
 * 809. Expressive Words
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
 *
 * For some given string s, a query word is stretchy if it can be made to be equal to s by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.
 *
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If s = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = s.
 *
 * Given a list of query words, return the number of words that are stretchy.
 *
 * Example:
 * Input:
 * s = "heeellooo"
 * words = ["hello", "hi", "helo"]
 * Output: 1
 * Explanation:
 * We can extend "e" and "o" in the word "hello" to get "heeellooo".
 * We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 *
 * Constraints:
 *
 * 0 <= len(s) <= 100.
 * 0 <= len(words) <= 100.
 * 0 <= len(words[i]) <= 100.
 * s and all words in words consist only of lowercase letters
 */
public class Expressive {

    public int expressiveWords(String S, String[] words) {
        int count = 0;
        for (String word : words) {
            if (isExpressive(S, word)) count++;
        }
        return count;
    }

    private boolean isExpressive(String s, String word) {
        if (s.isBlank() && word.isBlank()) return true;
        if (s.isBlank()) return false;
        if (word.isBlank()) return false;
        if (s.charAt(0) != word.charAt(0)) return false;
        int i = 0;
        while (i < s.length() && s.charAt(0) == s.charAt(i)) i++;
        int j = 0;
        while (j < word.length() && word.charAt(0) == word.charAt(j)) j++;
        if (i < 3) {
            if (i != j) return false;
            else return isExpressive(s.substring(i), word.substring(j));
        }
        if (j > i) return false;
        else return isExpressive(s.substring(i), word.substring(j));
    }

}
