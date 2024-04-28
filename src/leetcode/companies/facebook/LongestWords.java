package leetcode.companies.facebook;

/**
 * 720. Longest Word in Dictionary
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * Given an array of strings words representing an English Dictionary, return the longest word in words that can be built one character at a time by other words in words.
 *
 * If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.
 *
 * Note that the word should be built from left to right with each additional character being added to the end of a previous word.
 *
 * Example 1:
 *
 * Input: words = ["w","wo","wor","worl","world"]
 * Output: "world"
 * Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 * Example 2:
 *
 * Input: words = ["a","banana","app","appl","ap","apply","apple"]
 * Output: "apple"
 * Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
 *
 * Constraints:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 30
 * words[i] consists of lowercase English letters.
 */
public class LongestWords {

    String longest = "";

    public String longestWord(String[] words) {
        Trie root = new Trie();
        root.isWord = true;
        for (String word : words) insert(root, word);
        findLongest(root, "");
        return longest;
    }

    private void findLongest(Trie node, String s) {
        if (!node.isWord) return;
        if (s.length() > longest.length()) longest = s;
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) findLongest(node.children[i], s + ((char) ('a' + i)));
        }
    }

    private void insert(Trie root, String word) {
        for (int i = 0; i < word.length(); i++) {
            Trie node = null;
            if (root.children[word.charAt(i) - 'a'] == null) root.children[word.charAt(i) - 'a'] = new Trie();
            root = root.children[word.charAt(i) - 'a'];
        }
        root.isWord = true;
    }

    class Trie {
        Trie[] children = new Trie[26];
        boolean isWord = false;
    }

}
