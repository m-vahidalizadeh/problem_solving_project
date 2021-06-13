package leetcode.companies.google;

import java.util.*;

/**
 * 425. Word Squares
 * Given an array of unique strings words, return all the word squares you can build from words. The same word from words can be used multiple times. You can return the answer in any order.
 *
 * A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).
 *
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
 *
 * Example 1:
 *
 * Input: words = ["area","lead","wall","lady","ball"]
 * Output: [["ball","area","lead","lady"],["wall","area","lead","lady"]]
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 * Example 2:
 *
 * Input: words = ["abat","baba","atan","atal"]
 * Output: [["baba","abat","baba","atal"],["baba","abat","baba","atan"]]
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 *
 * Constraints:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 5
 * All words[i] have the same length.
 * words[i] consists of only lowercase English letters.
 * All words[i] are unique.
 */
public class SquareWords {

    private Trie root;
    int n;
    List<List<String>> res;

    public List<List<String>> wordSquares(String[] words) {
        root = new Trie();
        n = words[0].length();
        res = new ArrayList<>();
        for (String word : words) addToTrie(word);
        for (String word : words) {
            LinkedList<String> list = new LinkedList<>();
            list.add(word);
            backtrack(1, list);
        }
        return res;
    }

    private void backtrack(int index, LinkedList<String> list) {
        if (index == n) {
            res.add(new ArrayList<>(list));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String word : list) sb.append(word.charAt(index));
        String prefix = sb.toString();
        Set<String> possibleWords = getPossibleWords(prefix);
        for (String word : possibleWords) {
            list.addLast(word);
            backtrack(index + 1, list);
            list.removeLast();
        }
    }

    public class Trie {
        Map<Character, Trie> children;
        boolean isWord;
        String word;

        public Trie() {
            children = new HashMap<>();
        }
    }

    private void addToTrie(String word) {
        Trie curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children.containsKey(c)) {
                curr = curr.children.get(c);
            } else {
                Trie newChild = new Trie();
                curr.children.put(c, newChild);
                curr = newChild;
            }
        }
        curr.isWord = true;
        curr.word = word;
    }

    private Set<String> getPossibleWords(String prefix) {
        Trie foundTrie = searchTrie(prefix);
        if (foundTrie == null) return Collections.emptySet();
        Set<String> words = new HashSet<>();
        Queue<Trie> q = new ArrayDeque<>();
        q.add(foundTrie);
        while (!q.isEmpty()) {
            Trie curr = q.poll();
            if (curr.isWord) words.add(curr.word);
            else {
                for (Trie child : curr.children.values()) q.add(child);
            }
        }
        return words;
    }

    private Trie searchTrie(String prefix) {
        Trie curr = root;
        for (char c : prefix.toCharArray()) {
            if (curr.children.containsKey(c)) {
                curr = curr.children.get(c);
            } else {
                return null;
            }
        }
        return curr;
    }

}
