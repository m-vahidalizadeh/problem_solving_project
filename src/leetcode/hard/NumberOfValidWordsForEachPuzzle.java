package leetcode.hard;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1178. Number of Valid Words for Each Puzzle
 * With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
 * word contains the first letter of puzzle.
 * For each letter in word, that letter is in puzzle.
 * For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage", while
 * invalid words are "beefed" (does not include 'a') and "based" (includes 's' which is not in the puzzle).
 * Return an array answer, where answer[i] is the number of words in the given word list words that is valid with respect to the puzzle puzzles[i].
 *
 * Example 1:
 *
 * Input: words = ["aaaa","asas","able","ability","actt","actor","access"], puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * Output: [1,1,3,2,4,0]
 * Explanation:
 * 1 valid word for "aboveyz" : "aaaa"
 * 1 valid word for "abrodyz" : "aaaa"
 * 3 valid words for "abslute" : "aaaa", "asas", "able"
 * 2 valid words for "absoryz" : "aaaa", "asas"
 * 4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
 * There are no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.
 * Example 2:
 *
 * Input: words = ["apple","pleas","please"], puzzles = ["aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy"]
 * Output: [0,1,3,2,0]
 *
 * Constraints:
 *
 * 1 <= words.length <= 105
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 104
 * puzzles[i].length == 7
 * words[i] and puzzles[i] consist of lowercase English letters.
 * Each puzzles[i] does not contain repeated characters.
 */
public class NumberOfValidWordsForEachPuzzle {

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Trie trie = new Trie();
        for (String word : words) {
            boolean[] letters = toLetter(word);
            Trie node = trie;
            for (int i = 0; i < 26; i++) {
                if (letters[i]) {
                    if (node.children[i] == null) node.children[i] = new Trie();
                    node = node.children[i];
                }
            }
            node.n++;
        }
        return Arrays.stream(puzzles).map(p -> getCount(trie, p.charAt(0) - 'a', toLetter(p), new boolean[26])).collect(Collectors.toList());
    }

    public int getCount(Trie node, int puzzleFirstLetter, boolean[] puzzleLetters, boolean[] wordLetters) {
        int count = wordLetters[puzzleFirstLetter] ? node.n : 0;
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null && puzzleLetters[i]) {
                wordLetters[i] = true;
                count += getCount(node.children[i], puzzleFirstLetter, puzzleLetters, wordLetters);
                wordLetters[i] = false;
            }
        }
        return count;
    }

    public boolean[] toLetter(String word) {
        boolean[] letters = new boolean[26];
        for (char c : word.toCharArray()) letters[c - 'a'] = true;
        return letters;
    }

    public class Trie {
        Trie[] children;
        int n;

        public Trie() {
            this.children = new Trie[26];
            this.n = 0;
        }
    }

}
