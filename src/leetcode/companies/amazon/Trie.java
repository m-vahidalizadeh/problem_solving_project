package leetcode.companies.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * 208. Implement Trie (Prefix Tree)
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 *
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 */
public class Trie {

    public class TrieDS {
        public boolean isWord;
        public Map<Character, TrieDS> children;

        public TrieDS() {
            this.isWord = false;
            this.children = new HashMap<>();
        }
    }

    TrieDS root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieDS();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        int n = word.length();
        TrieDS curr = root;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (curr.children.containsKey(c)) curr = curr.children.get(c);
            else {
                TrieDS newNode = new TrieDS();
                curr.children.put(c, newNode);
                curr = newNode;
            }
        }
        curr.isWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        int n = word.length();
        TrieDS curr = root;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (curr.children.containsKey(c)) curr = curr.children.get(c);
            else return false;
        }
        return curr.isWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        int n = prefix.length();
        TrieDS curr = root;
        for (int i = 0; i < n; i++) {
            char c = prefix.charAt(i);
            if (curr.children.containsKey(c)) curr = curr.children.get(c);
            else return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("apple");
        System.out.println(t.search("apple"));
        System.out.println(t.search("app"));
        System.out.println(t.startsWith("app"));
        t.insert("app");
        System.out.println(t.search("app"));
    }

}
