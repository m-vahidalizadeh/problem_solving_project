package leetcode.hard;

/**
 * 745. Prefix and Suffix Search
 * Design a special dictionary with some words that searchs the words in it by a prefix and a suffix.
 *
 * Implement the WordFilter class:
 *
 * WordFilter(string[] words) Initializes the object with the words in the dictionary.
 * f(string prefix, string suffix) Returns the index of the word in the dictionary, which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 *
 * Example 1:
 *
 * Input
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * Output
 * [null, 0]
 *
 * Explanation
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 *
 * Constraints:
 *
 * 1 <= words.length <= 15000
 * 1 <= words[i].length <= 10
 * 1 <= prefix.length, suffix.length <= 10
 * words[i], prefix and suffix consist of lower-case English letters only.
 * At most 15000 calls will be made to the function f.
 */
public class WordFilter {

    TrieNode root;

    public WordFilter(String[] words) {
        root = new TrieNode();
        for (int index = 0; index < words.length; index++) {
            String word = words[index] + "{";
            for (int i = 0; i < word.length(); i++) {
                TrieNode curr = root;
                curr.index = index;
                for (int j = i; j < 2 * word.length() - 1; j++) { // Add all_possible_suffixes+{+word For apple add {apple{apple,pple{apple,ple{apple,le{apple,l{apple,{apple}
                    int k = word.charAt(j % word.length()) - 'a';
                    if (curr.children[k] == null) curr.children[k] = new TrieNode();
                    curr = curr.children[k];
                    curr.index = index;
                }
            }
        }
    }

    public int f(String prefix, String suffix) {
        TrieNode curr = root;
        for (char c : (suffix + '{' + prefix).toCharArray()) {
            if (curr.children[c - 'a'] == null) return -1;
            curr = curr.children[c - 'a'];
        }
        return curr.index;
    }

    private class TrieNode {
        TrieNode[] children;
        int index;

        public TrieNode() {
            children = new TrieNode[27]; // a to z + { which is right after z in the ascii table
            index = 0;
        }
    }

}
