import java.util.HashSet;
import java.util.Set;

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
public class AMZNTrie {

    Set<String> words;

    public static void main(String[] args) {
/*
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");
trie.search("app");     // returns true
 */
        AMZNTrie trie = new AMZNTrie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }

    /**
     * Initialize your data structure here.
     */
    public AMZNTrie() {
        words = new HashSet<>();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        words.add(word);
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        return words.contains(word);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        for (String word : words) {
            if (word.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

}
