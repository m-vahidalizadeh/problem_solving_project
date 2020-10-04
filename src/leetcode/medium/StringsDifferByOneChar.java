package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Strings Differ by One Character
 * Given a list of strings dict where all the strings are of the same length.
 * <p>
 * Return True if there are 2 strings that only differ by 1 character in the same index, otherwise return False.
 * <p>
 * Follow up: Could you solve this problem in O(n*m) where n is the length of dict and m is the length of each string.
 * <p>
 * Example 1:
 * <p>
 * Input: dict = ["abcd","acbd", "aacd"]
 * Output: true
 * Explanation: Strings "abcd" and "aacd" differ only by one character in the index 1.
 * Example 2:
 * <p>
 * Input: dict = ["ab","cd","yz"]
 * Output: false
 * Example 3:
 * <p>
 * Input: dict = ["abcd","cccc","abyd","abab"]
 * Output: true
 * <p>
 * Constraints:
 * <p>
 * Number of characters in dict <= 10^5
 * dict[i].length == dict[j].length
 * dict[i] should be unique.
 * dict[i] contains only lowercase English letters.
 */
public class StringsDifferByOneChar {

    Trie root;
    int l;

    public boolean differByOne(String[] dict) {
        int n = dict.length;
        if (n == 0) return false;
        l = dict[0].length();
        root = new Trie();
        for (int i = 0; i < n; i++) {
            if (doesExist(dict[i], 1, 0, root)) return true;
            insert(dict[i]);
        }
        return false;
    }

    public class Trie {
        Map<Character, Trie> children;

        public Trie() {
            this.children = new HashMap<>();
        }
    }

    private void insert(String input) {
        Trie curr = root;
        for (int i = 0; i < l; i++) {
            char c = input.charAt(i);
            if (curr.children.containsKey(c)) curr = curr.children.get(c);
            else {
                Trie newNode = new Trie();
                curr.children.put(c, newNode);
                curr = newNode;
            }
        }
    }

    private boolean doesExist(String input, int tolerance, int i, Trie curr) {
        if (i == l) return tolerance == 0;
        if (tolerance < 0) return false;
        char c = input.charAt(i);
        boolean result = false;
        for (Map.Entry<Character, Trie> e : curr.children.entrySet()) {
            if (e.getKey() == c) result = result || doesExist(input, tolerance, i + 1, e.getValue());
            else result = result || doesExist(input, tolerance - 1, i + 1, e.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        StringsDifferByOneChar s = new StringsDifferByOneChar();
        System.out.println(s.differByOne(new String[]{"abcd", "acbd", "aacd"}));
        System.out.println(s.differByOne(new String[]{"ab", "cd", "yz"}));
        System.out.println(s.differByOne(new String[]{"abcd", "cccc", "abyd", "abab"}));
    }

}
