package leetcode.companies.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Brace Expansion
 * Medium
 * <p>
 * 266
 * <p>
 * 30
 * <p>
 * Add to List
 * <p>
 * Share
 * A string S represents a list of words.
 * <p>
 * Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].
 * <p>
 * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
 * <p>
 * Return all words that can be formed in this manner, in lexicographical order.
 * <p>
 * Example 1:
 * <p>
 * Input: "{a,b}c{d,e}f"
 * Output: ["acdf","acef","bcdf","bcef"]
 * Example 2:
 * <p>
 * Input: "abcd"
 * Output: ["abcd"]
 * <p>
 * Note:
 * <p>
 * 1 <= S.length <= 50
 * There are no nested curly brackets.
 * All characters inside a pair of consecutive opening and ending curly brackets are different.
 */
public class RepresentAListOfWords {

    public class Result {
        PriorityQueue<String> pq;

        public Result() {
            pq = new PriorityQueue<>();
        }
    }

    public String[] expand(String S) {
        Result result = new Result();
        expandRec(S, 0, S.length(), "", result);
        String[] res = new String[result.pq.size()];
        int i = 0;
        while (!result.pq.isEmpty()) res[i++] = result.pq.poll();
        return res;
    }

    private void expandRec(String S, int i, int n, String curr, Result result) {
        if (i == n) result.pq.add(curr);
        else {
            char c = S.charAt(i);
            if (c == '{') {
                List<Character> tempList = new ArrayList<>();
                c = S.charAt(++i);
                while (c != '}') {
                    if (c != ',') tempList.add(c);
                    c = S.charAt(++i);
                }
                for (char e : tempList) expandRec(S, i + 1, n, curr + e, result);
            } else expandRec(S, i + 1, n, curr + c, result);
        }
    }

    public static void main(String[] args) {
        RepresentAListOfWords r = new RepresentAListOfWords();
        System.out.println(Arrays.toString(r.expand("{a,b,c}d{e,f}")));
    }

}
