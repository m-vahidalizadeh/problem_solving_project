package leetcode.companies.google;

import java.util.*;

/**
 * Word Squares
 * Given a set of words (without duplicates), find all word squares you can build from them.
 *
 * A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
 *
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
 *
 * b a l l
 * a r e a
 * l e a d
 * l a d y
 * Note:
 *
 * There are at least 1 and at most 1000 words.
 * All words will have the exact same length.
 * Word length is at least 1 and at most 5.
 * Each word contains only lowercase English alphabet a-z.
 * Example 1:
 *
 * Input:
 * ["area","lead","wall","lady","ball"]
 *
 * Output:
 * [
 *   [ "wall",
 *     "area",
 *     "lead",
 *     "lady"
 *   ],
 *   [ "ball",
 *     "area",
 *     "lead",
 *     "lady"
 *   ]
 * ]
 *
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 * Example 2:
 *
 * Input:
 * ["abat","baba","atan","atal"]
 *
 * Output:
 * [
 *   [ "baba",
 *     "abat",
 *     "baba",
 *     "atan"
 *   ],
 *   [ "baba",
 *     "abat",
 *     "baba",
 *     "atal"
 *   ]
 * ]
 *
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 */
public class WordSquares {

    Map<String, List<String>> prefixMap;
    int l;
    List<List<String>> result;

    public List<List<String>> wordSquares(String[] words) {
        prefixMap = new HashMap<>();
        l = words[0].length();
        buildPrefixMap(words);
        result = new ArrayList<>();
        for (String word : words) {
            LinkedList<String> list = new LinkedList<>();
            list.add(word);
            backTrack(1, list);
        }
        return result;
    }

    private void backTrack(int step, LinkedList<String> list) {
        if (list.size() == l) result.add(new ArrayList<>(list));
        else {
            StringBuilder prefixSB = new StringBuilder();
            for (String word : list) prefixSB.append(word.charAt(step));
            List<String> wordsWithThePrefix = prefixMap.getOrDefault(prefixSB.toString(), new ArrayList<>());
            for (String word : wordsWithThePrefix) {
                list.add(word);
                backTrack(step + 1, list);
                list.removeLast();
            }
        }
    }

    private void buildPrefixMap(String[] words) {
        for (String word : words) {
            for (int i = 0; i < l; i++) {
                String prefix = word.substring(0, i);
                prefixMap.putIfAbsent(prefix, new ArrayList<>());
                List<String> entry = prefixMap.get(prefix);
                entry.add(word);
            }
        }
    }

    public static void main(String[] args) {
        WordSquares w = new WordSquares();
        System.out.println(w.wordSquares(new String[]{"area", "lead", "wall", "lady", "ball"}));
    }

}
