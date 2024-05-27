package leetcode.companies.facebook;

/**
 * 211. Design Add and Search Words Data Structure
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 * Constraints:
 *
 * 1 <= word.length <= 25
 * word in addWord consists of lowercase English letters.
 * word in search consist of '.' or lowercase English letters.
 * There will be at most 2 dots in word for search queries.
 * At most 10^4 calls will be made to addWord and search.
 */
public class WordDictionaryAddSearch {

    Node root;

    public WordDictionaryAddSearch() {
        root = new Node(false);
    }

    public void addWord(String word) {
        if (word.isBlank()) {
            root.isWord = true;
            return;
        }
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new Node(false);
            }
            if (i == word.length() - 1) curr.children[c - 'a'].isWord = true;
            curr = curr.children[c - 'a'];
        }
    }

    public boolean search(String word) {
        return search(root, word);
    }

    private boolean search(Node curr, String word) {
        if (word.isBlank()) {
            return curr.isWord;
        }
        char c = word.charAt(0);
        if (c == '.') {
            for (int j = 0; j < 26; j++) {
                if (curr.children[j] == null) continue;
                if (search(curr.children[j], word.substring(1))) return true;
            }
            return false;
        } else {
            if (curr.children[c - 'a'] == null) return false;
            else return search(curr.children[c - 'a'], word.substring(1));
        }
    }

    class Node {
        Node[] children = new Node[26];
        boolean isWord;

        public Node(boolean isWord) {
            this.isWord = isWord;
        }
    }

}
