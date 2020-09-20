package leetcode.companies.google;

import java.util.*;

/**
 * Stream of Characters
 * Implement the StreamChecker class as follows:
 * <p>
 * StreamChecker(words): Constructor, init the data structure with the given words.
 * query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.
 * <p>
 * Example:
 * <p>
 * StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
 * streamChecker.query('a');          // return false
 * streamChecker.query('b');          // return false
 * streamChecker.query('c');          // return false
 * streamChecker.query('d');          // return true, because 'cd' is in the wordlist
 * streamChecker.query('e');          // return false
 * streamChecker.query('f');          // return true, because 'f' is in the wordlist
 * streamChecker.query('g');          // return false
 * streamChecker.query('h');          // return false
 * streamChecker.query('i');          // return false
 * streamChecker.query('j');          // return false
 * streamChecker.query('k');          // return false
 * streamChecker.query('l');          // return true, because 'kl' is in the wordlist
 * <p>
 * Note:
 * <p>
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 2000
 * Words will only consist of lowercase English letters.
 * Queries will only consist of lowercase English letters.
 * The number of queries is at most 40000.
 */
public class StreamChecker {

    public class Trie {
        Map<Character, Trie> children;
        boolean isWord;

        public Trie() {
            children = new HashMap<>();
            isWord = false;
        }
    }

    String stream;
    Trie root;

    public StreamChecker(String[] words) {
        root = new Trie();
        for (String word : words) addWord(word);
        stream = "";
    }

    public boolean query(char letter) {
        stream += letter;
        return isWord();
    }

    private boolean isWord() {
        Trie cur = root;
        for (int i = stream.length() - 1; i >= 0; i--) {
            char c = stream.charAt(i);
            if (!cur.children.containsKey(c)) return false;
            cur = cur.children.get(c);
            if (cur.isWord) return true;
        }
        return false;
    }

    private void addWord(String word) {
        Trie cur = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (cur.children.containsKey(c)) cur = cur.children.get(c);
            else {
                Trie newChild = new Trie();
                cur.children.put(c, newChild);
                cur = newChild;
            }
        }
        cur.isWord = true;
    }

    public static void main(String[] args) {
        StreamChecker streamChecker = new StreamChecker(new String[]{"cd", "f", "kl"}); // init the dictionary.
        System.out.println(streamChecker.query('a'));          // return false
        System.out.println(streamChecker.query('b'));          // return false
        System.out.println(streamChecker.query('c'));          // return false
        System.out.println(streamChecker.query('d'));          // return true, because 'cd' is in the wordlist
        System.out.println(streamChecker.query('e'));          // return false
        System.out.println(streamChecker.query('f'));          // return true, because 'f' is in the wordlist
        System.out.println(streamChecker.query('g'));          // return false
        System.out.println(streamChecker.query('h'));          // return false
        System.out.println(streamChecker.query('i'));          // return false
        System.out.println(streamChecker.query('j'));          // return false
        System.out.println(streamChecker.query('k'));          // return false
        System.out.println(streamChecker.query('l'));          // return true, because 'kl' is in the wordlist
    }

}
