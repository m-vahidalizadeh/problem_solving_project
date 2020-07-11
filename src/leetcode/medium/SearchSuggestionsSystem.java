package leetcode.medium;

import java.util.*;

/**
 * Search Suggestions System
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
 * <p>
 * Return list of lists of the suggested products after each character of searchWord is typed.
 * <p>
 * Example 1:
 * <p>
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 * Example 2:
 * <p>
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Example 3:
 * <p>
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * Example 4:
 * <p>
 * Input: products = ["havana"], searchWord = "tatiana"
 * Output: [[],[],[],[],[],[],[]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= products.length <= 1000
 * There are no repeated elements in products.
 * 1 <= Σ products[i].length <= 2 * 10^4
 * All characters of products[i] are lower-case English letters.
 * 1 <= searchWord.length <= 1000
 * All characters of searchWord are lower-case English letters.
 */
public class SearchSuggestionsSystem {

    public class Trie {
        public Character val;
        public Map<Character, Trie> children;
        boolean isFinal;

        public Trie(Character val) {
            this.val = val;
            isFinal = false;
            children = new HashMap<>();
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie root = new Trie(null);
        for (String product : products) addToTrie(root, product);
        List<List<String>> result = new ArrayList<>();
        String prefix = "";
        for (int i = 0; i < searchWord.length(); i++) {
            prefix += searchWord.charAt(i);
            result.add(getWords(root, prefix));
        }
        return result;
    }

    private void addToTrie(Trie root, String product) {
        Trie currNode = root;
        for (int i = 0; i < product.length(); i++) {
            char currChar = product.charAt(i);
            if (currNode.children.containsKey(currChar)) {
                currNode = currNode.children.get(currChar);
            } else {
                Trie newTrie = new Trie(currChar);
                currNode.children.put(currChar, newTrie);
                currNode = newTrie;
            }
        }
        currNode.isFinal = true;
    }

    private List<String> getWords(Trie root, String prefix) {
        List<String> result = new ArrayList<>();
        Trie currNode = root;
        String curr = "";
        for (int i = 0; i < prefix.length(); i++) {
            char currChar = prefix.charAt(i);
            if (currNode.children.containsKey(currChar)) {
                if (currNode.val != null) curr += currNode.val;
                currNode = currNode.children.get(currChar);
            } else {
                return result;
            }
        }
        PriorityQueue<String> pq = new PriorityQueue<>();
        dfs(currNode, pq, curr);
        for (int i = 0; i < 3; i++) {
            if (pq.isEmpty()) break;
            result.add(pq.poll());
        }
        return result;
    }

    private void dfs(Trie currNode, PriorityQueue<String> pq, String curr) {
        curr += currNode.val;
        if (currNode.isFinal) {
            pq.add(curr);
        }
        for (Trie c : currNode.children.values()) {
            dfs(c, pq, curr);
        }
    }

    public static void main(String[] args) {
        SearchSuggestionsSystem s = new SearchSuggestionsSystem();
        String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        System.out.println(s.suggestedProducts(products, "mouse"));
    }

}
