package leetcode.medium;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Brace Expansion
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
public class BraceExpansion {

    public class Result {
        PriorityQueue<String> strings;

        public Result() {
            strings = new PriorityQueue<>();
        }
    }

    public String[] expand(String S) {
        Result result = new Result();
        findExpansions(S, 0, S.length(), "", result);
        int n = result.strings.size();
        String[] strings = new String[n];
        int i = 0;
        while (!result.strings.isEmpty()) {
            strings[i++] = result.strings.poll();
        }
        return strings;
    }

    private void findExpansions(String s, int index, int n, String curr, Result result) {
        if (index == n) {
            result.strings.add(curr);
            return;
        }
        char c = s.charAt(index);
        if (c == '{') {
            Set<Character> tempSet = new HashSet<>();
            c = s.charAt(++index);
            while (c != '}') {
                if (c != ',') tempSet.add(c);
                c = s.charAt(++index);
            }
            for (Character tempS : tempSet) findExpansions(s, index + 1, n, curr + tempS, result);
        } else {
            findExpansions(s, index + 1, n, curr + c, result);
        }
    }

}
