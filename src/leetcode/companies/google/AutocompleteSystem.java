package leetcode.companies.google;

import java.util.*;

/**
 * Design Search Autocomplete System
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:
 * <p>
 * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
 * If less than 3 hot sentences exist, then just return as many as you can.
 * When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 * Your job is to implement the following functions:
 * <p>
 * The constructor function:
 * <p>
 * AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.
 * <p>
 * Now, the user wants to input a new sentence. The following function will provide the next character the user types:
 * <p>
 * List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 * <p>
 * Example:
 * Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 * The system have already tracked down the following sentences and their corresponding times:
 * "i love you" : 5 times
 * "island" : 3 times
 * "ironman" : 2 times
 * "i love leetcode" : 2 times
 * Now, the user begins another search:
 * <p>
 * Operation: input('i')
 * Output: ["i love you", "island","i love leetcode"]
 * Explanation:
 * There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 * <p>
 * Operation: input(' ')
 * Output: ["i love you","i love leetcode"]
 * Explanation:
 * There are only two sentences that have prefix "i ".
 * <p>
 * Operation: input('a')
 * Output: []
 * Explanation:
 * There are no sentences that have prefix "i a".
 * <p>
 * Operation: input('#')
 * Output: []
 * Explanation:
 * The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 * <p>
 * Note:
 * <p>
 * The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
 * The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
 * Please use double-quote instead of single-quote when you write test cases even for a character input.
 * Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.
 */
public class AutocompleteSystem {

    private Trie root;
    private String currentKey;

    public class Node {
        String sentence;
        int times;

        public Node(String sentence, int times) {
            this.sentence = sentence;
            this.times = times;
        }
    }

    public class Trie {
        int times;
        Trie[] children = new Trie[27];
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            insert(root, sentences[i], times[i]);
        }
        currentKey = "";
    }

    private int toInt(char c) {
        return c == ' ' ? 26 : c - 'a';
    }

    private void insert(Trie t, String sentence, int times) {
        for (int i = 0; i < sentence.length(); i++) {
            if (t.children[toInt(sentence.charAt(i))] == null) t.children[toInt(sentence.charAt(i))] = new Trie();
            t = t.children[toInt(sentence.charAt(i))];
        }
        t.times += times;
    }

    private PriorityQueue<Node> lookup(Trie t, String key) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
        for (int i = 0; i < key.length(); i++) {
            if (t.children[toInt(key.charAt(i))] == null) return pq;
            t = t.children[toInt(key.charAt(i))];
        }
        traverse(t, key, pq);
        return pq;
    }

    private void traverse(Trie t, String key, PriorityQueue<Node> pq) {
        if (t.times > 0) pq.add(new Node(key, t.times));
        for (char c = 'a'; c <= 'z'; c++) {
            if (t.children[c - 'a'] != null) traverse(t.children[c - 'a'], key + c, pq);
        }
        if (t.children[26] != null) traverse(t.children[26], key + ' ', pq);
    }

    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        if (c == '#') {
            insert(root, currentKey, 1);
            currentKey = "";
        } else {
            currentKey += c;
            PriorityQueue<Node> lookupResult = lookup(root, currentKey);
            for (int i = 0; !lookupResult.isEmpty() && i < 3; i++) {
                result.add(lookupResult.poll().sentence);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        AutocompleteSystem a = new AutocompleteSystem(new String[]{"i love you", "island", "iroman", "i love leetcode"},
                new int[]{5, 3, 2, 2});
        System.out.println(a.input('i'));
        System.out.println(a.input(' '));
        System.out.println(a.input('a'));
        System.out.println(a.input('#'));
    }

}
